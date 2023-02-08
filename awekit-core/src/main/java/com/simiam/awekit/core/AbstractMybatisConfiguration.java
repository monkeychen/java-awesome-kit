package com.simiam.awekit.core;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p>Title: AbstractMybatisConfiguration</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/12 16:54</p>
 */
public abstract class AbstractMybatisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AbstractMybatisConfiguration.class);

    private PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public SqlSessionFactory createSqlSessionFactory(DataSource dataSource, String mapperLocation, String name) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocation));
        } catch (IOException e) {
            logger.error("Fail to set mapper locations[{}]!", mapperLocation, e);
        }
        try {
            sqlSessionFactory = sessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("Fail to create Mybatis {} SqlSessionFactory Bean!", name, e);
        }
        return sqlSessionFactory;
    }
}
