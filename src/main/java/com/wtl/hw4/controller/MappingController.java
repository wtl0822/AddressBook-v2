package com.wtl.hw4.controller;

import com.wtl.hw4.Data.AddressBook;
import com.wtl.hw4.Data.ContactInfo;
import com.wtl.hw4.Data.LoginInfo;
import com.wtl.hw4.models.BookAlters;
import com.wtl.hw4.models.CheckLogin;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MappingController {
    // 请求登陆界面
    @RequestMapping("/login")
    public String login(LoginInfo user, Model model) {
        model.addAttribute("user", user); // 取到数据加入模板，模板将数据交给视图
        return "login"; // view 渲染后返回 login.html
    }

    // 登陆检测界面（post）
    @SneakyThrows // 异常处理
    @PostMapping("/checklogin")
    public String logincheck(LoginInfo user, Model model, HttpServletRequest request) {
        if(true == CheckLogin.Check(user)) {
            user.setMessage("OK");
            // 已登陆
            request.getSession().setAttribute("isLogin", "true");
            return "redirect:/main";
        }
        else { // 密码输入错误
            user.setMessage("用户名或密码错误");
            user.setPassword("");
            return login(user, model);
        }
    }

    // get 方法直接请求，重定向
    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectlogin(HttpServletRequest request) {
        if(null == request.getSession().getAttribute("isLogin"))
            return "redirect:/login";
        else
            return "redirect:/main";
    }

    // 访问主界面
    @SneakyThrows
    @GetMapping("/main")
    public String showMain(HttpServletRequest request) {
        Object flag = request.getSession();
        if (null != flag) { // 从 session 中取出 book
            HttpSession session = request.getSession();
            if (null == session.getAttribute("book")) {
                AddressBook addressBook = new AddressBook();
                session.setAttribute("book", addressBook);
            }
            return "main";
        }
        else
            return "redirect:/login";
    }

    @RequestMapping("/add")
    public String showAdd(ContactInfo con, Model model) {
        model.addAttribute("con", con);
        return "add";
    }

    @PostMapping("/modify")
    public String showAlter(HttpServletRequest request, @ModelAttribute(value="row")Integer row, ContactInfo m, Model model) {
        AddressBook t = (AddressBook)request.getSession().getAttribute("book"); // 获取 session 中的 book 数据
        ContactInfo info = t.getBookinfo().elementAt(row); // 取 row 行的数据
        model.addAttribute("con", info); // 将第 row 行的数据交给 modify.html 渲染
        return "modify";
    }

    // 重定向回
    @GetMapping("/modify")
    public String redirectAlter() {
        return "redirect:/main";
    }

    @GetMapping("/checkmodify")
    public String redirectCheckAlter() {
        return "redirect:/main";
    }

    // 修改联系人信息
    @PostMapping("/checkmodify")
    public String checkAlter(ContactInfo info, HttpServletRequest request) {
        AddressBook t = (AddressBook)request.getSession().getAttribute("book");
        BookAlters.alterElem(t, info); // 使用类方法更新指定元素
        return "redirect:/main"; // 修改后返回 main
    }

    // 删除联系人，get 方法直接返回 main
    @GetMapping("/del")
    public String redirectDel() {
        return "redirect:/main";
    }

    // 删除联系人
    @PostMapping("del")
    public String DeleteContact(@ModelAttribute(value="row")Integer row, HttpServletRequest request) {
        AddressBook t = (AddressBook) request.getSession().getAttribute("book");
        BookAlters.deleteElem(t, row); // 使用类方法删除 row 的数据
        return "redirect:/main";
    }

    // 处理添加的URL 如果不是通过post请求的说明是手动请求的，将跳转回去，否则进行处理
    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "add";
    }

    // 处理添加的URL 如果是通过POST提交的，则进行处理
    @PostMapping("/checkadd")
    public String checkAdd(ContactInfo cont, HttpServletRequest request, Model model) {
        AddressBook t = (AddressBook)request.getSession().getAttribute("book");
        boolean is_valid = BookAlters.checkValidAdd(t, cont);
        if (true == is_valid) {
            t.getBookinfo().addElement(cont);
            return "redirect:/main";
        }
        else {
            cont.setMessage("联系人名称已存在");
            cont.setContactname("");
            return showAdd(cont, model); // 跳回 add
        }
    }
}
