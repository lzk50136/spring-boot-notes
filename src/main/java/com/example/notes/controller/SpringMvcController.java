package com.example.notes.controller;

import com.example.notes.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lzk
 */
@Controller
@RequestMapping(value = "/spring-mvc")
public class SpringMvcController {
    /*
    1、@RequestMapping
    @RequestMapping是一个用来处理请求地址映射的注解（将请求映射到对应的控制器方法中），可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
    @RequestMapping请求路径映射，如果标注在某个Controller的类级别上，则表明访问此类路径下的方法都要加上其配置的路径；最常用是标注在方法上，表明哪个具体的方法来接受处理某次请求。
     */

    /*
    由于SpringMvcController类加了value="/spring-mvc"的@RequestMapping的注解，所以相关路径都要加上"/spring-mvc"，即请求的url为：http://localhost:8080/spring-boot-notes/spring-mvc/test
     */

    /*
    @RequestMapping的属性
    @RequestMapping有value，method，consumes，produces，params，headers六个属性，下面我们把它分成六类进行说明。
     */

    /*
     * value：指定请求的实际url
     * value值前后是否有“/”对请求的路径没有影响，即value="test" 、"/test"、"/test/"其效果是一样的。
     */

    /**
     * (1)普通的具体值。如前面的value="/test"。
     */
    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

    /**
     * (2)含某变量的一类值。
     * 路径中的userId可以当变量，@PathVariable注解可提取路径中的变量值。
     */
    @RequestMapping(value = "/test2/{userId}")
    public Integer test2(@PathVariable("userId") Integer userId) {
        return userId;
    }

    /**
     * (3)ant风格
     * 注解@RequestMapping(value="/get/id?")：可匹配“/get/id1”或“/get/ida”，但不匹配“/get/id”或“/get/idaa”;
     * 注解@RequestMapping(value="/get/id*")：可匹配“/get/idabc”或“/get/id”，但不匹配“/get/idabc/abc”;
     * 注解@RequestMapping(value="/get/id/*")：可匹配“/get/id/abc”，但不匹配“/get/idabc”;
     * 注解@RequestMapping(value="/get/id/**\{id}")：可匹配“/get/id/abc/abc/123”或“/get/id/123”，也就是Ant风格和URI模板变量风格可混用。
     */
    @RequestMapping(value = "/test3/id/**/{id}")
    public Integer test3(@PathVariable("id") Integer id) {
        return id;
    }

    /**
     * (4)含正则表达式的一类值
     * 可以通过@PathVariable注解提取路径中的变量(username)
     */
    @RequestMapping(value = "/test4/{username:^[A-Za-z]+$}")
    public String test4(@PathVariable("username") String username) {
        return username;
    }

    /**
     * (5)或关系
     */
    @RequestMapping(value = {"/test5", "/test6"})
    public String test5() {
        return "test5";
    }
    /*
    value = {"/test5", "/test6"}表示指定多个映射url
     */

    /**
     * method：指定请求的method类型，GET、POST、PUT、DELETE等；
     */
    @RequestMapping(value = "/test7", method = RequestMethod.POST)
    public String test7() {
        return "test7";
    }
    /*
    method = {RequestMethod.GET, RequestMethod.POST}表示指定多种method类型
     */

    /**
     * params：指定request中必须包含某些指定的params值，才让该方法处理。
     */
    @RequestMapping(value = "/test8", params = "my_params!=8")
    public String test8() {
        return "test8";
    }
    /*
    RequestMapping请求参数可以使用简单的表达式
    1. my_params!=8 表示请求头中此参数不能等于8
    2. !=my_params 表示请求头中不能含有此参数
    3. params={"my_params", "my_params!=8"}表示请求参数中包含多个参数
     */

    /**
     * headers：指定request中必须包含某些指定的header值，才能让该方法处理请求。
     */
    @RequestMapping(value = "/test9", headers = "my_headers!=9")
    public String test9() {
        return "test9";
    }
    /*
    RequestMapping请求头可以使用简单的表达式
    1. my_headers!=9 表示请求头中此参数不能等于9
    2. !=my_headers 表示请求头中不能含有此参数
    3. headers = {"my_headers", "my_headers!=9"}表示请求头中包含多个参数
     */

    /**
     * consumes：指定处理请求的提交内容类型（Content-Type），例如application/json,text/html。
     */
    @RequestMapping(value = "/test10", consumes = "application/json")
    public String test10() {
        return "test10";
    }

