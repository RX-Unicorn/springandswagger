package com.test.controller;

import com.test.entity.BlogArticleBeen;
import com.test.entity.JSONResult;
import com.test.entity.PageInfoBeen;
import com.test.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class SwaggerTestController {


    @ApiOperation(value = "获取id,name",notes = "测试获取id,name")
    @RequestMapping(value = "/apitest",method = RequestMethod.GET)
    @ResponseBody
    public String ApiTest(@ApiParam(name = "id",value = "用户id",required = true)@RequestParam int id,
                          @ApiParam(name = "name",value = "用户name") @RequestParam String name) {

        return "ok "+id+" "+name;
    }


    //推荐这种，自由设置类型
    @ApiOperation(value = "获取id1,nam1e",notes = "测试获取id1,name1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id1",value = "用户id1",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "name1",value = "用户name1",paramType = "query",dataType = "string",example = "aa")
    })
    @RequestMapping(value = "/test1",method = RequestMethod.POST)
    @ResponseBody
    public String Test1(int id1,String name1)
    {
        return "success "+id1+" "+name1;
    }


    //传类对象
    @ApiOperation(value = "获取user",notes = "测试获取user")
    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    @ResponseBody
    public User Test2(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user)
    {
        return user;
    }

    //这里使用POST @RequestBody必须使用POST才能接收，这里方便讲解
    @ApiOperation(value = "一个测试API", notes = "第一个测试API")
    @ResponseBody
    @RequestMapping(value = "/test/{path}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogArticleBeen", value = "文档对象", required = true, paramType = "body", dataType = "BlogArticleBeen"),
            @ApiImplicitParam(name = "path", value = "url上的数据", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "query", value = "query类型参数", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "header中的数据", required = true, paramType = "header", dataType = "String")
    })
    public JSONResult test(@RequestBody BlogArticleBeen blogArticleBeen,
                           @PathVariable Long path,
                           String query,
                           @RequestHeader String apiKey,
                           PageInfoBeen pageInfoBeen){
        System.out.println("blogArticleBeen.getLastUpdateTime():"+blogArticleBeen.getLastUpdateTime());
        System.out.println("blogArticleBeen.getSorter():"+blogArticleBeen.getSorter());
        System.out.println("path:"+path);
        System.out.println("query:"+query);
        System.out.println("apiKey:"+apiKey);
        System.out.println("pageInfoBeen.getNowPage():"+pageInfoBeen.getNowPage());
        System.out.println("pageInfoBeen.getPageSize():"+pageInfoBeen.getPageSize());
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMessage("success");
        jsonResult.setMessageCode(null);
        jsonResult.setCode(0);
        jsonResult.setBody(null);
        return jsonResult;
    }
}
