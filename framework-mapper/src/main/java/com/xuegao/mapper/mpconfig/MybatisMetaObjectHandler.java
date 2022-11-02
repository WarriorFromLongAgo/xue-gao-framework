package com.xuegao.mapper.mpconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xuegao.core.model.Context;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.core.model.UserInfo;
import com.xuegao.util.JsonUtil;
import com.xuegao.util.LocalDateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDateTime;

public class MybatisMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MybatisMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("[xue-gao-framework][MybatisMetaObjectHandler][insertFill][metaObject={}]", JsonUtil.toJsonString(metaObject));
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        UserInfo userInfo = context.getUserInfo();

        // 起始版本 3.3.0(推荐使用)
        // this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        // 起始版本 3.3.3(推荐)
        if (StringUtils.isNotBlank(userInfo.getUserId())) {
            this.strictInsertFill(metaObject, "createBy", userInfo::getUserId, String.class);
        }

        this.strictInsertFill(metaObject, "createTime", LocalDateTimeUtil::now, LocalDateTime.class);

        if (StringUtils.isNotBlank(userInfo.getUserId())) {
            this.strictInsertFill(metaObject, "updateBy", userInfo::getUserId, String.class);
        }

        this.strictInsertFill(metaObject, "updateTime", LocalDateTimeUtil::toDate, Date.class);

        if (StringUtils.isNotBlank(context.getTraceId())) {
            this.strictInsertFill(metaObject, "traceId", context::getTraceId, String.class);
        }
        // 或者
        // 也可以使用(3.3.0 该方法有bug)
        // this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("[xue-gao-framework][MybatisMetaObjectHandler][updateFill][metaObject={}]", JsonUtil.toJsonString(metaObject));
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        UserInfo userInfo = context.getUserInfo();
        // 起始版本 3.3.0(推荐)
        // this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        // 起始版本 3.3.3(推荐)
        if (StringUtils.isNotBlank(userInfo.getUserId())) {
            this.strictUpdateFill(metaObject, "updateBy", userInfo::getUserId, String.class);
        }

        this.strictUpdateFill(metaObject, "updateTime", LocalDateTimeUtil::toDate, Date.class);

        if (StringUtils.isNotBlank(context.getTraceId())) {
            this.strictUpdateFill(metaObject, "traceId", context::getTraceId, String.class);
        }
        // 或者
        // 也可以使用(3.3.0 该方法有bug)
        // this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}
