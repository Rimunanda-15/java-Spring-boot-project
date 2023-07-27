package com.rimunandabootcamp.restApi.service;

import com.rimunandabootcamp.restApi.dao.ProductDao;
import com.rimunandabootcamp.restApi.dao.UserProductDao;
import com.rimunandabootcamp.restApi.dto.ProductsDto;
import com.rimunandabootcamp.restApi.dto.UserProductDto;
import com.rimunandabootcamp.restApi.entity.Products;
import com.rimunandabootcamp.restApi.entity.UserProduct;
import com.rimunandabootcamp.restApi.entity.Users;
import com.rimunandabootcamp.restApi.exception.IdNotFoundException;
import com.rimunandabootcamp.restApi.exception.StockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao dao;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserService user;
    private final ProductService product;
    public void save(UserProductDto.Save data ){
        user.findById(data.getUserId());
        product.findById(data.getProductId());
        Products products = product.findById(data.getProductId());
        int quantityToSave = Integer.parseInt(data.getQuantity());
        if (Integer.parseInt(products.getStok()) >= quantityToSave){
            int updatedStock = Integer.parseInt(products.getStok()) - quantityToSave;
            String sisaStok = String.valueOf(updatedStock);
            String query="UPDATE public.product SET stok=:stok WHERE id=:id ;";
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("stok", sisaStok);
            map.addValue("id",products.getId());
            this.jdbcTemplate.update(query,map);
            this.dao.save(data);
        }else {
            throw new StockNotFoundException("stok produk kosong");
        }

    }
    public void update(Integer id, UserProductDto.Update data){
        findById(id);
        user.findById(data.getUserId());
        product.findById(data.getProductId());
        Products products = product.findById(data.getProductId());
        int quantityToSave = Integer.parseInt(data.getQuantity());
        if (Integer.parseInt(products.getStok()) >= quantityToSave){
            int updatedStock = Integer.parseInt(products.getStok()) - quantityToSave;
            String sisaStok = String.valueOf(updatedStock);

            String query="UPDATE public.product SET stok=:stok WHERE id=:id ;";
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("stok", sisaStok);
            map.addValue("id",products.getId());
            this.jdbcTemplate.update(query,map);
            this.dao.update(id,data);
        }else {
            throw new StockNotFoundException("produk stok kosong");
        }

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
