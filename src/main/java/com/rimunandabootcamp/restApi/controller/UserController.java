package com.rimunandabootcamp.restApi.controller;

import com.rimunandabootcamp.restApi.dto.UsersDto;
import com.rimunandabootcamp.restApi.entity.Users;
import com.rimunandabootcamp.restApi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        List<Users> users = this.service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(
            @PathVariable(name = "id") Integer id
    ){
        Users user = this.service.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody @Valid UsersDto.Save data,
            BindingResult result
            ){
        Map<String,Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String,Object> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.save(data);
        output.put("status", "Berhasil menambah user");
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody UsersDto.Update data
    ){
        try {
            this.service.update(id,data);
            return  ResponseEntity.ok("Data berhasil diubah");
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan "+ id +" tidak ditemukan");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ){
        this.service.delete(id);
        return ResponseEntity.ok("data berhasil dihapus");

    }
}
