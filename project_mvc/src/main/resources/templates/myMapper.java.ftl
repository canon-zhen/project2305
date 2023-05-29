package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotationClass??>
import ${mapperAnnotationClass.name};
</#if>
import org.apache.ibatis.annotations.Mapper;

/**
*
* ${table.comment!} Mapper 接口
* @author QIUQIU&LL (个人博客:https://www.cnblogs.com/qbbit)
* @date ${date}
* @tags 我爱的人在很远的地方, 我必须更加努力
*/
<#if mapperAnnotationClass??>
@${mapperAnnotationClass.simpleName}
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
