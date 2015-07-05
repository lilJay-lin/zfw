package com.mimi.zfw.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ZFWSessionListener implements HttpSessionListener {
    private ServletContext context = null;//servlet上下文
    private int count = 0;
    public void sessionCreated(HttpSessionEvent event) {
	System.out.println("in:"+(++count));
    //因为创建session没有动作,所以这个方法就可以不写了
   }
    public void sessionDestroyed(HttpSessionEvent event) {
	System.out.println("out:"+(--count));
	System.out.println(event.getSession().getAttribute("name")+"_"+event.getSession().getAttribute("userName"));
      //监听session,当session过期后,就转道登陆页面
      if (context == null) 
        storeInServletContext(event);
      
    }
       private void storeInServletContext(HttpSessionEvent event) {
      HttpSession session = event.getSession();
      context = session.getServletContext();
      context.setAttribute("sessionCounter", this);
    }
  }