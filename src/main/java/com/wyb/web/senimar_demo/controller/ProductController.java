package com.wyb.web.senimar_demo.controller;

import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.entity.Product;
import com.wyb.web.senimar_demo.services.CategoryService;
import com.wyb.web.senimar_demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Display all products
     */
    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products";
    }

    /**
     * Show add product form
     */
    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "productsAdd";
    }

    /**
     * Add new product
     */
    @PostMapping("/products/add")
    public String addProduct(@RequestParam("name") String name,
                            @RequestParam(value = "description", defaultValue = "no product details") String description,
                            @RequestParam("price") Integer price,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("weight") Integer weight,
                            @RequestParam(value = "productImage", required = false) String image,
                            @RequestParam("categoryid") Long categoryId,
                            RedirectAttributes redirectAttributes) {
        try {
            Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
            if (!categoryOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Category not found!");
                return "redirect:/admin/products/add";
            }

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setWeight(weight);
            // Use default image if not provided
            product.setImage(image != null && !image.isEmpty() ? image : "Product Images/one.jpg");
            product.setCategory(categoryOpt.get());

            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add product: " + e.getMessage());
            return "redirect:/admin/products/add";
        }
        return "redirect:/admin/products";
    }

    /**
     * Show update product form
     */
    @GetMapping("/products/update/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productService.getProductById(id);
        if (!productOpt.isPresent()) {
            return "redirect:/admin/products";
        }

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", productOpt.get());
        model.addAttribute("categories", categories);
        return "productsUpdate";
    }

    /**
     * Update product
     */
    @PostMapping("/products/update")
    public String updateProduct(@RequestParam("id") Long id,
                               @RequestParam("name") String name,
                               @RequestParam(value = "description", defaultValue = "no product details") String description,
                               @RequestParam("price") Integer price,
                               @RequestParam("quantity") Integer quantity,
                               @RequestParam("weight") Integer weight,
                               @RequestParam(value = "productImage", required = false) String image,
                               @RequestParam("categoryid") Long categoryId,
                               RedirectAttributes redirectAttributes) {
        try {
            Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
            if (!categoryOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Category not found!");
                return "redirect:/admin/products/update/" + id;
            }

            // If image is null or empty, keep the existing image
            Optional<Product> existingProduct = productService.getProductById(id);
            String finalImage = (image == null || image.isEmpty()) && existingProduct.isPresent()
                    ? existingProduct.get().getImage()
                    : image;

            productService.updateProduct(id, name, description, price, quantity, weight, finalImage, categoryOpt.get());
            redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update product: " + e.getMessage());
            return "redirect:/admin/products/update/" + id;
        }
        return "redirect:/admin/products";
    }

    /**
     * Delete product
     */
    @GetMapping("/products/delete")
    public String deleteProduct(@RequestParam("id") Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete product: " + e.getMessage());
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/products/search")
    public String searchProducts(@RequestParam(value = "name",required = false, defaultValue = "") String name,
                                 Model model) {
        if (!name.equals("")){
            List<Product> products = productService.searchProductbyName(name);
            model.addAttribute("products", products);
            model.addAttribute("name", name);
        } else {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
        }
        return "products";
    }
}
