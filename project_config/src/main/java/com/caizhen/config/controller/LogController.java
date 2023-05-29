package com.caizhen.config.controller;

import com.caizhen.pojo.entity.Log;
import com.caizhen.pojo.entity.Result;
import com.caizhen.mvc.result.ResultFactory;
import com.caizhen.mvc.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *
 * @author caizhen
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    public LogServiceImpl logService;

    /**
     * 保存和修改操作公用此方法
     * @param log 前端传递来的实体数据
     */
    @PostMapping(value="/save")
    public Result save(@RequestBody Log log){
        if(log.getId()!=null){
            logService.updateById(log);
        }else{
            logService.save(log);
        }
        String message = "日志保存成功";
        return ResultFactory.buildSuccessResult(log,message);
    }

    /**
     * 根据ID删除指定对象信息
     * @param id 需要删除日志的id
     */
    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable("id") Long id){
        Log logById = logService.getById(id);
        logService.removeById(id);
        String message = "日志删除成功";
        return ResultFactory.buildSuccessResult(logById,message);
    }

    /**
     * 根据ID查询对象详情信息
     * @param id 查询日志的id
     * @return result
     */
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable("id")Long id) {
        Log logById = logService.getById(id);
        String message = "根据ID查询日志成功";
        return ResultFactory.buildSuccessResult(logById,message);
    }


    /**
     * 查看所有对象数据（不分页）
     */
    @GetMapping(value = "/list")
    public Result list(){
        List<Log> logList = logService.list();
        String message = "查询所有日志成功";
        return ResultFactory.buildSuccessResult(logList,message);
    }


//    /**
//     * 分页查询数据
//     * @param query 查询对象
//     * @return PageList 分页对象
//     */
//    @PostMapping(value = "/pagelist")
//    public JSONResult pageList(@RequestBody LogQuery query)
//    {
//        Page<Log> page = new Page<Log>(query.getPage(),query.getRows());
//        page = LogService.selectPage(page);
//        return JSONResult.success(new PageList<Log>(page.getTotal(), page.getRecords()));
//    }
}
