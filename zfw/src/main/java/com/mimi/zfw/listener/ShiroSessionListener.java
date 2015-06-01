package com.mimi.zfw.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ValidatingSessionManager;

public class ShiroSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
	// TODO Auto-generated method stub
	System.out.println("start:"+session.getId());

    }

    @Override
    public void onStop(Session session) {
	// TODO Auto-generated method stub
	System.out.println("stop:"+session.getId());
    }

    @Override
    public void onExpiration(Session session) {
	// TODO Auto-generated method stub
	System.out.println("expiration:"+session.getId());

    }

}
