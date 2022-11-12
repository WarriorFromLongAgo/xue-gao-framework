package com.xuegao.mapper.mpservice;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xuegao.core.model.Context;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.core.model.FmkUserInfo;
import com.xuegao.mapper.enums.DelFlagEnum;
import com.xuegao.mapper.model.GenericModel;
import com.xuegao.mapper.model.GenericModelField;
import com.xuegao.util.LocalDateTimeUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 简单的版本，只需要四个方法，
 * 新增，禁用，查询，修改
 */
public abstract class AbstractMpServiceV2<M extends BaseMapper<T>, T extends GenericModel<PK>, PK extends Serializable>
        extends ServiceImpl<M, T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractMpServiceV2.class);


    // region 新增

    /**
     * 新增
     * 如果新增的字段是null，也新增该字段
     * <p>
     * insert
     *
     * @param entity:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:41
     */
    public T mpInsert(T entity) {
        setDefaultByInsert(entity);
        getBaseMapper().insert(entity);
        return entity;
    }

    /**
     * 批量新增
     * insertBatch
     *
     * @param entityList:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:48
     */
    public Collection<T> mpInsertBatch(Collection<T> entityList) {
        setDefaultByInsert(entityList);
        saveBatch(entityList, DEFAULT_BATCH_SIZE);
        return entityList;
    }

    /**
     * 批量新增
     * insertBatch
     *
     * @param entityList:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:48
     */
    public Collection<T> mpInsertBatch(Collection<T> entityList, int batchSize) {
        saveBatch(entityList, batchSize);
        return entityList;
    }

    // endregion

    // region 删除

    /**
     * 禁用
     * disable
     *
     * @param ids:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:48
     */
    @SneakyThrows
    public int mpDisable(PK... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        LambdaUpdateWrapper<T> updateWrapper = Wrappers.lambdaUpdate(getEntityClass());
        updateWrapper.in(GenericModel::getId, ids);
        updateWrapper.set(GenericModel::getDelFlag, DelFlagEnum.DEL_FLAG_0.getCode());
        this.setDefault(updateWrapper);
        boolean update = super.update(/*updateWrapper.getEntity(), */updateWrapper);

        // 测试
        // Class<T> entityClass = getEntityClass();
        // T t = entityClass.newInstance();
        // UpdateWrapper<T> updateWrapper = Wrappers.update(t);
        // updateWrapper.in(GenericModelField.FILED_ID, ids);
        // updateWrapper.set(GenericModelField.FILED_SQL_COLUMN_DEL_FLAG, DelFlagEnum.DEL_FLAG_0.getCode());
        // // updateWrapper.eq("username", "1");
        // updateWrapper.set("username", "1");
        // updateWrapper.eq("password", "1");
        // // updateWrapper.set("password", "1");
        // this.setDefault(updateWrapper);
        // boolean update = super.update(t, updateWrapper);
        if (!update) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpDisable][update={}]", update);
            return ids.length;
        }
        return ids.length;
    }

    public int mpEnable(PK... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        LambdaUpdateWrapper<T> updateWrapper = Wrappers.lambdaUpdate(getEntityClass());
        updateWrapper.in(GenericModel::getId, ids);
        updateWrapper.set(GenericModel::getDelFlag, DelFlagEnum.DEL_FLAG_1.getCode());
        this.setDefault(updateWrapper);
        boolean update = super.update(/*updateWrapper.getEntity(),*/ updateWrapper);
        if (!update) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpEnable][update={}]", update);
            return ids.length;
        }
        return ids.length;
    }

    /**
     * 删除
     * delete
     *
     * @param ids:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:48
     */
    public int mpDelete(PK... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        boolean removeBatchByIds = super.removeBatchByIds(Lists.newArrayList(ids));
        if (!removeBatchByIds) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpDelete][removeBatchByIds={}]", removeBatchByIds);
            return ids.length;
        }
        return ids.length;
    }
    // endregion

    // region 查询
    public T mpGetById(PK id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return super.getById(id);
    }

    public List<T> mpGetByIds(PK... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        return super.listByIds(Lists.newArrayList(ids));
    }

    /**
     * 指定字段的查询方式
     * mpListByColumn
     *
     * @param function:
     * @param idList:
     * @param delFlagEnum:
     * @return java.util.List<T>
     * @author xuegao
     * @date 2022/11/5 19:19
     */
    public List<T> mpListByColumn(SFunction<T, String> function,
                                  Collection<? extends Serializable> idList,
                                  DelFlagEnum delFlagEnum) {
        if (ObjectUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<T> queryWrapper = Wrappers.lambdaQuery(getEntityClass());
        queryWrapper.in(function, idList);
        queryWrapper.eq(GenericModel::getDelFlag, delFlagEnum.getCode());
        List<T> list = super.list(queryWrapper);
        if (ObjectUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    public List<T> mpListByColumn(SFunction<T, String> function,
                                  Collection<? extends Serializable> idList) {
        if (ObjectUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        return this.mpListByColumn(function, idList, DelFlagEnum.DEL_FLAG_1);
    }

    /**
     * 根据对象的属性，进行查询，会将对象的非空字段作为查询条件
     * mpListLimit
     *
     * @param t:
     * @return java.util.List<T>
     * @author xuegao
     * @date 2022/11/12 23:20
     */
    public List<T> mpListLimit(T t, int limit) {
        if (ObjectUtils.isEmpty(t)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<T> queryWrapper = Wrappers.lambdaQuery(t);
        queryWrapper.eq(GenericModel::getDelFlag, DelFlagEnum.DEL_FLAG_1);
        if (limit > 0) {
            queryWrapper.last("limit " + limit);
        }
        List<T> list = super.list(queryWrapper);
        if (ObjectUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    // endregion

    // region 修改 更新
    // 这么处理，会将对象的字段，带给sql，也就是驼峰，带给sql
    // public int mpUpdate(T t) {
    //     if (Objects.isNull(t)) {
    //         return 0;
    //     }
    //     TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
    //     List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    //     if (ObjectUtils.isEmpty(fieldList)) {
    //         return 0;
    //     }
    //     UpdateWrapper<T> updateWrapper = Wrappers.update(t);
    //     for (Field field : fieldList) {
    //         TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
    //         // 如果字段有这个注解
    //         if (Objects.nonNull(tableFieldAnnotation)) {
    //             // 如果数据库不存在这个字段
    //             boolean exist = tableFieldAnnotation.exist();
    //             if (Boolean.FALSE.equals(exist)) {
    //                 continue;
    //             }
    //         }
    //         Object fieldValue = ReflectionKit.getFieldValue(t, field.getName());
    //         // 如果这个字段的值为空，那么过滤这个字段
    //         // if (Objects.isNull(fieldValue)) {
    //         //     continue;
    //         // }
    //         updateWrapper.set(field.getName(), fieldValue);
    //     }
    //     this.setDefault(updateWrapper);
    //     boolean update = super.update(updateWrapper);
    //     if (!update) {
    //         log.info("[xue-gao-framework][AbstractMpServiceV2][mpUpdate][update={}]", update);
    //         return 0;
    //     }
    //     return 1;
    // }

    public int mpUpdate(T t) {
        if (Objects.isNull(t)) {
            return 0;
        }
        if (ObjectUtils.isEmpty(t.getId())) {
            throw new RuntimeException("mpUpdate时id不能为空");
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();

        if (ObjectUtils.isEmpty(fieldList)) {
            return 0;
        }
        UpdateWrapper<T> updateWrapper = Wrappers.update(null);
        for (TableFieldInfo tableFieldInfo : fieldList) {
            // 如果是特殊的五个字段，直接返回
            if (GenericModelField.sqlSet().contains(tableFieldInfo.getColumn())) {
                continue;
            }
            Field field = tableFieldInfo.getField();
            TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
            // 如果字段有这个注解
            if (Objects.nonNull(tableFieldAnnotation)) {
                // 如果数据库不存在这个字段
                boolean exist = tableFieldAnnotation.exist();
                if (Boolean.FALSE.equals(exist)) {
                    continue;
                }
            }
            Object fieldValue = ReflectionKit.getFieldValue(t, field.getName());
            // 如果这个字段的值为空，那么过滤这个字段
            // if (Objects.isNull(fieldValue)) {
            //     continue;
            // }
            updateWrapper.set(tableFieldInfo.getColumn(), fieldValue);
        }
        updateWrapper.eq(GenericModelField.FILED_ID, t.getId());
        this.setDefault(updateWrapper);
        boolean update = super.update(updateWrapper);
        if (!update) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpUpdate][update={}]", update);
            return 0;
        }
        return 1;
    }

    public int mpUpdateBatch(Collection<T> collection) {
        if (ObjectUtils.isEmpty(collection)) {
            return 0;
        }
        int count = SqlHelperV2.executeBatch(getEntityClass(), log,
                new ArrayList<>(collection), 100, this::mpUpdate);
        if (count <= 0) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpUpdateBatch][count={}]", count);
            return 0;
        }
        return count;
    }

    /**
     * 更新的时候，过滤空字段
     * mpUpdateFilterNull
     *
     * @param t:
     * @return int
     * @author xuegao
     * @date 2022/11/5 21:10
     */
    public int mpUpdateFilterNull(T t) {
        if (Objects.isNull(t)) {
            return 0;
        }
        if (ObjectUtils.isEmpty(t.getId())) {
            throw new RuntimeException("mpUpdate时id不能为空");
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();

        if (ObjectUtils.isEmpty(fieldList)) {
            return 0;
        }
        UpdateWrapper<T> updateWrapper = Wrappers.update(null);
        for (TableFieldInfo tableFieldInfo : fieldList) {
            // 如果是特殊的五个字段，直接返回
            if (GenericModelField.sqlSet().contains(tableFieldInfo.getColumn())) {
                continue;
            }
            Field field = tableFieldInfo.getField();
            TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
            // 如果字段有这个注解
            if (Objects.nonNull(tableFieldAnnotation)) {
                // 如果数据库不存在这个字段
                boolean exist = tableFieldAnnotation.exist();
                if (Boolean.FALSE.equals(exist)) {
                    continue;
                }
            }
            Object fieldValue = ReflectionKit.getFieldValue(t, field.getName());
            // 如果这个字段的值为空，那么过滤这个字段
            if (Objects.isNull(fieldValue)) {
                continue;
            }
            updateWrapper.set(tableFieldInfo.getColumn(), fieldValue);
        }
        updateWrapper.eq(GenericModelField.FILED_ID, t.getId());
        this.setDefault(updateWrapper);
        boolean update = super.update(updateWrapper);
        if (!update) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpUpdate][update={}]", update);
            return 0;
        }
        return 1;
    }


    public int mpUpdateBatchFilterNull(Collection<T> collection) {
        if (ObjectUtils.isEmpty(collection)) {
            return 0;
        }
        int count = SqlHelperV2.executeBatch(getEntityClass(), log,
                new ArrayList<>(collection), 100, this::mpUpdateFilterNull);
        if (count <= 0) {
            log.info("[xue-gao-framework][AbstractMpServiceV2][mpUpdateBatchFilterNull][count={}]", count);
            return 0;
        }
        return count;
    }

    // endregion

    /**
     * 在修改的时候，进行填充数据
     * setDefault
     *
     * @param updateWrapper:
     * @author xuegao
     * @date 2022/11/5 14:59
     */
    public void setDefault(LambdaUpdateWrapper<T> updateWrapper) {
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        FmkUserInfo fmkUserInfo = context.getUserInfo();

        updateWrapper.set(GenericModel::getUpdatedBy, fmkUserInfo.getUserId());
        updateWrapper.set(GenericModel::getUpdatedTime, LocalDateTimeUtil.now());
        updateWrapper.set(GenericModel::getTraceId, context.getTraceId());
    }

    public void setDefault(UpdateWrapper<T> updateWrapper) {
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        FmkUserInfo fmkUserInfo = context.getUserInfo();

        updateWrapper.set(GenericModelField.FILED_SQL_COLUMN_UPDATED_BY, fmkUserInfo.getUserId());
        updateWrapper.set(GenericModelField.FILED_SQL_COLUMN_UPDATED_TIME, LocalDateTimeUtil.now());
        updateWrapper.set(GenericModelField.FILED_SQL_COLUMN_TRACE_ID, context.getTraceId());
    }

    public void setDefaultByInsert(T t) {
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        FmkUserInfo fmkUserInfo = context.getUserInfo();
        LocalDateTime now = LocalDateTimeUtil.now();

        t.setCreatedBy(fmkUserInfo.getUserId());
        t.setCreatedTime(now);
        t.setUpdatedBy(fmkUserInfo.getUserId());
        t.setUpdatedTime(now);
        t.setTraceId(context.getTraceId());
    }

    public void setDefaultByInsert(Collection<T> tCollect) {
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        FmkUserInfo fmkUserInfo = context.getUserInfo();
        LocalDateTime now = LocalDateTimeUtil.now();

        for (T t : tCollect) {
            t.setCreatedBy(fmkUserInfo.getUserId());
            t.setCreatedTime(now);
            t.setUpdatedBy(fmkUserInfo.getUserId());
            t.setUpdatedTime(now);
            t.setTraceId(context.getTraceId());
        }
    }

    public void test(T t) {
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(getEntityClass());
        TableInfo info = TableInfoHelper.getTableInfo(getEntityClass());

        LambdaUpdateWrapper<T> updateWrapper = Wrappers.lambdaUpdate(getEntityClass());
        T entity = updateWrapper.getEntity();

    }
}
