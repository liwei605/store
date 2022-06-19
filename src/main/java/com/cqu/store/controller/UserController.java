package com.cqu.store.controller;

import com.cqu.store.controller.ex.*;
import com.cqu.store.entity.User;
import com.cqu.store.util.JsonResult;
import com.cqu.store.controller.ex.*;
import com.cqu.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController //@controller + @ResponseBody
@RequestMapping("users")//请求拦截
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")//请求拦截
    public JsonResult<Void> reg(User user)
    {
        userService.reg(user);
        return new JsonResult<Void> (OK);
    }

    @RequestMapping("login")//请求拦截
    public  JsonResult<User > login(String username, String password, HttpSession session)
    {
        User data= userService.login(username,password); //这个data将来会被存放在cookies或者secceion

        //向session中完成数据绑定
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());


        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_password")//请求拦截
    public  JsonResult<Void > changePassword(String oldPassword, String newPassword,HttpSession session)
    {
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changerPassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")//请求拦截
    public JsonResult<User> getByUid(HttpSession session)
    {
        User data= userService.getByUid(getuidFromSession(session));
        return  new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        userService.changeInfo(uid, username, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    //限制文件上传的最大值
    public static final int AVATAR_MAX_SIZE=1024*1024*1024;
    //限制文件上传的类型
    public static final List<String> AVATAR_TYPES=new ArrayList<>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvator(HttpSession session, MultipartFile file)
    {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("文件类型不支持");
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.err.println(parent);
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;


        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeAvatar(uid, username, avatar);


        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }
}
