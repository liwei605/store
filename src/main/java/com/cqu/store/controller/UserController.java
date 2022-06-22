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
@RequestMapping("users")//��������
public class UserController extends BaseController {
    //�����ļ��ϴ������ֵ
    public static final int AVATAR_MAX_SIZE = 1024 * 1024 * 1024;
    //�����ļ��ϴ�������
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

    @RequestMapping("reg")//��������
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")//��������
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password); //���data�����ᱻ�����cookies����secceion

        //��session��������ݰ�
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());


        //��ȡsession�а󶨵�����
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")//��������
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changerPassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")//��������
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("logout")//账号注销
    public JsonResult<Void> logout(HttpSession session){
        session.invalidate();
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // ��HttpSession�����л�ȡuid��username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // ����ҵ�����ִ���޸��û�����
        userService.changeInfo(uid, username, user);
        // ��Ӧ�ɹ�
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvator(HttpSession session, MultipartFile file) {
        // �ж��ϴ����ļ��Ƿ�Ϊ��
        if (file.isEmpty()) {
            // �ǣ��׳��쳣
            throw new FileEmptyException("�ϴ���ͷ���ļ�������Ϊ��");
        }
        // �ж��ϴ����ļ���С�Ƿ񳬳�����ֵ
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()�������ļ��Ĵ�С�����ֽ�Ϊ��λ
            // �ǣ��׳��쳣
            throw new FileSizeException("�������ϴ�����" + (AVATAR_MAX_SIZE / 1024) + "KB��ͷ���ļ�");
        }
        // �ж��ϴ����ļ������Ƿ񳬳�����
        String contentType = file.getContentType();
        // boolean contains(Object o)����ǰ�б�������ĳԪ�أ����ؽ��Ϊtrue������������Ԫ�أ����ؽ��Ϊfalse
        if (!AVATAR_TYPES.contains(contentType)) {
            // �ǣ��׳��쳣
            throw new FileTypeException("�ļ����Ͳ�֧��");
        }

        // ��ȡ��ǰ��Ŀ�ľ��Դ���·��
        String parent = session.getServletContext().getRealPath("upload");
        System.err.println(parent);
        // ����ͷ���ļ����ļ���
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // �����ͷ���ļ����ļ���
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;


        // �����ļ����󣬱�ʾ�����ͷ���ļ�
        File dest = new File(dir, filename);
        // ִ�б���ͷ���ļ�
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // �׳��쳣
            throw new FileStateException("�ļ�״̬�쳣�������ļ��ѱ��ƶ���ɾ��");
        } catch (IOException e) {
            // �׳��쳣
            throw new FileUploadIOException("�ϴ��ļ�ʱ��д�������Ժ����³���");
        }

        // ͷ��·��
        String avatar = "/upload/" + filename;
        // ��Session�л�ȡuid��username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // ��ͷ��д�뵽���ݿ���
        userService.changeAvatar(uid, username, avatar);


        // ���سɹ�ͷ��·��
        return new JsonResult<String>(OK, avatar);
    }
}
