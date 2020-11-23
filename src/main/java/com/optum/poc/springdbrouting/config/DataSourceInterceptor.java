package com.optum.poc.springdbrouting.config;


import com.optum.poc.springdbrouting.constant.BranchEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataSourceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String branch = request.getHeader("branch");
        if (BranchEnum.TEST.toString().equalsIgnoreCase(branch)) {
            BranchContextHolder.setBranchContext(BranchEnum.TEST);
        }
        else {
            BranchContextHolder.setBranchContext(BranchEnum.DEV);
        }
        return super.preHandle(request, response, handler);
    }
}
