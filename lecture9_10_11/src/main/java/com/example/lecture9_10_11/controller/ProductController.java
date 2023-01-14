package com.example.lecture9_10_11.controller;

import com.example.lecture9_10_11.AppConstants;
import com.example.lecture9_10_11.dto.ProductNameCategoryDto;
import com.example.lecture9_10_11.entity.Product;
import com.example.lecture9_10_11.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/categories/{categoryId}/products")
    public ResponseEntity<Product> createProduct(
            @Valid @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody Product productRequest){
        Product product = productService.createProduct(productRequest, categoryId);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/categories/{categoryId}/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "id") Long id,
            @RequestBody Product productRequest){
        Product updatedProduct = productService.updateProduct(id, categoryId, productRequest);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(value = "id") Long id){
        productService.deleteProductById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/nameandcategoryid")
    public ResponseEntity<List<Product>> searchByNameAndCategoryId(
            @Valid @RequestBody ProductNameCategoryDto dto,
            @RequestParam(
                    value = "pageNo",
                    defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
                    required = false
            ) int pageNo,
            @RequestParam(
                    value = "pageSize",
                    defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
                    required = false
            ) int pageSize){
        List<Product> products = productService._search(dto, PageRequest.of(pageNo, pageSize));

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
