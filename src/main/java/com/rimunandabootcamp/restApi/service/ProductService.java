package com.rimunandabootcamp.restApi.service;

import com.rimunandabootcamp.restApi.dao.ProductDao;
import com.rimunandabootcamp.restApi.dto.ProductsDto;
import com.rimunandabootcamp.restApi.entity.Products;
import com.rimunandabootcamp.restApi.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final CategoryService category;

    public void save(ProductsDto.Save data){
        category.findById(data.getCategoryId());
        this.dao.save(data);
    }
    public void update(Integer id, ProductsDto.Update data){
        findById(id);
        category.findById(data.getCategoryId());
        this.dao.update(id, data);
    }
    public List<Products> findAll(){return this.dao.findAll();}
    public Products findById(Integer id){
        return this.dao.findById(id)
                .orElseThrow( () -> new IdNotFoundException(" produk dengan id "+ id + " tidak ditemukan "));
    }
    public void delete(Integer id){findById(id); this.dao.delete(id);}
}
