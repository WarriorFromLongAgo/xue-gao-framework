package com.xuegao.mapper.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.core.model.BaseResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//如果是列表页，那必然要返回给前端数据总条数，不然前端不好计算你一共有几页
@Getter
@Setter
public class PageResult<T> extends BaseResult {

    private Long total;

    private List<T> data;

    public PageResult() {
    }

    public static <T> PageResult<T> ok(Page<T> result) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(CODE_SUCCESS);
        pageResult.setMessage(QUERY_SUCCESS);
        pageResult.setTotal(result.getTotal());
        pageResult.setData(result.getRecords());
        return pageResult;
    }
}