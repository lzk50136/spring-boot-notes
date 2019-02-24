package com.example.notes.controller;

import com.example.notes.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lzk
 */
@Controller
@RequestMapping(value = "/spring-mvc2")
//(1)将ModelMap中属性名为currUser的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes("currUser")
public class SpringMvcController2 {

    /*
    4、@ModelAttribute
    @ModelAttribute可以应用在方法参数上或方法上，它的作用主要是：
    当注解在方法参数上时会将注解的参数对象添加到Model中；
    当注解在请求处理方法Action上时会将该方法变成一个非请求处理的方法，但其它Action被调用时会首先调用该方法。
     */

    /*
    4.1、@ModelAttribute注解一个方法
    被@ModelAttribute注解的方法表示这个方法的目的是增加一个或多个模型(model)属性。这个方法和被@RequestMapping注解的方法一样也支持@RequestParam参数，但是它不能直接被请求映射。实际上，控制器中的@ModelAttribute方法是在同一控制器中的@RequestMapping方法被调用之前调用的。
    被@ModelAttribute注解的方法用于填充model属性，例如，为下拉菜单填充内容，或检索一个command对象（如，Account），用它来表示一个HTML表单中的数据。
    一个控制器可以有任意数量的@ModelAttribute方法。所有这些方法都在@RequestMapping方法被调用之前调用。
    有两种类型的@ModelAttribute方法。一种是：只加入一个属性，用方法的返回类型隐含表示。另一种是：方法接受一个Model类型的参数，这个model可以加入任意多个model属性。
     */

    /**
     * (1)@ModelAttribute注释void返回值的方法
     */
    @ModelAttribute
    public void populateModel(@RequestParam String abc, Model model) {
        model.addAttribute("attributeName", abc);
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
        return "book/helloWorld";
    }
    /*
    这个例子，在获得请求/helloWorld 后，populateModel方法在helloWorld方法之前先被调用，它把请求参数（/helloWorld?abc=text）加入到一个名为attributeName的model属性中，在它执行后helloWorld被调用，返回视图名helloWorld和model已由@ModelAttribute方法生产好了。
    这个例子中model属性名称和model属性对象由model.addAttribute()实现，不过前提是要在方法中加入一个Model类型的参数。
     */

    /**
     * (2)@ModelAttribute注释返回具体类的方法
     */
    @ModelAttribute
    public User getUserInfo(String id) {
        return null;
    }
    /*
    这种情况，model属性的名称没有指定，它由返回类型隐含表示，如这个方法返回User类型，那么这个model属性的名称是user。
    这个例子中model属性名称有返回对象类型隐含表示，model属性对象就是方法的返回值。它无须要特定的参数。
     */

    /**
     * (3)@ModelAttribute(value="")注释返回具体类的方法
     */
    @ModelAttribute("str")
    public String getParam(@RequestParam String param) {
        return param;
    }

    @RequestMapping(value = "/helloWorld2")
    public String helloWorld2() {
        return "book/helloWorld";
    }
    /*
    这个例子中使用@ModelAttribute注释的value属性，来指定model属性的名称。model属性对象就是方法的返回值。它无须要特定的参数。
     */

    /**
     * (4)@ModelAttribute和@RequestMapping同时注释一个方法
     */
    @RequestMapping(value = "/helloWorld3")
    @ModelAttribute("attributeName")
    public String helloWorld3() {
        return "hi";
    }
    /*
    这时这个方法的返回值并不是表示一个视图名称，而是model属性的值，视图名称由RequestToViewNameTranslator根据请求"/helloWorld"转换为helloWorld。Model属性名称由@ModelAttribute(value=””)指定，相当于在request中封装了key=attributeName，value=hi。
     */

    /*
    4.2 @ModelAttribute注释一个方法的参数
     */

    /**
     * 注解@ModelAttribute注释方法的一个参数表示应从模型model中取得。若在model中未找到，那么这个参数将先被实例化后加入到model中。若在model中找到，则请求参数名称和model属性字段若相匹配就会自动填充。这个机制对于表单提交数据绑定到对象属性上很有效。
     * 当@ModelAttribute注解用于方法参数时，它有了双重功能，即“存/取”。首先，它从模型中取出数据并赋予对应的参数，如果模型中尚不存在，则实例化一个，并存放于模型中；其次，一旦模型中已存在此数据对象，接下来一个很重要的步骤便是将请求参数绑定到此对象上（请求参数名映射对象属性名），这是Spring MVC提供的一个非常便利的机制--数据绑定。
     */
    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String doLogin(@ModelAttribute("baseMember") User member) {
        return "home";
    }
    /*
    上述代码中，如果模型中尚不存在键名为“baseMember”的数据，则首先会调用BaseMember类的默认构造器创建一个对象，如果不存在默认构造器会抛出异常。因此，给实体类提供一个默认构造器是一个好的编程习惯。当请求路径的请求参数或提交的表单与BaseMember的属性名匹配时，将自动将其值绑定到baseMember对象中，非常的便利！这可能是我们使用@ModelAttribute最主要的原因之一。比如：请求路径为http://localhost:8080/spring-web/login.htm?loginName=myLoginName，baseMember对象中的loginName属性的值将被设置为myLoginName。
     */

