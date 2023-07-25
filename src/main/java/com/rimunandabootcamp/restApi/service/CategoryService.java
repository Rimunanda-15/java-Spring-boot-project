package com.rimunandabootcamp.restApi.service;


import com.rimunandabootcamp.restApi.dao.CategoryDao;
import com.rimunandabootcamp.restApi.dto.CategoriesDto;
import com.rimunandabootcamp.restApi.entity.Categories;
import com.rimunandabootcamp.restApi.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao dao ;

    public void save(CategoriesDto.Save data){this.dao.save(data);}

    public void update(Integer id, CategoriesDto.Update data){findById(id); this.dao.update(id,data);}

    public List<Categories> findAll(){
        return this.dao.findAll();
    }
    public Categories findById(Integer id){
        return this.dao.findById(id)
                .orElseThrow( () -> new IdNotFoundException("Kategori dengan id"+ id + "tidak ditemukan"));
    }

    public void delete(Integer id){
        findById(id);
        this.dao.delete(id);
    }
}
