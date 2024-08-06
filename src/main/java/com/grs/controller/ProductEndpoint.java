package com.grs.controller;

import com.grs.model.dto.Product;
import com.grs.model.dto.ProductList;
import com.grs.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/rest/v1/product")
@Tag(name = "Product")
public class ProductEndpoint {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<List<Product>> getProduct(){
        return ResponseEntity.ok(service.getProducts());
    }
}
