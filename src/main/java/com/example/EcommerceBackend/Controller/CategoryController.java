package com.example.EcommerceBackend.Controller;

import com.example.EcommerceBackend.Common.APIResponse;
import com.example.EcommerceBackend.Entity.Category;
import com.example.EcommerceBackend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public void createCategory(@RequestBody Category category){
         categoryService.createCategory(category);
    }
    @GetMapping
    public List<Category> allCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category categoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping ("/update/{categoryId}")
    public ResponseEntity<APIResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category ) {
        System.out.println("category id " + categoryId);
        Category category1= categoryService.getCategoryById(categoryId);
        categoryService.UpdateCategory(categoryId, category);
        return new ResponseEntity<APIResponse>(new APIResponse(true, "category has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        categoryService.deleteCategory(id);

    }



}
