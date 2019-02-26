package com.example.notes.controller;

import org.springframework.web.bind.annotation.*;

/**
 * RestController注解相当于ResponseBody＋Controller合在一起的作用
 * 当我们在Controller上标注了@RestController，这样相当于Controller的所有方法都标注了@ResponseBody
 * 1.如果只是使用RestController注解Controller，则Controller中的方法无法返回jsp，html页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是return里的内容。
 * 2.如果需要返回到指定页面，则需要用Controller配合视图解析器InternalResourceViewResolver才行。如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 *
 * @author Lzk
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /*
    RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
    RequestMapping请求路径映射，如果标注在某个Controller的类级别上，则表明访问此类路径下的方法都要加上其配置的路径，最常用是标注在方法上，表明哪个具体的方法来接受处理某次请求。
    RequestMapping注解有value，method，consumes，produces，params，headers六个属性，下面我们把它分成三类进行说明。
     */

    /**
     * 1、value，method；
     * value：指定请求的实际地址，指定的地址可以是 URI Template 模式；
     * value值前后是否有“/”对请求的路径没有影响，即value="user"、"/user"、"/user/"其效果是一样的。
     * value的uri值为以下五类：
     * A）可以指定为普通的具体值(value="/user")；
     * B) 可以指定为含有某变量的一类值(value="/user/{userId}")；
     * C) 可以指定为含正则表达式的一类值(value="/user/{username:[a-z0-9_]+}")；
     * D) Ant风格
     * 注解@RequestMapping(value="/get/id?")：可匹配“/get/id1”或“/get/ida”，但不匹配“/get/id”或“/get/idaa”;
     * 注解@RequestMapping(value="/get/id*")：可匹配“/get/idabc”或“/get/id”，但不匹配“/get/idabc/abc”;
     * 注解@RequestMapping(value="/get/id/*")：可匹配“/get/id/abc”，但不匹配“/get/idabc”;
     * 注解@RequestMapping(value="/get/id/**\{id}")：可匹配“/get/id/abc/abc/123”或“/get/id/123”，也就是Ant风格和URI模板变量风格可混用。
     * E) 或关系
     * 注解@RequestMapping(value={"/get","/fetch"} )即 /get或/fetch都会映射到该方法上。
     * method：指定请求的method类型， GET、POST、PUT、DELETE等；
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/test2/{userId}", method = RequestMethod.GET)
    public String test2(@PathVariable("userId") String userId) {
        return userId;
    }

    @RequestMapping(value = "/test3/{username:^[A-Za-z]+$}", method = RequestMethod.GET)
    public String test3(@PathVariable("username") String username) {
        return username;
    }

    /**
     * 2、 consumes，produces；
     * consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html；
     * produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
     */
    @RequestMapping(value = "/test4", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public String test4() {
        return "test4";
    }

    /**
     * 3、 params，headers；
     * RequestMapping映射参数和头可以使用简单的表达式
     * 1. Params！=5 表示请求参数中不能等于5
     * 2.！=params  表示请求参数中不能含有此参数
     * 3. params={"username","age!=10"}请求参数中包含多个参数
     * <p>
     * params： 指定request中必须包含某些参数值，才让该方法处理；
     * headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求；
     */
    @RequestMapping(value = {"/test5"}, method = {RequestMethod.POST}, params = {"params", "test!=5"}, headers = {"headers", "test!=5"})
    public String test5() {
        return "test5";
    }

    @RequestMapping(value = {"/test6"}, method = {RequestMethod.POST}, consumes = {"application/json"}, produces = {"application/json"}, params = {"params", "test!=6"}, headers = {"headers", "test!=6"})
    public String test6() {
        return "test6";
    }

    /*
     简介：
     handler method 参数绑定常用的注解,我们根据他们处理的Request的不同内容部分分为四类：（主要讲解常用类型）
     A、处理request uri 部分（这里指uri template中variable，不含queryString部分）的注解：   @PathVariable;
     B、处理request header部分的注解：   @RequestHeader, @CookieValue;
     C、处理request body部分的注解：@RequestParam,  @RequestBody;
     D、处理attribute类型是注解： @SessionAttributes, @ModelAttribute;
     */

    /**
     * 1、@PathVariable
     * 当使用 @RequestMapping URI template 样式映射时，即 someUrl/{paramId} ，这时的paramId可通过 @PathVariable 注解绑定它传过来的值到方法的参数上。
     */
    @RequestMapping("/test7/{userId}")
    public String test7(@PathVariable("userId") String userId) {
        return userId;
    }

    /**
     * 2、@RequestHeader
     * 注解@RequestHeader，可以把Request请求header部分的值绑定到方法的参数上。
     */
    @RequestMapping("/test8")
    public String test8(@RequestHeader("Accept-Encoding") String encoding) {
        return encoding;
    }

    /**
     * 3、@CookieValue
     * 注解@CookieValue，可以把Request header中关于cookie的值绑定到方法的参数上。
     */
    @RequestMapping("/test9")
    public String test9(@CookieValue("JSESSIONID") String cookieValue) {
        return cookieValue;
    }

    /**
     * 4、@RequestParam
     * 注解@RequestParam
     * A）常用来处理简单类型的绑定，通过 Request.getParameter() 获取的String可直接转换为简单类型的情况（ String--> 简单类型的转换操作由ConversionService配置的转换器来完成）；因为使用request.getParameter()方式获取参数，所以可以处理get 方式中queryString的值，也可以处理post方式中 body data的值；
     * B）用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST；
     * C) 该注解有两个属性：value、required；value用来指定要传入值的id名称，required用来指示参数是否必须绑定；
     * 注解@RequestParam用于将请求参数区数据映射到功能处理方法的参数上。
     * 请求中包含username参数（如/requestparam1?username=zhang），则自动传入。
     * 注解@RequestParam有以下三个参数：
     * value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；
     * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将抛出异常；
     * defaultValue：默认值，表示如果请求中没有同名参数时的默认值，设置该参数时，自动将required设为false。
     */
    @RequestMapping(value = "/test10", method = RequestMethod.POST)
    public String test10(@RequestParam("petId") Integer petId) {
        return petId.toString();
    }

    /**
     * 5、@RequestBody
     * 作用：
     * i：该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
     * ii：再把HttpMessageConverter返回的对象数据绑定到controller中方法的参数上。
     * 使用时机：
     * A：GET、POST方式提交时，根据request header Content-Type的值来判断：
     * application/x-www-form-urlencoded，可选（即非必须，因为这种情况的数据@RequestParam，@ModelAttribute也可以处理，当然@RequestBody也能处理）；
     * multipart/form-data，不能处理（即使用@RequestBody不能处理这种格式的数据）；
     * 其他格式，必须（其他格式包括application/json，application/xml等。这些格式的数据，必须使用@RequestBody来处理）；
     * B：PUT方式提交时，根据request header Content-Type的值来判断：
     * application/x-www-form-urlencoded，必须；
     * multipart/form-data，不能处理；
     * 其他格式，必须；
     * 说明：request的body部分的数据编码格式由header部分的Content-Type指定；
     * 该注解常用来处理Content-Type: 不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等；
     * 它是通过使用 HandlerAdapter 配置的 HttpMessageConverters 来解析post data body，然后绑定到相应的bean上的。
     * 因为配置有FormHttpMessageConverter，所以也可以用来处理 application/x-www-form-urlencoded的内容，处理完的结果放在一个MultiValueMap<String, String>里，这种情况在某些特殊需求下使用，详情查看FormHttpMessageConverter api;
     */
    @RequestMapping(value = "/test11", method = RequestMethod.POST)
    public String test11(@RequestBody String body) {
        return body;
    }

    /**
     * 6、@ResponseBody
     * 作用：
     * 该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
     * 使用时机：
     * 返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；
     */
    @RequestMapping(value = "/test12", method = RequestMethod.POST)
    @ResponseBody
    public String test12(@RequestBody String body) {
        return body;
    }

}
