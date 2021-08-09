package com.myprojects.cvproject1.controllers.admin;

import com.myprojects.cvproject1.entities.Category;
import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.services.CategoryService;
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
@RequestMapping("/admin/category")
public class CategoryController implements ICRUD<Category>{
    @Autowired
    private CategoryService categoryService;


    @Override
    @GetMapping("/add")
    public String add(Model model) {
        Category category=new Category();
        model.addAttribute("category",category);
        return "admin/category/add";
    }

    @Override
    @PostMapping ("/do-add")
    public String doAdd(Category category, RedirectAttributes flashSession) {
        if (categoryService.save(category)) {
            flashSession.addFlashAttribute("success", "Đã thêm danh mục ");
        } else {
            flashSession.addFlashAttribute("failed", "Thêm danh mục thất bại");
        }
        return "redirect:/admin/category/add";

    }

    @Override
    public String doAdd(Category obj, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }

    @Override
    @GetMapping("/list")
    public String list(Model model,@RequestParam(name="page", defaultValue = "0") int page) {
        Page<Category> listCategoryPage = categoryService.getPageCategory(page);

        System.out.println(listCategoryPage.stream().iterator());
        listCategoryPage.getTotalPages();
        model.addAttribute("listCategoryPage",listCategoryPage);
        model.addAttribute("activePage", page);
        return "admin/category/list";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") long id, RedirectAttributes flashSession){
        if (categoryService.delete(id)) {
            flashSession.addFlashAttribute("success", "Đã xóa người dùng ");
        } else {
            flashSession.addFlashAttribute("failed", "Xóa người dùng thất bại");
        }
        return "redirect:/admin/category/list";
    }

    @Override
    @GetMapping ("/edit")
    public String edit(Model model, long id) {
        Category category= categoryService.getCategoryById(id);
        model.addAttribute("category",category);
        return "admin/category/edit";
    }

    @Override
    @PostMapping ("/do-edit")
    public String doEdit(Category category, RedirectAttributes flashSession) {
        if (categoryService.save(category)) {
            flashSession.addFlashAttribute("success", "Đã xóa người dùng ");
        } else {
            flashSession.addFlashAttribute("failed", "Xóa người dùng thất bại");
        }
        return "redirect:/admin/category/list";
    }

    @Override
    public String doEdit(Category obj, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }
}
