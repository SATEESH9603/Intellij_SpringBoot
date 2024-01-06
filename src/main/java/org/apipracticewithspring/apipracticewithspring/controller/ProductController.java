package org.apipracticewithspring.apipracticewithspring.controller;

import org.apipracticewithspring.apipracticewithspring.FakeStoreProductDTO.FakeStoreProductDto;
import org.apipracticewithspring.apipracticewithspring.models.Category;
import org.apipracticewithspring.apipracticewithspring.models.Product;
import org.apipracticewithspring.apipracticewithspring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping ("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping()
    public List<Product> getAllproducts(){
        List<Product> getAllprolist = new ArrayList<Product>();
        return getAllprolist;
    }

    @GetMapping("/{id}")
    public Product getsingleproduct(@PathVariable("id") Long id){
        //return new Product();
        return productService.getSingleProduct(id);
    }
    @PostMapping()
    public Product addNewProduct(@RequestBody String product) throws JSONException {
        FakeStoreProductDto product1 = new FakeStoreProductDto();
        //product1.setTitle("A new product."+product.getTitle());
        JSONObject jo = new JSONObject(product);
        product1.setTitle(jo.getString("title"));
        product1.setPrice(jo.getDouble("price"));
        product1.setCategory(jo.getString("category"));
        //product1.setCategory(new Category());
        //product1.getCategory().setName(jo.getString("category"));
        product1.setDescription(jo.getString("description"));
        product1.setImage(jo.getString("image"));

        return productService.addNewproduct(product1);

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable("id") Long id){

    }
}
