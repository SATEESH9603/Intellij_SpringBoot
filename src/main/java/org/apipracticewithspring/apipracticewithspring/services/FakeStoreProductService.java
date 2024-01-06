package org.apipracticewithspring.apipracticewithspring.services;

import org.apipracticewithspring.apipracticewithspring.FakeStoreProductDTO.FakeStoreProductDto;
import org.apipracticewithspring.apipracticewithspring.models.Category;
import org.apipracticewithspring.apipracticewithspring.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    private Product FakestoreproductdtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product= new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }
    @Override
    public Product getSingleProduct(Long id) {
      FakeStoreProductDto productDto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        return FakestoreproductdtoToProduct(productDto);
    }

    @Override
    public Product addNewproduct(FakeStoreProductDto product) {
        FakeStoreProductDto productDto =restTemplate.postForObject("https://fakestoreapi.com/products",product,FakeStoreProductDto.class);
        return FakestoreproductdtoToProduct(productDto);
    }
}
