package com.reactiveCrud.reactiveCrud.controller;

import com.reactiveCrud.reactiveCrud.dto.ProductDto;
import com.reactiveCrud.reactiveCrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/saveProduct")
    public Mono<ProductDto> saveProduct(@RequestBody  Mono<ProductDto> productDtoMono) {
        return  productService.saveProduct(productDtoMono);
    }

    @GetMapping("/")
    public Flux<ProductDto> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/range")
    public Flux<ProductDto> getRangedProducts(@RequestParam("min") double min ,@RequestParam("max") double max){
        return productService.getProductInRange(min,max);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable int id){
       return productService.updateProduct(productDtoMono ,id);
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

}
