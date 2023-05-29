package com.caizhen.mvc.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.project2305.mapper.FileMapper;
//import com.example.project2305.pojo.File;
//import com.example.project2305.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caizhen.mvc.mapper.FileMapper;
import com.caizhen.pojo.entity.File;
import com.caizhen.mvc.service.FileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {


}
