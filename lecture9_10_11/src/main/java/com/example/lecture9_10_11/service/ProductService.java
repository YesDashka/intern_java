package com.example.lecture9_10_11.service;

import com.example.lecture9_10_11.dto.ProductNameCategoryDto;
import com.example.lecture9_10_11.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product, long categoryId);

    Product updateProduct(long productId, long categoryId, Product product);

    Product getProductById(long id);

    void deleteProductById(long id);

    List<Product> _search(ProductNameCategoryDto dto, Pageable pageable);
}
