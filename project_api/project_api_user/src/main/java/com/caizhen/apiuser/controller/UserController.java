package com.caizhen.apiuser.controller;


import com.caizhen.apiuser.feign.TranslateApiFeignService;
import com.caizhen.commonutils.utils.IpEntity;
import com.caizhen.commonutils.utils.IpUtils;
import com.caizhen.commonutils.utils.RedisUtil;
import com.caizhen.commonutils.utils.IpUtil;
import com.caizhen.config.annotation.MyLog;
import com.caizhen.mvc.result.ResultFactory;
import com.caizhen.mvc.service.impl.UserServiceImpl;
import com.caizhen.pojo.entity.Result;
import com.caizhen.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    TranslateApiFeignService translateApiFeignService;

    /**
     * 测试使用openfeign调用translate API接口
     * @return 字符串
     */
    @GetMapping(value = "/trans", produces = MediaType.APPLICATION_JSON_VALUE)
    public String trans(@RequestParam("query") String query,
                        @RequestParam("from") String from, @RequestParam("to") String to){
        System.out.println("api-user 使用openfeign 调用 translate 服务");
        String reduct = translateApiFeignService.query(query,from,to);
        return "trans result  :" + reduct;
    }
    /**
     * 测试 openfeign 调用api-translate 的回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/feign/{string}", method = RequestMethod.GET)
    public String echoFeignTranslate(@PathVariable String string) {
        System.out.println("api-user-->openfeign 调用api-translate 的回显服务");
        String echo_translateApiFeignService = translateApiFeignService.echo(string);
        return "echo-api-user: " + echo_translateApiFeignService;
    }



    @GetMapping("/test")
    public Result consumerTest() {
        String serviceUrl="http://service-api-user";
        Result result = restTemplate.getForObject(serviceUrl + "/user/list", Result.class);
        System.out.println(result.toString()+"------------------------------------****************");
        return result;
    }
    /**
     * 测试 nacos  提供回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        System.out.println("apiuser-->服务");
        return "echo-api-user: " + string;
    }

    /**
     * 登录模块
     * @param user
     * @return result
     */
    @GetMapping("/login")
    @MyLog(title = "用户模块", content = "登录")
    public Result login( User user){
        String userName = user.getUserName();
        String userId = user.getUserId();
        System.out.println(userName + userId + " ---------------------->login  usercontroller");
        User userByUserIdAndUsername = userService.findUserByUserIdAndUsername(user.getUserId(), user.getUserName());
        if (userByUserIdAndUsername != null){
            //存在这个用户,给用户添加个token
            String token = UUID.randomUUID().toString();
            System.out.println(token+"---------------------->login  usercontroller");
            //登录放行   存入redis
            redisUtil.set(token,userName);
            String message = "用户登录成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(userByUserIdAndUsername,message);
        }else {
            String message = "登录失败";
            return ResultFactory.buildFailResult(message);
        }
    }

    /**
     * 注册
     * @param user 注册返回的用户
     * @return 返回result
     */
    @PostMapping("/register")
    @MyLog(title = "用户模块", content = "注册")
    public Result register(@RequestBody User user){
        String userName = user.getUserName();
        String userId = user.getUserId();
        System.out.println(userName + userId + " -------->register  usercontroller");
        String userPhoto = user.getUserPhoto();

        if (userService.isExistUserId(user.getUserId()) ){
            //存在用户userId，注册失败
            String message = "注册用户失败";
            return ResultFactory.buildFailResult(message);
        }else{
            //注册成功
            userService.save(user);
            String message = "注册用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(user,message);
        }
    }

    /**
     * 进行注销操作，实质是删除redis和token中的缓存
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/logout")
    @MyLog(title = "用户模块", content = "注销")
    public Result logout(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        System.out.println(token+"-------------------------------->user/logout");
        //删除缓存
        redisUtil.del(token);
        String message = "注销成功";
        return ResultFactory.buildSuccessResult(message);
    }

    /**
     * 根据ID删除用户
     * @param sno
     * @return
     */
    @DeleteMapping("/del/{sno}")
    @MyLog(title = "用户模块", content = "根据ID删除用户")
    public Result deleteById(@PathVariable("sno") Integer sno){
//        return userService.removeById(sno) ? "删除成功":"删除失败";
        User userbyId = userService.getById(sno);
        boolean b = userService.removeById(sno);
        if(b){
            String message = "根据ID删除用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(userbyId,message);
        }else {
            String message = "根据ID删除用户失败";
            return ResultFactory.buildFailResult(message);
        }
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping("/del")
    @MyLog(title = "用户模块", content = "删除用户")
    public Result delete(@RequestBody User user){
//        return userService.removeById(user) ? "删除成功":"删除失败";
        boolean b = userService.removeById(user);
        if(b){
            String message = "删除用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(user,message);
        }else {
            String message = "删除用户失败";
            return ResultFactory.buildFailResult(message);
        }
    }
    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    @MyLog(title = "用户模块", content = "批量删除用户")
    public Result deleteByIds(@RequestBody List<Integer> ids){
//        return userService.removeBatchByIds(ids);
        boolean b = userService.removeBatchByIds(ids);
        if(b){
            String message = "批量删除用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(null,message);
        }else {
            String message = "批量删除用户失败";
            return ResultFactory.buildFailResult(message);
        }
    }


    /**
     * 根据ID更新用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    @MyLog(title = "用户模块", content = "根据ID更新用户")
    public Result updateById(@RequestBody User user){
//        return userService.updateById(user) ? "更新成功":"更新失败";
        boolean b = userService.updateById(user);
        if(b){
            String message = "根据ID更新用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(user,message);
        }else {
            String message = "根据ID更新用户失败";
            return ResultFactory.buildFailResult(message);
        }
    }


    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/list")
    @MyLog(title = "用户模块", content = "查询所有用户")
    public Result userListAll(){
        List<User> list = userService.list();
        Result result = new Result();
        result.setResult(list);
        return result;
    }
    /**
     * 根据ID查询用户
     * @return
     */
    @GetMapping("/list/{sno}")
    @MyLog(title = "用户模块", content = "根据ID查询用户")
    public User userListById(@PathVariable("sno") Integer sno){
        User userServiceById = userService.getById(sno);
        System.out.println(userServiceById);
        return userServiceById;
    }




    /**
     * 增加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    @MyLog(title = "用户模块", content = "增加用户")
    public Result saveUser(@RequestBody User user){
        boolean b = userService.save(user);
        if(b){
            String message = "添加用户成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(user,message);
        }else {
            String message = "添加用户失败";
            return ResultFactory.buildFailResult(message);
        }
    }

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
    public Map<String,Object> getName(@RequestParam Map<String,Object> paramMap){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("name",redisUtil.get(paramMap.get("name").toString()));
        return resultMap;
    }

    /**
     * 测试redis 设置值
     * @param paramMap
     * @return
     */
    @PostMapping("/redisSetName")
    public Object setName(@RequestParam Map<String,Object> paramMap){
        String nameKey = paramMap.get("nameKey").toString();
        String nameValue = paramMap.get("nameValue").toString();
        System.out.println(nameKey+"------"+nameValue);
        redisUtil.set(nameKey,nameValue);
        return "加入成功";
    }


    @GetMapping("/test/getIp")
    public Map<String,String> test(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("真实ip",request.getHeader("X-Real-IP"));
        map.put("ip列表",request.getHeader("X-Forwarded-For"));
        map.put("转发ip",request.getRemoteAddr());
        String ipAddress = IpUtils.getIpAddr(request);
        String cityInfo = IpUtils.getCityInfo(ipAddress);
        System.out.println("ip工具类获取的ip地址： "+ipAddress);
        System.out.println("ip归属地: "+cityInfo);
        map.put("ip工具类获取的ip地址：",ipAddress);
        return map;
    }



}