    /*
    4.3 @ModelAttribute注解的使用场景
    当@ModelAttribute注解用于方法时，与其处于同一个处理类的所有请求方法执行前都会执行一次此方法，这可能并不是我们想要的，因此，我们使用更多的是将其应用在请求方法的参数上，而它的一部分功能与@RequestParam注解是一致的，只不过@RequestParam用于绑定单个参数值，而@ModelAttribute注解可以绑定所有名称匹配的，此外它自动将绑定后的数据添加到模型中，无形中也给我们提供了便利，这也可能是它命名为ModelAttribute的原因。
     */

    /*
    5、SessionAttributes
    在默认情况下，ModelMap中的属性作用域是request级别，也就是说，当本次请求结束后，ModelMap 中的属性将销毁。如果希望在多个请求中共享ModelMap中的属性，必须将其属性转存到session 中，这样 ModelMap 的属性才可以被跨请求访问。
    Spring 允许我们有选择地指定 ModelMap 中的哪些属性需要转存到 session 中，以便下一个请求属对应的 ModelMap 的属性列表中还能访问到这些属性。这一功能是通过类定义处标注 @SessionAttributes 注解来实现的。
     */

    @RequestMapping(value = "/getUser")
    public String getUser(ModelMap model) {
        User user = new User();
        //(2)向ModelMap中添加一个属性
        model.addAttribute("currUser", user);
        return "/book/user";
    }

    @RequestMapping(value = "/getUser1")
    public String getUser1(ModelMap model) {
        User user = (User) model.get("currUser");
        return "book/user1";
    }
    /*
    我们在(2)处添加了一个 ModelMap 属性，其属性名为 currUser，而(1)处通过 @SessionAttributes 注解将 ModelMap 中名为 currUser 的属性放置到 Session 中，所以我们不但可以在 getUser() 请求所对应的 JSP 视图页面中通过 request.getAttribute(“currUser”) 和 session.getAttribute(“currUser”) 获取 user 对象，还可以在下一个请求(getUser1())所对应的 JSP 视图页面中通过 session.getAttribute(“currUser”) 或 session.getAttribute(“currUser”)访问到这个属性。
    这里我们仅将一个 ModelMap 的属性放入 Session 中，其实 @SessionAttributes 允许指定多个属性。你可以通过字符串数组的方式指定多个属性，如 @SessionAttributes({“attr1”,"attr2”})。此外，@SessionAttributes 还可以通过属性类型指定要 session 化的 ModelMap 属性，如 @SessionAttributes(types = User.class)，当然也可以指定多个类，如 @SessionAttributes(types = {User.class,Dept.class})，还可以联合使用属性名和属性类型指定：@SessionAttributes(types = {User.class,Dept.class},value={“attr1”,"attr2”})。
     */

    /**
     * 通过@ModelAttribute绑定
     * 注解@SessionAttributes 是用来在 controller 内部共享 model 属性的。我们可以在需要访问 Session 属性的 controller 上加上 @SessionAttributes，然后在 action 需要的 User 参数上加上 @ModelAttribute，并保证两者的属性名称一致。SpringMVC 就会自动将 @SessionAttributes 定义的属性注入到 ModelMap 对象，在 setup action 的参数列表时，去 ModelMap 中取到这样的对象，再添加到参数列表。只要我们不去调用 SessionStatus 的 setComplete() 方法，这个对象就会一直保留在 Session 中，从而实现 Session 信息的共享。
     */
    @RequestMapping
    public void hello(@ModelAttribute("currentUser") User user) {
        //user.sayHello()
    }
    /*
    @SessionAttributes清除
    @SessionAttributes需要清除时，使用SessionStatus.setComplete();来清除。注意，它只清除@SessionAttributes的session，不会清除HttpSession的数据。故如用户身份验证对象的session一般不用它来实现，还是用session.setAttribute等传统的方式实现。
     */
}