    /**
     * produces：指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回。
     */
    @RequestMapping(value = "/test11", produces = "application/json")
    public String test11() {
        return "test11";
    }

    /*
    2、@RequestParam
    @RequestParam绑定单个请求参数值
     */

    /**
     * 注解@RequestParam用于将请求参数区数据映射到功能处理方法的参数上。
     * 请求中包含username参数（如/test10?username=tom），则自动传入。
     * 注解@RequestParam有以下三个参数：
     * value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；
     * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将抛出异常；
     * defaultValue：默认值，表示如果请求中没有同名参数时的默认值，设置该参数时，自动将required设为false。
     */
    @RequestMapping(value = "/test10")
    public String test10(@RequestParam(value = "username", defaultValue = "tom", required = false) String username) {
        return username;
    }
    /*
    表示请求中可以没有名字为username的参数，如果没有默认为null，此处需要注意如下几点：
    原子类型：必须有值，否则抛出异常，如果允许空值请使用包装类代替。
    Boolean包装类型：默认Boolean.FALSE，其他引用类型默认为null。
     */

    /**
     * 如果请求中有多个同名的应该如何接收呢？如给用户授权时，可能授予多个权限，首先看下如下代码：
     */
    @RequestMapping(value = "/test11")
    public String test11(@RequestParam(value = "role") String roles) {
        return roles;
    }

    /**
     * 如果请求参数类似于url?role=admin&rule=user，则实际roles参数入参的数据为“admin,user”，即多个数据之间使用“，”分割；我们应该使用如下方式来接收多个请求参数：
     */
    @RequestMapping(value = "/test12")
    public String test12(@RequestParam(value = "role") String[] roles) {
        return Arrays.toString(roles);
    }

    /**
     * 或者
     */
    @RequestMapping(value = "/test13")
    public String test13(@RequestParam(value = "role") List<String> roles) {
        return Arrays.toString(roles.toArray());
    }

    /*
    3、@PathVariable
    @PathVariable绑定URI模板变量值
     */

    /**
     * 注解@PathVariable用于将请求URL中的模板变量映射到功能处理方法的参数上。
     */
    @RequestMapping(value = "/test14/{userId}")
    public Integer test14(@PathVariable(value = "userId") Integer userId) {
        return userId;
    }

    /*
    6、@RequestBody
    @RequestBody将HTTP请求正文插入方法中,使用适合的HttpMessageConverter将请求体写入某个对象。
    作用：
    i) 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
    ii) 再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。
    使用时机：
    A) GET、POST方式提时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理）；
    multipart/form-data, 不能处理（即使用@RequestBody不能处理这种格式的数据）；
    其他格式， 必须（其他格式包括application/json, application/xml等。这些格式的数据，必须使用@RequestBody来处理）；
    B) PUT方式提交时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 必须；
    multipart/form-data, 不能处理；
    其他格式， 必须；
    说明：request的body部分的数据编码格式由header部分的Content-Type指定；
     */

    /**
     * 将请求中的data写入String对象中
     */
    @RequestMapping(value = "/test15", method = RequestMethod.POST)
    public User test15(@RequestBody User user) {
        return user;
    }

    /*
    7、@ResponseBody
    @ResponseBody表示该方法的返回结果直接写入HTTP response body中。一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@ResponseBody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。比如异步获取json数据，加上@ResponseBody后，会直接返回json数据。
    @ResponseBody将内容或对象作为HTTP响应正文返回，并调用适合HttpMessageConverter的Adapter转换对象，写入输出流。
    作用：
    该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
    使用时机：
    返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；
     */

    /**
     * 不会被解析为跳转路径，而是直接写入HTTP response body中
     */
    @RequestMapping(value = "/test16", method = RequestMethod.POST)
    @ResponseBody
    public String test16(@RequestBody String string) {
        return string;
    }

    /**
     * 8、@RequestHeader
     * 注解@RequestHeader，可以把Request请求header部分的值绑定到方法的参数上。
     */
    @RequestMapping("/test17")
    public String test17(@RequestHeader("Accept-Encoding") String encoding) {
        return encoding;
    }

    /**
     * 9、@CookieValue
     * 注解@CookieValue，可以把Request header中关于cookie的值绑定到方法的参数上。
     */
    @RequestMapping("/test18")
    public String test18(@CookieValue("JSESSIONID") String cookieValue) {
        return cookieValue;
    }

}
