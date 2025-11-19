package com.wyb.web.senimar_demo.config;

import com.wyb.web.senimar_demo.entity.Account;
import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.entity.Product;
import com.wyb.web.senimar_demo.services.CategoryService;
import com.wyb.web.senimar_demo.services.ProductService;
import com.wyb.web.senimar_demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    //call service to create table and import data


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (categoryService.getAllCategories().isEmpty()) {
            // Initialize categories
            String[] categories = {
                    "Fruits", "Vegetables", "Meat", "Fish", "Dairy",
                    "Bakery", "Drinks", "Sweets", "Other"
            };

            for (String cat : categories) {
                categoryService.saveCategory(new Category(cat));
            }
        }
        // Insert Accounts only if they don't exist
        if (!accountService.usernameExists("admin")) {
            Account admin = new Account(
                    "admin",
                    "admin@nyan.cat",
                    passwordEncoder.encode("123"),
                    "ROLE_ADMIN",
                    "123, Albany Street"
            );
            accountService.saveAccount(admin);
        }

        if (!accountService.usernameExists("lisa")) {
            Account lisa = new Account(
                    "lisa",
                    "lisa@gmail.com",
                    passwordEncoder.encode("765"),
                    "ROLE_NORMAL",
                    "765, 5th Avenue"
            );
            accountService.saveAccount(lisa);
        }

        // Insert Products only if they don't exist
        if (productService.getAllProducts().isEmpty()) {
            // Get categories by ID
            Category fruitsCategory = categoryService.getCategoryById(1L).orElse(null);
            Category otherCategory = categoryService.getCategoryById(9L).orElse(null);

            if (fruitsCategory != null) {
                Product p1 = new Product();
                p1.setDescription("Fresh and juicy");
                p1.setImage("https://freepngimg.com/save/9557-apple-fruit-transparent/744x744");
                p1.setName("Apple");
                p1.setPrice(3);
                p1.setQuantity(40);
                p1.setWeight(76);
                p1.setCategory(fruitsCategory);
                productService.saveProduct(p1);
            }

            if (otherCategory != null) {
                Product p2 = new Product();
                p2.setDescription("Woops! There goes the eggs...");
                p2.setImage("https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png");
                p2.setName("Cracked Eggs");
                p2.setPrice(1);
                p2.setQuantity(90);
                p2.setWeight(43);
                p2.setCategory(otherCategory);
                productService.saveProduct(p2);
            }
        }
    }
}
