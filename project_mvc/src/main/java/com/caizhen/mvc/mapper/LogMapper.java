package com.caizhen.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caizhen.pojo.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author caizhen
 * @since 2023/5/18
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

}
