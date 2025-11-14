package com.wyb.web.senimar_demo.services;

import com.wyb.web.senimar_demo.entity.Category;
import com.wyb.web.senimar_demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get category by ID
     */
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Save or update category
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Update category
     */
    public Category updateCategory(Long id, String name) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            category.setName(name);
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

    /**
     * Delete category by ID
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    /**
     * Search categories by name
     */
    public List<Category> searchCategoriesbyName(String categoryName) {
        return categoryRepository.findByNameContainingIgnoreCase(categoryName);
    }

    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }
}
