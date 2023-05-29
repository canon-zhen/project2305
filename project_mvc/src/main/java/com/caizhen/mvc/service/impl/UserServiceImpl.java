package com.caizhen.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caizhen.mvc.mapper.UserMapper;
import com.caizhen.pojo.entity.User;
import com.caizhen.mvc.service.UserService;
import com.caizhen.commonutils.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 是否存在这个用户
     * @param userId 用户id
     * @return 返回布尔值
     */
    public boolean isExistUserId(String userId){
        //创建一个QueryWrapper的对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过QueryWrapper设置条件
        //第一个参数是字段的名称 ， 第二个参数是设置的值
        wrapper.eq("userId",userId);
        //查询出的不为空 返回 true
        return userMapper.selectOne(wrapper) != null;
    }

    /**
     * 根据 userid 和 username 查询用户
     * @param userId 用户id
     * @param username 用户名
     * @return user对象
     */
    public User findUserByUserIdAndUsername(String userId, String username){
        System.out.println(userId+username);
        //创建一个QueryWrapper的对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过QueryWrapper设置条件
        //ge gt le lt
        //第一个参数是字段的名称 ， 第二个参数是设置的值
        wrapper.eq("user_id",userId).eq("user_name",username);
        return userMapper.selectOne(wrapper);
    }








}
