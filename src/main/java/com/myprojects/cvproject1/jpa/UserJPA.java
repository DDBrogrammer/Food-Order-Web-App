package com.myprojects.cvproject1.jpa;

import com.myprojects.cvproject1.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserJPA extends PagingAndSortingRepository<User,Long> {



}
