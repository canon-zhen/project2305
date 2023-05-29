package com.caizhen.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caizhen.mvc.mapper.LogMapper;
import com.caizhen.pojo.entity.Log;
import com.caizhen.mvc.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author caizhen
 * @since 2021-12-21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
