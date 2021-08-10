package com.myprojects.cvproject1.controllers.admin;
import com.myprojects.cvproject1.entities.Category;
import com.myprojects.cvproject1.entities.Product;
import com.myprojects.cvproject1.services.CategoryService;
import com.myprojects.cvproject1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/admin/product")
public class ProductController implements ICRUD<Product> {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @Override
    @GetMapping("/add")
    public String add(Model model) {
        Product product=new Product();
        model.addAttribute("product",product);
        model.addAttribute("categories",categoryService.getAll());
        return "admin/product/add";
    }

    @Override
    public String doAdd(Product obj, RedirectAttributes flashSession) {
        return null;
    }



    @Override
    @PostMapping("/do-add")
    public String doAdd(Product product, RedirectAttributes flashSession, @RequestParam(name="img")MultipartFile multipartFile) {
        if (productService.save(product, multipartFile)) {
            flashSession.addFlashAttribute("success", "Add successfully");
        } else {
            flashSession.addFlashAttribute("failed", "Add failed");
        }
        return "redirect:/admin/product/add";
    }

    @Override
    @GetMapping("/list")
    public String list(Model model,@RequestParam(name="page", defaultValue = "0") int page) {
        Page<Product> listProductPage = productService.getPageProduct(page);
        listProductPage.getTotalPages();
        model.addAttribute("listProductPage",listProductPage);
        model.addAttribute("activePage", page);
        return "admin/product/list";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") long id, RedirectAttributes flashSession){
        if (productService.delete(id)) {
            flashSession.addFlashAttribute("success", "Đã xóa sản phẩm  ");
        } else {
            flashSession.addFlashAttribute("failed", "Xóa sản phẩm thất bại");
        }
        return "redirect:/admin/product/list";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("categories",categoryService.getAll());
        return "admin/product/edit";
    }

    @Override
    public String doEdit(Product product, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(Product product, RedirectAttributes flashSession,@RequestParam(name="img") MultipartFile multipartFile) {
         if(productService.save(product,multipartFile)){
             flashSession.addFlashAttribute("success", "Add successfully");
         } else {
             flashSession.addFlashAttribute("failed", "Add failed");
         }
        return "redirect:/admin/product/list";
    }
}

