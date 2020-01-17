package com.mac.utils;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mac.config.BaseProperties;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
public class MyDataSourceUtil {

    public static MysqlXADataSource getMysqlXADataSource(BaseProperties baseProperties) {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(baseProperties.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(baseProperties.getPassword());
        mysqlXaDataSource.setUser(baseProperties.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        return mysqlXaDataSource;
    }

    public static AtomikosDataSourceBean getAtomikosDataSourceBean(MysqlXADataSource mysqlXaDataSource,
                                                                   BaseProperties baseProperties, String dataSourceName) throws SQLException {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(dataSourceName);
        xaDataSource.setMinPoolSize(baseProperties.getMinPoolSize());
        xaDataSource.setMaxPoolSize(baseProperties.getMaxPoolSize());
        xaDataSource.setMaxLifetime(baseProperties.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(baseProperties.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(baseProperties.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(baseProperties.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(baseProperties.getMaxIdleTime());
        xaDataSource.setTestQuery(baseProperties.getTestQuery());
        return xaDataSource;
    }

    public static SqlSessionFactory getSqlSessionFactory(DataSource dataSource,
                                                         BaseProperties baseProperties) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(baseProperties.getMapperLocations()));
        bean.setTypeAliasesPackage(baseProperties.getTypeAliasesPackage());
        return bean.getObject();
    }
}
