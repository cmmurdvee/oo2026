package ee.murdvee.veebipood.controller;

import ee.murdvee.veebipood.entity.Category;
import ee.murdvee.veebipood.entity.Product;
import ee.murdvee.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    // TODO: categoies/admin --> kuvatakse koik kategooriad
    // categories --> kuvatakse vaid kategooriad, kellel on moni toode

    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id); // kustutan
        return categoryRepository.findAll(); // uuenenud seis
    }

    @PostMapping("categories")
    public List<Category> addCategory(@RequestBody Category category){
        categoryRepository.save(category); // siin salvestab
        return categoryRepository.findAll(); // siin on uuenenud seis
    }

}
