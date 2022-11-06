package com.xuegao.config.web;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

// 我们在进行日期格式序列化的时候，经常会有把日期、时间映射成yyyy-MM-dd HH:mm:ss、yyyy-MM-dd、HH:mm:ss格式的字符串返回给前端。
// 同样的，前端也会将yyyy-MM-dd HH:mm:ss、yyyy-MM-dd、HH:mm:ss格式的字符串请求给业务服务，
// 业务又需要映射成Date相关的java类。
// Spring在日期格式的出入参序列化提供了 @DateTimeFormat、@JsonFormat注解。
//
// 作者：柏炎
// 链接：https://juejin.cn/post/7158016257197867044
// 来源：稀土掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


@Configuration
@ConditionalOnProperty(value = "xuegao.fmk.config.jackson.enable", havingValue = "true")
public class CommonJacksonConfig {

    public static final String timeFormat = "HH:mm:ss";
    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 全局时间格式化
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            //日期序列化
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            //日期反序列化
            builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}