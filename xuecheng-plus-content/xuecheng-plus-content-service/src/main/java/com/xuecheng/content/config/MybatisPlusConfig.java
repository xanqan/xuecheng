package com.xuecheng.content.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus 配置
 */
@Configuration
@MapperScan({"com.xuecheng.content.mapper"})
public class MybatisPlusConfig {

}