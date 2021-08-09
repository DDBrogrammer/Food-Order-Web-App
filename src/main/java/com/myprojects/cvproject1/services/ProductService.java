package com.myprojects.cvproject1.services;

import com.myprojects.cvproject1.entities.Product;
import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.jpa.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ProductService {
    @Autowired
    private ProductJPA productJPA;

    public boolean save(Product product) {

        try {
            productJPA.save(product);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            productJPA.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Product getUserById(long id) {
        return productJPA.findById(id).get();
    }

    public Page<Product> getPageUser(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return productJPA.findAll(pageable);
    }
}
