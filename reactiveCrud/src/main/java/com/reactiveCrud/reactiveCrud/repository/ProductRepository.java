package com.reactiveCrud.reactiveCrud.repository;

import com.reactiveCrud.reactiveCrud.dto.ProductDto;
import com.reactiveCrud.reactiveCrud.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

    Flux<ProductDto> findByPriceBetween(double min,double max);
}
