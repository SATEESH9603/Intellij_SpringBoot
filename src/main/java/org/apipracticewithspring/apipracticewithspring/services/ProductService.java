package org.apipracticewithspring.apipracticewithspring.services;

import org.apipracticewithspring.apipracticewithspring.FakeStoreProductDTO.FakeStoreProductDto;
import org.apipracticewithspring.apipracticewithspring.models.Product;

public interface ProductService {
    Product getSingleProduct(Long id);
    Product addNewproduct(FakeStoreProductDto product);

}
