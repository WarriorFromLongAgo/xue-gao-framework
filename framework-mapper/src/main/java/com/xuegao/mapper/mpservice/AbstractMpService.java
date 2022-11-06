package com.xuegao.mapper.mpservice;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;

import java.util.Collection;

public abstract class AbstractMpService<M extends BaseMapper<T>, T, PK> extends ServiceImpl<M, T> {
    // todo 返回false，那么要报错
    // todo 分库分表情况下，不能为空

    protected BaseMapper<T> setBaseMapper;

    protected AbstractMpService(BaseMapper<T> baseMapper) {
        this.setBaseMapper = baseMapper;
    }

    /**
     * 分库分表的键
     * shardingKey
     *
     * @return java.lang.String
     * @author xuegao
     * @date 2022/11/3 23:58
     */
    public abstract String shardingKey();

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
    public T insert(T entity) {
        setBaseMapper.insert(entity);
        return entity;
    }

    /**
     * 新增
     * 如果新增的字段是null，则不会新增该字段
     * <p>
     * insert
     *
     * @param entity:
     * @return T
     * @author xuegao
     * @date 2022/11/3 23:41
     */
    public T insertFilterNull(T entity) {
        setBaseMapper.insert(entity);
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
    public Collection<T> insertBatch(Collection<T> entityList) {
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
    public Collection<T> insertBatch(Collection<T> entityList, int batchSize) {
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
    public int disable(PK... ids) {
        update(null, new UpdateWrapper<T>().set("del_flag", 1).in("id", ids));
        return ids.length;
    }

    public int enable(PK... ids) {
        update(null, new UpdateWrapper<T>().set("del_flag", 1).in("id", ids));
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
    public int delete(PK... ids) {
        removeByIds(Lists.newArrayList(ids));
        return ids.length;
    }
    // endregion

    // region 查询
    // public int listLimit(T t) {
    //     removeByIds(Lists.newArrayList(t));
    //     return ids.length;
    // }
    //
    // public int listByColumns(T t) {
    //     removeByIds(Lists.newArrayList(ids));
    //     return ids.length;
    // }

    // public int getById(PK id) {
    //     removeByIds(Lists.newArrayList(ids));
    //     return ids.length;
    // }

    public int getByIds(PK... ids) {
        removeByIds(Lists.newArrayList(ids));
        return ids.length;
    }

    // public void search(Pagination<T> pagination) {
    //
    // }

    // endregion

    // region 修改 更新
    // updateFilterNull
    // update
    // updateBatch
    // updateBatchFilterNull

    public int updateSelectiveWithNullColumns(T data, String... columnNames) {
        return 0;
    }


    public int updateBySingleShardingKey(String columnName, Object columnValue, T... datas) {

        return 0;
    }

    // endregion

}
