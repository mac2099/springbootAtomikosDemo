package com.mac.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mac.utils.MyDataSourceUtil;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <p>description：</p>
 * <p>copyright： copyright(C)2016-2099</p>
 * <p>Life is short,we need passion</p>
 * <p>Summary： </p>
 * <p>instructions： </p>
 * Date 2020-01-17</p>
 * Author mac
 *
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(value = {MacProperties.class})
@MapperScan(basePackages = "com.mac.mapper.mac", sqlSessionFactoryRef = "macSqlSessionFactory")
public class MacDataSourceConfig {

    @Autowired
    private MacProperties macProperties;

    private static final String DATA_SOURCES_NAME = "macDataSource";

    private static final String SQL_SESSION_FACTORY = "macSqlSessionFactory";

    private static final String SQL_SESSION_TEMPLATE = "macSqlSessionTemplate";

    @Primary
    @Bean(name = DATA_SOURCES_NAME)
    public DataSource macDataSource() throws SQLException {
        // MySQL使用的DataSource是MysqlXADataSource
        MysqlXADataSource mysqlXaDataSource = MyDataSourceUtil.getMysqlXADataSource(macProperties);


        // 将MysqlXADataSource设置到AtomikosDataSourceBean中
        return MyDataSourceUtil.getAtomikosDataSourceBean(mysqlXaDataSource,
                macProperties,
                DATA_SOURCES_NAME);
    }


    @Primary
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory macSqlSessionFactory(@Qualifier(DATA_SOURCES_NAME) DataSource dataSource) throws Exception {
        return MyDataSourceUtil.getSqlSessionFactory(dataSource, macProperties);
    }


    @Primary
    @Bean(name = SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate macSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
