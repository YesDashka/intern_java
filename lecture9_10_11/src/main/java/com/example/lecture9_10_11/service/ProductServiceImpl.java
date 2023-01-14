package com.example.lecture9_10_11.service;

import com.example.lecture9_10_11.dto.ProductNameCategoryDto;
import com.example.lecture9_10_11.entity.Category;
import com.example.lecture9_10_11.entity.Product;
import com.example.lecture9_10_11.exception.ResourceNotFoundException;
import com.example.lecture9_10_11.repository.CategoryRepository;
import com.example.lecture9_10_11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product, long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    product.setCategory(category);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }

    @Override
    public Product updateProduct(long productId, long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        productRepository.update(product.getName(), product.getPrice(), category, productId);
        return product;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public void deleteProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(product);
    }

    @Override
    public List<Product> _search(ProductNameCategoryDto dto, Pageable pageable) {
        List<Product> productList = productRepository.findByNameAndCategoryId(dto.getName(), dto.getCategoryId(), pageable);
        if(productList.isEmpty()){
            throw new ResourceNotFoundException("Product", "name", "categoryId");
        }
        return productList;
    }

}
