package com.ch.manager.config;

import java.util.HashMap;

import javax.sql.DataSource;

import com.ch.manager.utils.IdUtil;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author chenhao
 */
@Configuration
public class BeetlSqlConfig {

    @Bean(name = "sqlManagerFactoryBean")
    @Profile("local")
    public SqlManagerFactoryBean sqlManagerFactoryBean(@Qualifier("druidDataSource") DataSource datasource) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setSqlLoader(new DevMdLoader("/sql"));
        factory.setNc(new UnderlinedNameConversion());
        try {
            SQLManager sqlManager = factory.getObject();
            sqlManager.addIdAutonGen("uuid", new IDAutoGen<String>() {
                public String nextID(String params) {
                    return IdUtil.getUUID();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        return factory;
    }

    @Bean(name = "sqlManagerFactoryBean")
    @Profile({"pro", "dev"})
    public SqlManagerFactoryBean sqlManagerFactoryBean2(@Qualifier("druidDataSource") DataSource datasource) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setNc(new UnderlinedNameConversion());
        factory.setSqlLoader(new ClasspathLoader("/sql"));
        try {
            SQLManager sqlManager = factory.getObject();
            sqlManager.addIdAutonGen("uuid", new IDAutoGen<String>() {
                public String nextID(String params) {
                    return IdUtil.getUUID();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        return factory;
    }

    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.ch.manager.dao");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }
}
