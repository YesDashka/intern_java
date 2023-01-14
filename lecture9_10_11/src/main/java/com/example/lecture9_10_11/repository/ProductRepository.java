package com.example.lecture9_10_11.repository;

import com.example.lecture9_10_11.entity.Category;
import com.example.lecture9_10_11.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    List<Product> findByNameAndCategoryId(String name, long categoryId, Pageable pageable);

    @Modifying
    @Query("update products p set p.name = ?1, p.price = ?2, p.category = ?3 where p.id = ?4")
    @Transactional
    void update(String name, float price, Category category, long id);


}
