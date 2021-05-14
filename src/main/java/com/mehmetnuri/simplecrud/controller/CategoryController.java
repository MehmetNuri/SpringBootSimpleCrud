package com.mehmetnuri.simplecrud.controller;

import com.mehmetnuri.simplecrud.exception.CategoryAlreadyExistsException;
import com.mehmetnuri.simplecrud.exception.ResourceNotFoundException;
import com.mehmetnuri.simplecrud.model.Category;
import com.mehmetnuri.simplecrud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getAll(Pageable pageable) {
        return ResponseEntity.ok(categoryRepository.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent()) {
            throw  new ResourceNotFoundException( "Category is not found");
        } else {
            return ResponseEntity.ok(optionalCategory.get());
        }
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isPresent()) {
            throw  new CategoryAlreadyExistsException( "Category is already exist");
        }
        Category savedCategory = categoryRepository.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCategory.getId()).toUri();

        return ResponseEntity.created(location).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw  new ResourceNotFoundException( "Category is not found");
        } else {
            category.setId(optionalCategory.get().getId());
            categoryRepository.save(category);
            return ResponseEntity.ok().body(category);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent()) {
            throw  new ResourceNotFoundException( "Category is not found");
        } else {
            categoryRepository.delete(optionalCategory.get());
            return ResponseEntity.noContent().build();
        }
    }
}
