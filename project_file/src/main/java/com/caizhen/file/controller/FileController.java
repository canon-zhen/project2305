package com.caizhen.file.controller;

import com.caizhen.config.annotation.MyLog;
import com.caizhen.pojo.entity.File;
import com.caizhen.pojo.entity.Result;
import com.caizhen.mvc.result.ResultFactory;
import com.caizhen.mvc.service.impl.FileServiceImpl;
import com.caizhen.commonutils.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileServiceImpl fileService ;

    /**
     * 测试 nacos  提供回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        System.out.println("file服务");
        return "echo-file: " + string;
    }



    /**
     * 文件上传   测试头像上传  --- 保存本地路径
     * @param file  文件对象
     * @return  resule
     * @throws IOException
     */
    @PostMapping("/fileupdate")
    @MyLog(title = "文件模块", content = "文件上传操作")
    public Result fileUpload(File file) throws IOException {
        //  被上传的文件 - 真实开发中我们通过html获取被上传的路径
        String path = "C:/Users/86184/Pictures/wbb1.jpg";
        // 读取被上传的文件
        FileInputStream fileInputStream = new FileInputStream(path);
        String path2 = "D:/softwareData/ideaProjects/white_jotter/img"; // 读入到的文件位置
        String replace = UUID.randomUUID().toString().replace("-", ""); // UUID防止名字重复
        String realPath = path2 + "/" + replace + ".jpg"; // 真实被放去的位置
        FileOutputStream fileOutputStream = new FileOutputStream(realPath);
        // 数据库进行插入的操作
        file.setFilePath(realPath);
        file.setFileId("1234");
        file.setFileName("图片-wbb1");
        file.setCreateTime(LocalDateTime.now());

        boolean b = fileService.save(file);
        if(b){
            //缓冲区
            byte[] bytes = new byte[1024];
            int count = 0;
            while ((count = fileInputStream.read(bytes)) != -1) {
                // 上传到我们的 upload路径
                fileOutputStream.write(bytes, 0, count);
            }
            fileInputStream.close();  // 关闭文件
            fileOutputStream.close();
            String message = "上传文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(file,message);
        }else {
            fileInputStream.close();  // 关闭文件
            fileOutputStream.close();
            String message = "添加一个文件失败";
            return ResultFactory.buildFailResult(message);
        }



    }
    /**
     * 图片上传  -- 保存URL路径
     * @param file 头像文件
     * @return 返回 URL 路径
     * @throws Exception
     */
    @PostMapping("/photo")
    public String photoUpLoad(MultipartFile file) throws Exception{
        String folder = "D:/softwareData/ideaProjects/white_jotter/img";
        java.io.File imageFolder = new java.io.File(folder);
        System.out.println(file);
        System.out.println(imageFolder);
        java.io.File f = new java.io.File(imageFolder, StringUtil.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }try {
            file.transferTo(f);
            String imgURL = "http://localhost:8975/file/userPhoto" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }




    /**
     * 添加一个文件
     * @param file
     * @return
     */
    @PostMapping("/add")
    @MyLog(title = "文件模块", content = "添加文件操作")
    public Result addFile(File file){
//        return fileService.save(file);
//        boolean b = userService.removeBatchByIds(ids);
        boolean b = fileService.save(file);
        if(b){
            String message = "添加一个文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(file,message);
        }else {
            String message = "添加一个文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }


    /**
     * 删除文件
     * @param file
     * @return
     */
    @DeleteMapping("/del")
    @MyLog(title = "文件模块", content = "删除文件操作")
    public Result delete(@RequestBody File file){

        boolean b = fileService.removeById(file);
        if(b){
            String message = "删除文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(file,message);
        }else {
            String message = "删除文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }
    /**
     * 批量删除文件
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    @MyLog(title = "文件模块", content = "批量删除文件操作")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        boolean b = fileService.removeBatchByIds(ids);
        if(b){
            String message = "批量删除文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(null,message);
        }else {
            String message = "批量删除文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }

    /**
     * 根据ID更新文件
     * @param file
     * @return
     */
    @PostMapping("/update")
    @MyLog(title = "文件模块", content = "根据ID更新文件操作")
    public Result updateById(@RequestBody File file){
        boolean b = fileService.updateById(file);
        if(b){
            String message = "根据ID更新文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(file,message);
        }else {
            String message = "根据ID更新文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }


    /**
     * 查询所有文件
     * @return
     */
    @GetMapping("/list")
    @MyLog(title = "文件模块", content = "查询所有文件操作")
    public Result userListAll(){
//        return fileService.list();
        List<File> fileList = fileService.list();
        if(fileList != null){
            String message = "查询所有文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(fileList,message);
        }else {
            String message = "查询所有文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }
    /**
     * 根据ID查询文件
     * @return
     */
    @GetMapping("/list/{sno}")
    @MyLog(title = "文件模块", content = "根据ID查询文件操作")
    public Result userListById(@PathVariable("sno") Integer sno){
//        return fileService.getById(sno);
        File fileById = fileService.getById(sno);
        if(fileById != null){
            String message = "根据ID查询文件成功";
            //返回成功码，同时返回该对象
            return ResultFactory.buildSuccessResult(fileById,message);
        }else {
            String message = "根据ID查询文件失败";
            return ResultFactory.buildFailResult(message);
        }
    }




}
