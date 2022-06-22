package com.cqu.store.controller;

import com.cqu.store.controller.ex.*;
import com.cqu.store.entity.User;
import com.cqu.store.service.IUserService;
import com.cqu.store.util.JsonResult;
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
@RequestMapping("users")//????????
public class UserController extends BaseController {
    //????????????????
    public static final int AVATAR_MAX_SIZE = 1024 * 1024 * 1024;
    //????????????????
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/gif");
    }

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")//????????
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")//????????
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password); //???data???????????cookies????secceion

        //??session??????????
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());


        //???session?§Ñ??????
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")//????????
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changerPassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")//????????
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("logout")//ÕËºÅ×¢Ïú
    public JsonResult<Void> logout(HttpSession session){
        session.invalidate();
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // ??HttpSession?????§Ý??uid??username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // ???????????????????????
        userService.changeInfo(uid, username, user);
        // ??????
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvator(HttpSession session, MultipartFile file) {
        // ?§Ø???????????????
        if (file.isEmpty()) {
            // ????????
            throw new FileEmptyException("avatar is not exixst!");
        }
        // ?§Ø???????????§³?????????
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()????????????§³??????????¦Ë
            // ????????
            throw new FileSizeException("file limits " + (AVATAR_MAX_SIZE / 1024) + "KB !");
        }
        // ?§Ø?????????????????????
        String contentType = file.getContentType();
        // boolean contains(Object o)??????§Ò???????????????????true???????????????????????false
        if (!AVATAR_TYPES.contains(contentType)) {
            // ????????
            throw new FileTypeException("wrong file type!");
        }

        // ?????????????????¡¤??
        String parent = session.getServletContext().getRealPath("upload");
        System.err.println(parent);
        // ?????????????????
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // ??????????????????
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;


        // ????????????????????????
        File dest = new File(dir, filename);
        // ??§Ò?????????
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // ?????
            throw new FileStateException("wrong file state!");
        } catch (IOException e) {
            // ?????
            throw new FileUploadIOException("file IO failed!");
        }

        // ???¡¤??
        String avatar = "/upload/" + filename;
        // ??Session?§Ý??uid??username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // ?????§Õ?????????
        userService.changeAvatar(uid, username, avatar);


        // ?????????¡¤??
        return new JsonResult<String>(OK, avatar);
    }
}
