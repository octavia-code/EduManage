package org.jit.sose.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ueditor")
public class UEditorController {
	 
	    @RequestMapping(value="/config")
	    public void config(HttpServletRequest request, HttpServletResponse response) {
	    	
	    	try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        response.setContentType("application/json");
	        String rootPath = request.getSession()
	                .getServletContext().getRealPath("/");
	 
	        try {
	            String exec = new ActionEnter(request, rootPath).exec();
	            PrintWriter writer = response.getWriter();
	            writer.write(exec);
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	    }
}
