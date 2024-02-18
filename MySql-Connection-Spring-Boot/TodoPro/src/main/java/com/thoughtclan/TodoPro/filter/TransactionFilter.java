package com.thoughtclan.TodoPro.filter;

import com.thoughtclan.TodoPro.Entity.DbNames;
import com.thoughtclan.TodoPro.Repository.DbNamesRepository;
import com.thoughtclan.TodoPro.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Order(1)
@WebFilter(urlPatterns = {"*"})
public class TransactionFilter  implements Filter{
    @Autowired
    private  DbNamesRepository dbNamesRepository;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getHeader("dbId") != null) {
            DbNames dbname =dbNamesRepository.findById(Integer.parseInt(req.getHeader("dbId")));

            if (dbname!= null) {
                TenantContext.setTenant(dbname.getDbname());
                chain.doFilter(request, response);
            }

        } else {
            sendCustomErrorResponse(response, "Environment Id is not provided", HttpStatus.BAD_REQUEST);

        }

    }
    private void sendCustomErrorResponse(ServletResponse response, String errorMessage, HttpStatus badRequest) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(badRequest.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = httpResponse.getWriter();
        writer.write("{\"error\":\"" + errorMessage + "\"}");
        writer.flush();
    }
}
