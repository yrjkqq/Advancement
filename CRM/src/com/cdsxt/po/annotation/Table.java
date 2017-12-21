package com.cdsxt.po.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
    TYPE,  使用在类上

    FIELD, 属性

    METHOD,方法

    PARAMETER,参数

    CONSTRUCTOR,构造器

    LOCAL_VARIABLE,局部变量

    ANNOTATION_TYPE, 注解

    PACKAGE,包
 * 
 * 
 * @author Any
 * 
 * 
 * 注解的保留策略
 * 
 * RetentionPolicy.RUNTIME  运行时  如果要通过反射区解析注解  则注解需要在该时候
 * RetentionPolicy.CLASS  字节码中
 * RetentionPolicy.SOURCE 源码中
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	//属性
	String name();
}
