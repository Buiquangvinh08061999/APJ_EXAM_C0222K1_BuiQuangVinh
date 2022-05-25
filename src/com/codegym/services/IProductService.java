package com.codegym.services;
import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product newProduct);

    void update(Product newProduct);

    void deleteById(int id);

    Product findById(int id);

    boolean existsById(int id);

    List<Product> findAllOrderByPriceASC();

}
