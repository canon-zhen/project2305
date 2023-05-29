package com.caizhen.mvc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caizhen.pojo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
