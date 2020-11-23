package com.optum.poc.springdbrouting.config;

import com.optum.poc.springdbrouting.constant.BranchEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return BranchContextHolder.getBranchContext();
    }
    public void initDataSource(DataSource devDataSource, DataSource testDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(BranchEnum.DEV, devDataSource);
        dataSourceMap.put(BranchEnum.TEST, testDataSource);
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(devDataSource);
        this.afterPropertiesSet();
    }
}
