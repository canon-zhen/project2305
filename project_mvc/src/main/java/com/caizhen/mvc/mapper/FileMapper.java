package com.caizhen.mvc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caizhen.pojo.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@Repository
public interface FileMapper extends BaseMapper<File> {
    /**
     * 插入文件
     * @param entity
     * @return
     */
    @Override
    int insert(File entity);

    /**
     * 删除文件
     * @param entity
     * @return
     */
    @Override
    int deleteById(File entity);

    /**
     * 更新文件
     * @param entity
     * @return
     */
    @Override
    int updateById(File entity);

    /**
     * 查文件
     * @param id
     * @return
     */
    @Override
    File selectById(Serializable id);
}
