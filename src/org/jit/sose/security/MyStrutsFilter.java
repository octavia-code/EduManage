//package org.jit.sose.security;
//
//import java.io.IOException;
//
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
// 
// 
//import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
// 
// 
//public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter{
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//            FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        String url = request.getRequestURI();         
//        System.out.println(url);         
//        if (url.contains("http://127.0.0.1:8080//ueditor/config.sose"/*request.getContextPath()+"/js/utf8-jsp/jsp/controller.jsp"*/)) {             
//            System.out.println("使用自定义过滤器");             
//            chain.doFilter(req, res);         
//        }else{             
//            System.out.println("使用默认过滤器");             
//            super.doFilter(req, res, chain);         
//        } 
//    }
//}
