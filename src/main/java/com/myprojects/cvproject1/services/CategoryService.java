package com.myprojects.cvproject1.services;


import com.myprojects.cvproject1.entities.Category;
import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.helpers.Helper;
import com.myprojects.cvproject1.jpa.CategoryJPA;
import com.myprojects.cvproject1.jpa.UserJPA;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryService{
    @Autowired
    private CategoryJPA categoryJPA;

    @Autowired
    private Helper helper;


    public boolean save(Category category) {

        try {
            categoryJPA.save(category);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            categoryJPA.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Category getCategoryById(long id) {
        return categoryJPA.findById(id).get();
    }

    public Page<Category> getPageCategory(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return categoryJPA.findAll(pageable);
    }

}



