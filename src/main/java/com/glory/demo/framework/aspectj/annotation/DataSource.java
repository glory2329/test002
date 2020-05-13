package com.glory.demo.framework.aspectj.annotation;

import com.glory.demo.framework.aspectj.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * author : glory
 * date : 2019/12/6 21:50
 * description : 自定义多数据源切换注解
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
