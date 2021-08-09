package com.myprojects.cvproject1.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ICRUD <T> {
    public String add(Model model);

    public String doAdd(T user, RedirectAttributes flashSession);

    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page);


    public String delete(@RequestParam(name = "id") int id, RedirectAttributes flashSession);

    public String edit(Model model, long id);

    public String doEdit(T user, RedirectAttributes flashSession);

}
