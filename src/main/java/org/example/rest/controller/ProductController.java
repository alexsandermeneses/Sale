package org.example.rest.controller;

import org.example.domain.entity.Product;
import org.example.domain.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Update(@PathVariable Integer id,
                       @RequestBody Product product){
        productRepository.findById(id).map(productExist -> {
            product.setId(productExist.getId());
            productRepository.save(product);
            return productExist;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));
    }

    @GetMapping
    public List<Product> findAll(Product filter){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);
        return productRepository.findAll(example);

    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id){
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
                );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        productRepository.findById(id)
                .map(productExist ->{
                    productRepository.delete(productExist);
                    return productExist;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));
    }

}
