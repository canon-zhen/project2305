package com.caizhen.generator.generators;




import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.caizhen.generator.common.BaseController;
import com.caizhen.generator.common.BaseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class CodeGenerator2 {
    public static void main(String[] args) {
        // 获取表名
        List<String> tables = getTableNames();
        // 数据库设置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/project2305_db", "root", "cz148313")
                /**
                 * 全局配置
                 */
                .globalConfig(builder -> {
                    builder.author("caizhen") // 作者
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java") // 指定输出目录 /opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
//                            .enableSwagger() // 开启 swagger 模式  默认值:false
                            .dateType(DateType.TIME_PACK) // 时间策略   DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .commentDate("yyyy-MM-dd"); // 注释日期   默认值: yyyy-MM-dd

                })
                /**
                 * 包配置
                 */
                .packageConfig(builder -> builder.parent("com.example.project2305") // 父包名    默认值:com.baomidou
                        .entity("domain") // Entity 包名  默认值:common
                        .service("service") // Service 包名 默认值:service
                        .serviceImpl("service.impl") // Service Impl 包名    默认值:service.impl
                        .controller("controller") // Controller 包名  默认值:controller
                        .mapper("mapper") // Mapper 包名  默认值:mapper
                        .xml("mapper") // Mapper XML 包名  默认值:mapper.xml
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"))) // xml的路径配置信息
                /**
                 * 策略配置
                 */
                .strategyConfig(builder -> builder.addInclude(tables)// 增加表匹配(内存过滤)	include 与 exclude 只能配置一项
                        .addTablePrefix("cai_", "sys_", "tb_")

                        /**
                         * service 策略配置
                         */
                        .serviceBuilder()
                        .superServiceClass(IService.class)    // 设置 service 接口父类	BaseService.class
                        .superServiceImplClass(ServiceImpl.class)    // 设置 service 实现类父类	BaseServiceImpl.class
                        .formatServiceFileName("%sService") // 格式化 service 接口文件名称
                        .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称

                        /**
                         * 实体策略配置
                         */
                        .entityBuilder()
                        .superClass(BaseEntity.class)    // 设置父类	BaseEntity.class
                        .disableSerialVersionUID()    // 禁用生成 serialVersionUID	默认值:true
                        .enableChainModel()    // 开启链式模型	默认值:false
                        .enableRemoveIsPrefix()    // 开启 Boolean 类型字段移除 is 前缀	默认值:false
                        .enableTableFieldAnnotation()    // 开启生成实体时生成字段注解	默认值:false
                        .versionColumnName("version")    // 乐观锁字段名(数据库)
                        .logicDeleteColumnName("is_deleted")    // 逻辑删除字段名(数据库)
                        .naming(NamingStrategy.underline_to_camel)    // 数据库表映射到实体的命名策略	默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                        .addTableFills(new Column("create_time", FieldFill.INSERT))    // 添加表字段填充
                        .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))    // 添加表字段填充
                        .enableLombok() // 开启 lombok 模型	默认值:false

                        /**
                         * controller 策略配置
                         */
                        .controllerBuilder()
                        .superClass(BaseController.class) // 设置父类	BaseController.class
                        .enableHyphenStyle() // 开启驼峰转连字符	默认值:false
                        .enableRestStyle() // 开启生成@RestController 控制器	默认值:false
                        .formatFileName("%sController")//	格式化文件名称

                        /**
                         * mapper 策略配置
                         */
                        .mapperBuilder()
                        .superClass(BaseMapper.class)    // 设置父类	BaseMapper.class
//                        .mapperAnnotation(Mapper.class)
                        .enableMapperAnnotation()
                        .enableBaseResultMap()    // 启用 BaseResultMap 生成	默认值:false
                        .enableBaseColumnList()    // 启用 BaseColumnList	默认值:false
                        .formatMapperFileName("%sMapper")    // 格式化 mapper 文件名称
                        .formatXmlFileName("%sMapper")    // 格式化 xml 实现类文件名称
                )
                /**
                 * 模板配置
                 */
                .templateConfig(builder -> {
                    // WARN : 使用我们自定义模板 需要注意虽然我们文件是以ftl结尾，但是这里不要加上ftl,不然会报模板引擎找不到文件
                    builder
                            .entity("/templates/myEntity.java") // 自定义entity模板
                            .controller("/templates/myControlle.java") // 自定义controller模板
                            .service("/templates/myService.java") // 自定义service模板
                            .serviceImpl("/templates/myServiceImpl.java") // 自定义servicei.mpl模板
                            .mapper("/templates/myMapper.java") // 自定义mapper模板
//                            .xml("/templates/myMapper.xml"); // 自定义xml模板
                            .xml("/templates/myMapper.xml");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    /**
     * 读取控制台内容,获取用户输入的表名
     */
    public static List<String> getTableNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表名(多个表名以逗号隔开):");
        String tableNames = scanner.nextLine();
        if (StringUtils.isNotBlank(tableNames)) {
            return Arrays.asList(tableNames.split(","));
        }
        throw new MybatisPlusException("请按照格式输入正确的表名！");
    }
}
