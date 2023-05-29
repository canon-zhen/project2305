package com.caizhen.generator.generators;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.function.Consumer;

/**
 * @author caizhen
 * Date 2023/2/16 17:56
 * Description: store-cloud
 */
public class CodeGenerator {

        public static void main(String[] args) {

            /**
             *
             * TODO 注意修改 TODO处的信息
             */
            runCodeGenerator();

        }

        /**
         * 快速生成
         */
        private static void runCodeGenerator() {
            FastAutoGenerator.create(getDataSourceConfig())
                    .globalConfig(getGlobalConfig())
                    .packageConfig(getPackageConfig())
                    .strategyConfig(getStrategyConfig())
                    /**
                     * 默认的是Velocity引擎模板。也可使用其他的。注意引入模板引擎依赖
                     */
                    //.templateEngine(new BeetlTemplateEngine())
                    .templateEngine(new FreemarkerTemplateEngine())
                    .execute();

        }



        /**
         * 数据库配置(DataSourceConfig)
         * @return
         */
        private static DataSourceConfig.Builder getDataSourceConfig() {
            /**
             * TODO 数据库配置
             */
            String url = "jdbc:mysql://localhost:3306/project2305_db?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT";
            String username = "root";
            String password = "cz148313";
            DataSourceConfig.Builder builder = new DataSourceConfig.Builder(url, username, password);
            return builder;
        }

        /**
         * 全局配置(GlobalConfig)
         * @return
         */
        private static Consumer<GlobalConfig.Builder> getGlobalConfig() {
            Consumer<GlobalConfig.Builder> consumer = new Consumer<GlobalConfig.Builder>() {
                @Override
                public void accept(GlobalConfig.Builder builder) {
                    builder.fileOverride() // 覆盖已生成文件
                            // 开启 swagger 模式
//                            .enableSwagger()
                            // TODO 设置作者
                            .author("caizhen")
                            // TODO 指定输出目录
                            .outputDir("D:\\softwareData\\ideaProjects\\springbootProject\\project2305\\src\\main\\java")
                            .build();
                }
            };
            return consumer;
        }

        /**
         * 包配置(PackageConfig)
         */
        private static Consumer<PackageConfig.Builder> getPackageConfig() {
            Consumer<PackageConfig.Builder> consumer = new Consumer<PackageConfig.Builder>() {
                @Override
                public void accept(PackageConfig.Builder builder) {
                    builder
                            // TODO 设置父包模块名
                            //.moduleName("sysxxx")
                            // TODO 设置父包名
                            .parent("com.example.project2305")
                            // TODO 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\softwareData\\ideaProjects\\springbootProject\\project2305\\src\\main\\resources\\mapper"))
                            // 设置 Entity 包名
                            .entity("pojo")
                            // 设置 Service 包名
                            .service("service")
                            // 设置 Service Impl 包名
                            .serviceImpl("service.impl")
                            // 设置 Controller 包名
                            .controller("controller")
                            .build();
                }
            };
            return consumer;
        }

        /**
         * 策略配置(StrategyConfig)
         * @return
         */
        private static Consumer<StrategyConfig.Builder> getStrategyConfig(){
            Consumer<StrategyConfig.Builder> consumer = new Consumer<StrategyConfig.Builder>() {
                @Override
                public void accept(StrategyConfig.Builder builder) {

                    /**
                     * 策略配置
                     */
                    builder .addTablePrefix("cai_", "c_") // TODO 设置过滤表前缀
                            // TODO 设置需要生成的表
                            .addInclude("cai_user")
                            .addInclude("cai_file")
                            .build();

                    /**
                     * Entity 策略配置
                     */
                    builder.entityBuilder()
                            // 开启 lombok 模型
                            .enableLombok()
                            // 全局主键类型
                            .idType(IdType.AUTO)
                            // TODO 格式化文件名称
                            .formatFileName("%s")
                            .build();

                    /**
                     * Mapper 策略配置
                     */
                    builder.mapperBuilder()
                            // 启用 BaseResultMap 生成
                            .enableBaseResultMap()
                            // 启用 BaseColumnList
                            .enableBaseColumnList()
                            .build();

                    /**
                     * Service 策略配置
                     */
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .build();

                    /**
                     * Controller 策略配置
                     */
                    builder.controllerBuilder()
                            // 开启生成@RestController 控制器
                            .enableRestStyle()
                            // 转换文件名称
                            .formatFileName("%sController")
                            .build();
                }
            };
            return consumer;
        }

    }
