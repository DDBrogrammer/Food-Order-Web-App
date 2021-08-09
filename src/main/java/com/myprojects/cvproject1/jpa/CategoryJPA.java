package com.myprojects.cvproject1.jpa;

import com.myprojects.cvproject1.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryJPA extends PagingAndSortingRepository<Category,Long> {
}
