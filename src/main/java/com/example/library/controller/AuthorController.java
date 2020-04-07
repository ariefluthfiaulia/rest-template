package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Author Author) {
        return ResponseEntity.ok(authorService.save(Author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(author.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @Valid @RequestBody Author param) {
        Optional<Author> author = authorService.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        author.get().setName(param.getName());

        return ResponseEntity.ok(authorService.save(author.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!authorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        authorService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
