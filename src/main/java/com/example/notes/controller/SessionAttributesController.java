package com.example.notes.controller;

import com.example.notes.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 4、@SessionAttributes
 * 注解@SessionAttributes:
 * 该注解用来绑定HttpSession中的attribute对象的值，便于在方法中的参数里使用。
 * 该注解有value、types两个属性，可以通过名字和类型指定要使用的attribute 对象；
 *
 * @author Lzk
 */
@Controller
@RequestMapping("/editPet.do")
@SessionAttributes("pet")
public class SessionAttributesController {

    /**
     * 5、@ModelAttribute
     * 注解@ModelAttribute
     * 该注解有两个用法，一个是用于方法上，一个是用于参数上；
     * 用于方法上时：通常用来在处理@RequestMapping之前，为请求绑定需要从后台查询的model；
     */
    @ModelAttribute
    public User addAccount(@RequestParam String number) {
        return new User();
    }

    /**
     * 6、@ModelAttribute
     * 注解@ModelAttribute
     * 该注解有两个用法，一个是用于方法上，一个是用于参数上；
     * 用于参数上时：用来通过名称对应，把相应名称的值绑定到注解的参数bean上；要绑定的值来源于：
     * A） @SessionAttributes 启用的attribute 对象上；
     * B） @ModelAttribute 用于方法上时指定的model对象；
     * C） 上述两种情况都没有时，new一个需要绑定的bean对象，然后把request中按名称对应的方式把值绑定到bean中。
     */
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute User pet, @PathVariable String ownerId, @PathVariable String petId) {
        return "";
    }

    /*
    补充讲解：
    问题： 在不给定注解的情况下，参数是怎样绑定的？
    通过分析AnnotationMethodHandlerAdapter和RequestMappingHandlerAdapter的源代码发现，方法的参数在不给定参数的情况下：
    若要绑定的对象时简单类型：  调用@RequestParam来处理的。
    若要绑定的对象时复杂类型：  调用@ModelAttribute来处理的。
    这里的简单类型指java的原始类型(boolean, int 等)、原始类型对象（Boolean, Int等）、String、Date等ConversionService里可以直接String转换成目标对象的类型；
     */

}
