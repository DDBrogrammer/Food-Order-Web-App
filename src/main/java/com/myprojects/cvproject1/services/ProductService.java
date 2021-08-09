package com.myprojects.cvproject1.services;

import com.myprojects.cvproject1.entities.Product;
import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.jpa.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ProductService {
    @Autowired
    private ProductJPA productJPA;
    @Autowired
    private  UploadService uploadService;
    public boolean save(Product product, MultipartFile uploadImage) {

        try {
            //upload ảnh
            if(uploadImage!=null){
                //tiến hành upload
                String uploadPath =  uploadService.upload(uploadImage);
                if (uploadPath != null) {
                    product.setPicture(uploadPath);
                }else{
                    return false;
                }
            }
            productJPA.save(product);
        } catch (Exception e) {
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

    public Product getProductById(long id) {
        return productJPA.findById(id).get();
    }

    public Page<Product> getPageProduct(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return productJPA.findAll(pageable);
    }
}
