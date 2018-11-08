package com.wensir.bigdata.hos.mybatis;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

@Configuration
@MapperScan(basePackages=HosDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "HosSqlSessionFactory")
public class HosDataSourceConfig {

    static final String PACKAGE = "com.wensir.bigdata.hos.**";

    /**
     * 获取datasource
     * @return
     * @throws IOException
     */
    @Primary
    @Bean("HosDataSource")
    public DataSource hosDataSource() throws IOException {
        //1.获取datasource相关信息
        ResourceLoader loader = new DefaultResourceLoader();
        InputStream inputStream = loader.getResource("classpath:application.properties").getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);

        //使用lambda如何操作再研究下
        Set<Object> propertiesSet = properties.keySet();
        Properties dsProperties = propertiesSet.stream()
                .filter(item -> item.toString().startsWith("datasource"))
                .map(item -> {
                    Properties properties1 = new Properties();
                  return  properties1.put(item.toString().replace("datasource.",""),properties.get(item));
                });

        //2.通过HikariDataSourceFactory 生产一个datasource

        HikariDataSourceFactory hikariDataSourceFactory = new HikariDataSourceFactory();
        hikariDataSourceFactory.setProperties(dsProperties);
        inputStream.close();
        return hikariDataSourceFactory.getDataSource();
    }

    @Bean("HosSqlSessionFactory")
    @Primary
    public SqlSessionFactory hosSqlSessionFactory(@Qualifier("HosDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //读取Mybatis相关配置
        ResourceLoader loader = new DefaultResourceLoader();
        sqlSessionFactoryBean.setConfigLocation(loader.getResource("classpath:application.properties"));

        //获取SQLsessionfactory实例
        sqlSessionFactoryBean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());
        return sqlSessionFactoryBean.getObject();
    }

}
