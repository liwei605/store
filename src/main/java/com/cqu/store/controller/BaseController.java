package com.cqu.store.controller;

import com.cqu.store.controller.ex.*;
import com.cqu.store.service.ex.*;
import com.cqu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//控制层类的基类
public class BaseController {

    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage(e.getMessage()); //获取名字占用的错误信息
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage(e.getMessage()); //获取用户插入错误信息
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage(e.getMessage()); //用户不存在错误
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage(e.getMessage()); //用户密码错误异常
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage(e.getMessage()); //用户密码更新异常
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage(e.getMessage());//空文件异常
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage(e.getMessage()); //文件大小异常
        } else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMessage(e.getMessage()); //文件状态异常
        } else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage(e.getMessage()); //文件类型异常
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage(e.getMessage()); //文件上传IO异常
        } else if (e instanceof ProductNotFoundException) { //商品未找到异常
            result.setState(4006);
        }

        return result;
    }

    //获取session对象中的UID
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    //获取用户名称
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
