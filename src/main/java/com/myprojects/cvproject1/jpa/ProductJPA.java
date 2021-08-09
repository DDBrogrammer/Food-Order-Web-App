package com.myprojects.cvproject1.jpa;

import com.myprojects.cvproject1.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductJPA extends PagingAndSortingRepository<Product,Long> {
}
