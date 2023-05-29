package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import io.swagger.annotations.Api;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* ${table.comment!} 前端控制器
* @author QIUQIU&LL (个人博客:https://www.cnblogs.com/qbbit)
* @date ${date}
* @tags 我爱的人在很远的地方, 我必须更加努力
*/
@Api(tags = "${entity}控制器")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
}
</#if>