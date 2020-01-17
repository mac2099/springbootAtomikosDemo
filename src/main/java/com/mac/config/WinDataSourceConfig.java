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
@EnableConfigurationProperties(value = {WinProperties.class})
@MapperScan(basePackages = "com.mac.mapper.win", sqlSessionFactoryRef = "winSqlSessionFactory")
public class WinDataSourceConfig {
    @Autowired
    private WinProperties winProperties;

    private static final String DATA_SOURCES_NAME = "winDataSource";

    private static final String SQL_SESSION_FACTORY = "winSqlSessionFactory";

    private static final String SQL_SESSION_TEMPLATE = "winSqlSessionTemplate";

    // 配置数据源2
    @Bean(name = DATA_SOURCES_NAME)
    public DataSource winDataSource() throws SQLException {
        // 首先要注意的是MySQL使用的DataSource 是MysqlXADataSource
        MysqlXADataSource mysqlXaDataSource = MyDataSourceUtil.getMysqlXADataSource(winProperties);

        //再者就是要把是MysqlXADataSource设置到AtomikosDataSourceBean中
        return MyDataSourceUtil.getAtomikosDataSourceBean(mysqlXaDataSource,
                winProperties,
                DATA_SOURCES_NAME);

    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory winSqlSessionFactory(@Qualifier(DATA_SOURCES_NAME) DataSource dataSource) throws Exception {
        return MyDataSourceUtil.getSqlSessionFactory(dataSource, winProperties);
    }

    @Bean(name = SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate winSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
