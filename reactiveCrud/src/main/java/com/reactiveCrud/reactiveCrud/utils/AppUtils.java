package com.reactiveCrud.reactiveCrud.utils;

import com.reactiveCrud.reactiveCrud.dto.ProductDto;
import com.reactiveCrud.reactiveCrud.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }
    public static Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }


}
