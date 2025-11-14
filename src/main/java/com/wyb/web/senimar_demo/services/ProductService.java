package com.wyb.web.senimar_demo.services;

import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.entity.Product;
import com.wyb.web.senimar_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get product by ID
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Save or update product
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update product
     */
    public Product updateProduct(Long id, String name, String description, Integer price,
                                 Integer quantity, Integer weight, String image, Category category) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (name != null && !name.isEmpty()) {
                product.setName(name);
            }
            if (description != null) {
                product.setDescription(description);
            }
            if (price != null) {
                product.setPrice(price);
            }
            if (quantity != null) {
                product.setQuantity(quantity);
            }
            if (weight != null) {
                product.setWeight(weight);
            }
            if (image != null && !image.isEmpty()) {
                product.setImage(image);
            }
            if (category != null) {
                product.setCategory(category);
            }
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found with id: " + id);
    }

    /**
     * Delete product by ID
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProductbyName(String name) {
        return productRepository.searchProductByNameContainingIgnoreCase(name);
    }
}
