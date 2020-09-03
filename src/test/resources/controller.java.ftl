package ${package.Controller};

<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
</#if>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qy.hnbt.data.common.CommonResult;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.List;

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if swagger2>
@Api
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    <#assign service=table.serviceName?substring(1)?uncap_first/>
    @Autowired
    ${table.serviceName} ${service};

    @ApiOperation("get ${entity}")
    @GetMapping("get${entity}")
    public CommonResult get${entity}(Long id){
        CommonResult<${entity}> commonResult;
        ${entity} ${entity?uncap_first} = ${service}.getById(id);
        commonResult = ${entity?uncap_first} == null ? CommonResult.failed() : CommonResult.success(${entity?uncap_first});
        return commonResult;
    }



    @ApiOperation("${entity} list")
    @GetMapping("list${entity}")
    public CommonResult list${entity}(){
        CommonResult<List<${entity}>> commonResult;
        List<${entity}> ${entity?uncap_first}s = ${service}.list();
        commonResult = ${entity?uncap_first}s == null ? CommonResult.failed() : CommonResult.success(${entity?uncap_first}s);
        return commonResult;
    }


    @ApiOperation("save ${entity}")
    @PostMapping("save")
    public CommonResult save${entity}(${entity} ${entity?uncap_first}){
        CommonResult<String> commonResult;
        boolean result = ${service}.save(${entity?uncap_first});
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update ${entity}")
    @PostMapping("update")
    public CommonResult update${entity}(${entity} ${entity?uncap_first}){
        CommonResult<String> commonResult;
        boolean result = ${service}.updateById(${entity?uncap_first});
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete ${entity}")
    @PostMapping("delete")
    public CommonResult delete${entity}(Long id){
        CommonResult<String> commonResult;
        boolean result = ${service}.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page ${entity} list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<${entity}> page = new Page<>(pageNum,pageSize);
        IPage<${entity}> resultPage = ${service}.page(page);
        return CommonResult.success(resultPage);
    }


}

</#if>
