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
  /*  public ListResult getUserList(int page) {
        ListResult listResult = new ListResult();
        listResult.setListUser(userJPA.findAll(PageRequest.of(page - 1, 10)));
        listResult.setActivePage(page);
        double totalPage =  Math.ceil((double) userJPA.count()/10);
        listResult.setTotalPage(totalPage);
        return listResult;
    }*/

    // Class mẫu để lấy số phần tử  theo từng trang

  /*  public ListResult getPageUserList(int itemsInPage,int page){
        ListResult listResult=new ListResult();
        int totalPage=(int) userJPA.count()/itemsInPage;
        listResult.setTotalPage(totalPage);
        listResult.setActivePage(page);
        Pageable pageable= (Pageable) PageRequest.of(page,itemsInPage);
        Page<User> listUser = userJPA.findAll(pageable);
        listResult.setListUser(listUser);
        return  listResult;
    }*/

}



