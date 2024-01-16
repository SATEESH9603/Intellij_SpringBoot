package org.apipracticewithspring.apipracticewithspring.services;

import org.apipracticewithspring.apipracticewithspring.FakeStoreProductDTO.FakeStoreProductDto;
import org.apipracticewithspring.apipracticewithspring.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    Product addNewproduct(Product product);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    boolean deleteProduct(Long id);


}
