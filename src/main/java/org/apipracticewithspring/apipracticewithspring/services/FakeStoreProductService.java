package org.apipracticewithspring.apipracticewithspring.services;

import org.apipracticewithspring.apipracticewithspring.FakeStoreProductDTO.FakeStoreProductDto;
import org.apipracticewithspring.apipracticewithspring.models.Category;
import org.apipracticewithspring.apipracticewithspring.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
    private FakeStoreProductDto ProducttoFakestoreproduct(Product product){
        FakeStoreProductDto product1 = new FakeStoreProductDto();
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory().getName());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImageUrl());
        return product1;
    }
    @Override
    public Product getSingleProduct(Long id) {
      FakeStoreProductDto productDto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        return FakestoreproductdtoToProduct(productDto);
    }

    @Override
    public Product addNewproduct(Product product) {
        /*FakeStoreProductDto product1 = new FakeStoreProductDto();
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory().getName());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImageUrl());*/

        FakeStoreProductDto productDto =restTemplate.postForObject("https://fakestoreapi.com/products",ProducttoFakestoreproduct(product),FakeStoreProductDto.class);
        return FakestoreproductdtoToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] getAllProductsList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> ans = new ArrayList<>();
        for(FakeStoreProductDto gapl : getAllProductsList){
            ans.add(FakestoreproductdtoToProduct(gapl));
        }
        return ans;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto productDto =restTemplate.patchForObject("https://fakestoreapi.com/products/"+id,ProducttoFakestoreproduct(product),FakeStoreProductDto.class);
        return FakestoreproductdtoToProduct(productDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        /*FakeStoreProductDto replaceproductDto = new FakeStoreProductDto();
        replaceproductDto.setTitle(product.getTitle());
        replaceproductDto.setImage(product.getImageUrl());
        replaceproductDto.setPrice(product.getPrice());
        replaceproductDto.setDescription(product.getDescription());*/
        RequestCallback requestCallback = restTemplate.httpEntityCallback(ProducttoFakestoreproduct(product),FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto replaceResponse = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT,requestCallback,responseExtractor);

        return FakestoreproductdtoToProduct(replaceResponse);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

}
