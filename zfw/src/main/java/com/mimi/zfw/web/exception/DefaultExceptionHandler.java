package com.mimi.zfw.web.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mimi.zfw.web.shiro.exception.IncorrectCaptchaException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-12
 * <p>Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {
//    /**
//     * 没有权限 异常
//     * <p/>
//     * 后续根据不同的需求定制即可
//     */
//    @ExceptionHandler({UnauthorizedException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("exception", e);
//        mv.setViewName("unauthorized");
//        return mv;
//    }

    @ExceptionHandler({UnauthorizedException.class,AuthorizationException.class,UnauthenticatedException.class,IncorrectCaptchaException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("exception", e);
//        mv.setViewName("unauthorized");
        return "index";
    }

    @ExceptionHandler({IncorrectCaptchaException.class})
    public String defaultExceptino(NativeWebRequest request,Exception e){
	System.out.println("haha"+e.getMessage());
	return "index";
    }
}
