package com.xuegao.mapper.mpservice;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionHolder;
import org.slf4j.Logger;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.function.Function;

public class SqlHelperV2 {

    public static <E> int executeBatch(Class<?> entityClass,
                                       Logger log,
                                       List<E> list,
                                       int batchSize,
                                       Function<E, Integer> function) {
        Assert.isFalse(batchSize < 1, "batchSize must not be less than one");
        if (ObjectUtils.isEmpty(list)) {
            return 0;
        }
        List<List<E>> partition = Lists.partition(list, batchSize);
        int count = 0;
        try {
            for (List<E> subList : partition) {
                count += executeBatch(entityClass, log, subList, function);
            }
        } catch (Exception e) {
            throw ExceptionUtils.mpe("批量修改异常", e);
        }
        return count;
    }

    @SneakyThrows
    private static <E> int executeBatch(Class<?> entityClass,
                                        Logger log,
                                        List<E> eList,
                                        Function<E, Integer> function) {
        SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(entityClass);
        SqlSessionHolder sqlSessionHolder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(sqlSessionFactory);
        boolean transaction = TransactionSynchronizationManager.isSynchronizationActive();

        int count = 0;

        if (sqlSessionHolder != null) {
            SqlSession sqlSession = sqlSessionHolder.getSqlSession();
            //原生无法支持执行器切换，当存在批量操作时，会嵌套两个session的，优先commit上一个session
            //按道理来说，这里的值应该一直为false。
            sqlSession.commit(!transaction);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        if (!transaction) {
            log.warn("SqlSession [" + sqlSession + "] Transaction not enabled");
        }
        try {
            for (E e : eList) {
                count += function.apply(e);
            }
            //非事务情况下，强制commit。
            sqlSession.commit(!transaction);
            return count;
        } catch (Throwable t) {
            sqlSession.rollback();
            Throwable unwrapped = ExceptionUtil.unwrapThrowable(t);
            if (unwrapped instanceof PersistenceException) {
                MyBatisExceptionTranslator myBatisExceptionTranslator
                        = new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true);
                Throwable throwable = myBatisExceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
                if (throwable != null) {
                    throw throwable;
                }
            }
            throw ExceptionUtils.mpe(unwrapped);
        } finally {
            sqlSession.flushStatements();
            sqlSession.close();
        }
    }
}