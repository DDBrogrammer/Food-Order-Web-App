package com.myprojects.cvproject1.services;

import com.myprojects.cvproject1.entities.User;
import com.myprojects.cvproject1.helpers.Helper;
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
public class UserService{
    @Autowired
    private UserJPA userJPA;

    @Autowired
    private Helper helper;


    public boolean save(User user) {
        user.setPassword(helper.getMD5(user.getPassword()));
        try {
            userJPA.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            userJPA.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getUserById(long id) {
        return userJPA.findById(id).get();
    }

 public Page<User> getPageUser(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return userJPA.findAll(pageable);
 }


}



