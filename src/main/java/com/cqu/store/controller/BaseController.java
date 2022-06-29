package com.cqu.store.controller;

import com.cqu.store.controller.ex.*;
import com.cqu.store.service.ex.*;
import com.cqu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//基类控制层
public class BaseController {

    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage(e.getMessage()); //?????????????????
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage(e.getMessage()); //?????????????????
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage(e.getMessage()); //????????????
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage(e.getMessage()); //?????????????
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage(e.getMessage()); //?????????????
        } else if (e instanceof AddressCountLimitException) {
            result.setState(5004);
            result.setMessage(e.getMessage()); //???????????????????
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage(e.getMessage());//???????
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage(e.getMessage()); //?????С??
        } else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMessage(e.getMessage()); //???????
        } else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage(e.getMessage()); //?????????
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage(e.getMessage()); //??????IO??
        } else if (e instanceof ProductNotFoundException) { //商品不存在
            result.setState(4006);
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
        }else if(e instanceof FavoriteNotFoundException)
        {
            result.setState(4008);
        }else if(e instanceof HistoryNotFound)
        {
            result.setState(4009);
        }else if(e instanceof OrderNotFound)
        {
            result.setState(4010);
        }


        return result;
    }

    //???session?????е?UID
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    //??????????
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
