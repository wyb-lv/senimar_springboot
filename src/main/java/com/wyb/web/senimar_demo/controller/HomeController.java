package com.wyb.web.senimar_demo.controller;

import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.entity.Product;
import com.wyb.web.senimar_demo.services.CategoryService;
import com.wyb.web.senimar_demo.services.ProductService;
import com.wyb.web.senimar_demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    /**
     * Home page
     */
    @GetMapping("/admin")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
