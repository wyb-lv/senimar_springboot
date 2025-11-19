package com.wyb.web.senimar_demo.controller;

import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Display all categories
     */
    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    /**
     * Add new category
     */
    @PostMapping("/categories")
    public String addCategory(@RequestParam("categoryname") String categoryName,
                              RedirectAttributes redirectAttributes) {
        try {
            // Trim whitespace
            categoryName = categoryName.trim();

            // Validate category name is not empty
            if (categoryName.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Category name cannot be empty!");
                return "redirect:/admin/categories";
            }

            // Check if category already exists
            if (categoryService.categoryExistsByName(categoryName)) {
                redirectAttributes.addFlashAttribute("error", "Category already exists!");
                return "redirect:/admin/categories";
            }

            // Validate pattern: Each word must start with a capital letter, contain only letters
            String pattern = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$";
            if (!categoryName.matches(pattern)) {
                redirectAttributes.addFlashAttribute("error",
                    "Each word must start with a capital letter, contain only letters (Unicode), no numbers or special characters, and no extra whitespace");
                return "redirect:/admin/categories";
            }

            Category category = new Category(categoryName);
            categoryService.saveCategory(category);

            redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add category: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    /**
     * Update category
     */
    @PostMapping("/categories/update")
    public String updateCategory(@RequestParam("categoryid") Long categoryId,
                                 @RequestParam("categoryname") String categoryName,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Trim whitespace
            categoryName = categoryName.trim();

            // Validate category name is not empty
            if (categoryName.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Category name cannot be empty!");
                return "redirect:/admin/categories";
            }

            // Validate pattern: Each word must start with a capital letter, contain only letters
            String pattern = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$";
            if (!categoryName.matches(pattern)) {
                redirectAttributes.addFlashAttribute("error",
                    "Each word must start with a capital letter, contain only letters (Unicode), no numbers or special characters, and no extra whitespace");
                return "redirect:/admin/categories";
            }

            categoryService.updateCategory(categoryId, categoryName);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update category: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    /**
     * Delete category
     */
    @GetMapping("/categories/delete")
    public String deleteCategory(@RequestParam("id") Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete category: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    /**
     * Search categories
     */
    @PostMapping("/categories/search")
    public String searchCategories(@RequestParam("name") String name,
                                   Model model) {
        if (!name.equals("")) {
            List<Category> categories = categoryService.searchCategoriesbyName(name);
            model.addAttribute("categories", categories);
            model.addAttribute("name", name);
        } else {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
        }
        return "categories";
    }
}
