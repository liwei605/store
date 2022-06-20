package com.cqu.store.mapper;

import com.cqu.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 处理用户数据操作的持久层接口
 */
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);


    /**
     * 根据用户名查询用户数据
     * @Author supreme
     * @Date 2022/6/20 8:41
     * @param username
     * @return User
     */

    User findByUsername(String username);


    /**
     * 根据uid更新用户的密码
     * @Author supreme
     * @Date 2022/6/20 8:42
     * @param uid           用户的id
     * @param password      新密码
     * @param modifiedUser  最后执行者
     * @param modifiedTime  最后修改时间
     * @return Integer
     */

    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    /*
    Integer updatePasswordByUid(
            Integer uid,
            String password,
            String modifiedUser,
            Date modifiedTime); */

    /**
     * 根据用户id查询用户数据
     *
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User findByUid(Integer uid);


    /**
     * 根据uid更新用户资料
     * @Author supreme
     * @Date 2022/6/20 8:44
     * @param user
     * @return Integer 受影响的行数
     */

    Integer updateInfoByUid(User user);

    /**
     * 根据uid更新用户头像
     * @Author supreme
     * @Date 2022/6/20 8:46
     * @param uid           用户uid
     * @param avatar        新头像路经
     * @param modifiedUser  修改人
     * @param modifiedTime  修改时间
     * @return Integer
     */

    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
