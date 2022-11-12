package com.xuegao.mapper.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GenericModelDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 新增
     */
    private List<T> addList;
    /**
     * 修改
     */
    private List<T> updateList;
    /**
     * 启用
     */
    private List<T> enableList;
    /**
     * 失效
     */
    private List<T> disableList;
    /**
     * 删除数据
     */
    private List<T> deleteList;

    // /**
    //  * 通用查询对象
    //  */
    // private GenericQueryExample generic;

    // /**
    //  * 通用导出功能使用
    //  * 菜单ID
    //  */
    // private String menuId;

    // /**
    //  * 通用查询编码
    //  */
    // private String genericSearchCode;

    // /**
    //  * 通用导出功能使用
    //  * 查询编码
    //  */
    // private String searchCode;

    private T vo;

    private String id;

    private String[] ids;

    private int page = 1;  //页码

    private int pageSize = 10; //每页记录数

    // private String elasticsearchFlag = "N"; // 索引标识


    // /**
    //  * 排序对象
    //  */
    // private List<OrderByClause> orderByClauses;

    // /**
    //  * 统计计算对象
    //  */
    // private List<Aggregation> aggregations;

    // /**
    //  * 包含规则
    //  */
    // private String[] includes;
    //
    // /**
    //  * 排除规则
    //  */
    // private String[] excludes;
    //
    // private String[] includeFields;

}
