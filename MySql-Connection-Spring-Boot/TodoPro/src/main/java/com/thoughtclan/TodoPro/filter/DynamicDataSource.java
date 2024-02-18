package com.thoughtclan.TodoPro.filter;


import com.thoughtclan.TodoPro.TenantContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DynamicDataSource extends DriverManagerDataSource {

    @Value("${spring.datasource.url}")
    private String url;

    @Override
    public String getUrl() {
        String dbName = getDatabaseNameFromRequest();
        String dynamicUrl = url + dbName + "?useSSL=false&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return dynamicUrl;
    }

    private String getDatabaseNameFromRequest() {
        return TenantContext.getTenant();
    }
}