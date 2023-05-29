package com.caizhen.feign.client;

import com.caizhen.config.annotation.MyLog;
import com.caizhen.pojo.entity.Result;
import com.caizhen.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author canonzhen
 * @name project2305
 * @date 2023/5/26  15:27
 */
@Component
@FeignClient(name = "service-api-user", path = "/user")
public interface UserApiFeignService {

    /**
     * 测试 nacos  提供回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) ;

    /**
     * 登录模块
     * @param user
     * @return result
     */
    @GetMapping("/login")
    @MyLog(title = "用户模块", content = "登录")
    public Result login( User user);

    /**
     * 注册
     * @param user 注册返回的用户
     * @return 返回result
     */
    @PostMapping("/register")
    @MyLog(title = "用户模块", content = "注册")
    public Result register(@RequestBody User user);

    /**
     * 进行注销操作，实质是删除redis和token中的缓存
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/logout")
    @MyLog(title = "用户模块", content = "注销")
    public Result logout(HttpServletRequest httpServletRequest);

    /**
     * 根据ID删除用户
     * @param sno
     * @return
     */
    @DeleteMapping("/del/{sno}")
    @MyLog(title = "用户模块", content = "根据ID删除用户")
    public Result deleteById(@PathVariable("sno") Integer sno);

    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping("/del")
    @MyLog(title = "用户模块", content = "删除用户")
    public Result delete(@RequestBody User user);
    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    @MyLog(title = "用户模块", content = "批量删除用户")
    public Result deleteByIds(@RequestBody List<Integer> ids);


    /**
     * 根据ID更新用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    @MyLog(title = "用户模块", content = "根据ID更新用户")
    public Result updateById(@RequestBody User user);


    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/list")
    @MyLog(title = "用户模块", content = "查询所有用户")
    public Result userListAll();
    /**
     * 根据ID查询用户
     * @return
     */
    @GetMapping("/list/{sno}")
    @MyLog(title = "用户模块", content = "根据ID查询用户")
    public User userListById(@PathVariable("sno") Integer sno);




    /**
     * 增加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    @MyLog(title = "用户模块", content = "增加用户")
    public Result saveUser(@RequestBody User user);

//    @Test
//    public void testSelect(){
//        User user = userService.findUserByUserIdAndUsername("20192108", "蔡振");
//        System.out.println(user);
//    }

    /**
     * 测试 redis 获取值
     * @param paramMap
     * @return
     */
    @GetMapping("/redisGetName")
    public Map<String,Object> getName(@RequestParam Map<String,Object> paramMap);

    /**
     * 测试redis 设置值
     * @param paramMap
     * @return
     */
    @PostMapping("/redisSetName")
    public Object setName(@RequestParam Map<String,Object> paramMap);


    @GetMapping("/test/getIp")
    public Map<String,String> test(HttpServletRequest request) ;


}
