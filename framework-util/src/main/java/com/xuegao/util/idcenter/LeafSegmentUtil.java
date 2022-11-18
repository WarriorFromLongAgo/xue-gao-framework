package com.xuegao.util.idcenter;

import com.sankuai.inf.leaf.IDGen;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.segment.dao.impl.IDAllocDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * 生成的，都是类似数据的递增id
 */
public class LeafSegmentUtil {

    @Autowired
    private DataSource dataSource;

    public long nextId() {
        IDGen idGen = new SegmentIDGenImpl();
        // Config Dao
        IDAllocDao dao = new IDAllocDaoImpl(dataSource);
        ((SegmentIDGenImpl) idGen).setDao(dao);
        idGen.init();
        Result r = idGen.get("leaf-segment-test");
        System.out.println(r);
        return r.getId();
    }


}
