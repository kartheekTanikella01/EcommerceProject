package com.example.EcommerceBackend.Service;

import com.example.EcommerceBackend.Entity.Category;
import com.example.EcommerceBackend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id){
       return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("category with this ID not found"));

    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

    public Category UpdateCategory(int id,Category category){
        Optional<Category> existingCategory=categoryRepository.findById(id);

        // Check if category exists
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();

            // Update the properties of the category
            updatedCategory.setCategoryName(category.getCategoryName());
            updatedCategory.setDescription(category.getDescription());

            // Save the updated category back to the repository
            return categoryRepository.save(updatedCategory);
        } else {
            // Handle the case when the category is not found
            throw new RuntimeException("Category not found with id: " + id);
        }
    }




}
