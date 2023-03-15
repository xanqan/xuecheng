package com.xuecheng.content.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 *
 * @author xanqan
 */
@Configuration
@MapperScan({"com.xuecheng.content.mapper"})
public class MyBatisConfig {
}
