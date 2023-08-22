package com.reactiveCrud.reactiveCrud.service;

import com.reactiveCrud.reactiveCrud.dto.ProductDto;
import com.reactiveCrud.reactiveCrud.repository.ProductRepository;
import com.reactiveCrud.reactiveCrud.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(int id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }
    public Flux<ProductDto> getProductInRange(double min, double max){
         return  productRepository.findByPriceBetween(min ,max);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
         return  productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,int id){
       return productDtoMono.doOnNext(pid->pid.setId(id))
                .map(AppUtils::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);

    }
    public Mono<Void> deleteProduct(int id){

        return productRepository.deleteById(id)
                .onErrorResume(throwable -> {
                    // Log the error for troubleshooting
                    System.err.println("Error deleting product with ID: " + id);
                    throwable.printStackTrace();
                    // Continue the Mono chain with a default response
                    return Mono.empty();
                });

    }
}
