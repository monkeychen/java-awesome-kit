package com.simiam.awekit.support;

import com.simiam.awekit.core.AbstractMybatisConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * <p>Title: PrimaryMybatisConfiguration</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/12 16:54</p>
 */
@Configuration
@MapperScan(basePackages = {"com.simiam.${app.pkg.name:**}.mapper.primary"}, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryMybatisConfiguration extends AbstractMybatisConfiguration {
    @Value("${mybatis.primary.mapper-locations:mapping/*.xml}")
    private String MAPPER_LOCATION;

    @Bean(name = "primarySqlSessionFactory")
    @ConditionalOnProperty(name = "spring.datasource.primary.enable")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) {
        return createSqlSessionFactory(dataSource, MAPPER_LOCATION, "primary");
    }
}
