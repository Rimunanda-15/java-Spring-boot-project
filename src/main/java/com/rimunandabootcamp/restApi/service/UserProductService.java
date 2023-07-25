package com.rimunandabootcamp.restApi.service;

import com.rimunandabootcamp.restApi.dao.UserProductDao;
import com.rimunandabootcamp.restApi.dto.UserProductDto;
import com.rimunandabootcamp.restApi.entity.UserProduct;
import com.rimunandabootcamp.restApi.entity.Users;
import com.rimunandabootcamp.restApi.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao dao;
    private final UserService user;
    private final ProductService product;
    public void save(UserProductDto.Save data ){
        user.findById(data.getUserId());
        product.findById(data.getProductId());
        this.dao.save(data);
    }
    public void update(Integer id, UserProductDto.Update data){
        findById(id);
        user.findById(data.getUserId());
        product.findById(data.getProductId());
        this.dao.update(id,data);
    }
    public List<UserProduct> findAll(){
        return this.dao.findAll();
    }
    public UserProduct findById(Integer id){
        return this.dao.findById(id)
                .orElseThrow( () -> new IdNotFoundException("data dengan id"+ id + "tidak ditemukan"));
    }
    public void delete(Integer id){
        findById(id);
        this.dao.delete(id);
    }
}
