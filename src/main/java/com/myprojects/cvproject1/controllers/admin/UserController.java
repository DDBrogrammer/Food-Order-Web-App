package com.myprojects.cvproject1.controllers.admin;

import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.helpers.Helper;
import com.myprojects.cvproject1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
public class UserController implements ICRUD<User> {
    @Autowired
    private UserService userService;
    @Autowired
    private Helper helper;

    @Override
    @GetMapping("/add")
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/user/add";
    }

    @PostMapping("/do-add")
    public String doAdd(User user, RedirectAttributes flashSession) {
        if (userService.save(user)) {
            flashSession.addFlashAttribute("success", "Đã thêm người dùng ");
        } else {
            flashSession.addFlashAttribute("failed", "Thêm người dùng thất bại");
        }
        return "redirect:/admin/user/add";
    }


    @Override
    @GetMapping("/list")
    public String list(Model model,@RequestParam(name="page", defaultValue = "0") int page) {
        Page<User> listUserPage = userService.getPageUser(page);

        System.out.println(listUserPage.stream().iterator());
        listUserPage.getTotalPages();
        model.addAttribute("listUserPage",listUserPage);
        model.addAttribute("activePage", page);
        return "admin/user/list";
    }
    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") long id, RedirectAttributes flashSession){
        if (userService.delete(id)) {
            flashSession.addFlashAttribute("success", "Đã xóa người dùng ");
        } else {
            flashSession.addFlashAttribute("failed", "Xóa người dùng thất bại");
        }
        return "redirect:/admin/user/list";
    }

    @Override
    @GetMapping ("/edit")
    public String edit(Model model, long id) {
       User user= userService.getUserById(id);
       model.addAttribute("user",user);
       return "admin/user/edit";
    }

    @Override
    @PostMapping ("/do-edit")
    public String doEdit(User user, RedirectAttributes flashSession) {
        if (userService.save(user)) {
            flashSession.addFlashAttribute("success", "Đã xóa người dùng ");
        } else {
            flashSession.addFlashAttribute("failed", "Xóa người dùng thất bại");
        }
        return "redirect:/admin/user/list";
    }

    @Override
    public String doEdit(User obj, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String doAdd(User obj, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }
}
