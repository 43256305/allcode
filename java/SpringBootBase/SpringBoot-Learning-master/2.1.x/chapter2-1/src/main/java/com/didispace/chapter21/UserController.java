package com.didispace.chapter21;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {

    // 创建线程安全的Map，模拟users信息的存储
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    /**
     * 处理"/users/"的GET请求，用来获取用户列表
     *
     * @return
     */
    @GetMapping("/")
    public List<User> getUserList() {
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    //@GetMapping 组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写
    @GetMapping("/home")
    public ModelAndView getHome(){
        //Restcontroller时，所有的方法返回的都是json，要返回视图如下
        ModelAndView mv=new ModelAndView("home");
        return mv;
    }

    /**
     * 处理"/users/"的POST请求，用来创建User
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public String postUser(@RequestBody User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        //@RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。然而在ajax请求往往传的都是Json对象，用 JSON.stringify(data)的方式就能将对象变成字符串
        //同时ajax请求的时候也要指定dataType: "json",contentType:"application/json" 这样就可以轻易的将一个对象或者List传到Java端，使用@RequestBody即可绑定对象或者List.
        //RequestBody自动把此字符串转换成User对象（只会转换User中有getter的属性）
        //另外如果@requestBody 和 @requestParam 同时使用的话，@requestParam的参数需要拼接到URL上才可以拿到。
        users.put(user.getId(), user);
        return "success";
    }

    /**
     *
     *访问时的url为：users/1
     * @param id
     * @return
     */
    //用{}括起来表示变量  返回值不提倡用String，一般用对象（User，List<User>等）
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        //@PathVariable处理的参数是url中的参数，如：/users/1
        //@RequestParam("id") : /users?id=1  当/users（即没有id输入）时，会报错，可以设置默认值
        //@RequestParam(value="id",required = false,defaultValue = "1") Integer id    传递的值都需要包装类
        return users.get(id);
    }


    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}