package com.rimunandabootcamp.restApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ProductsDto {

    @Data
    public static class Save{
        @NotEmpty(message = "tidak boleh kosong")
        @NotNull(message = "enggak boleh null")
        private String name;

        @NotNull(message = "tidak boleh kosong ")
        @Min(value = 1)
        private Integer categoryId;
        @Min(value = 0)
        private String stok;
    }

    @Data
    public static class Update{
        @NotEmpty(message = "tidak boleh kosong")
        @NotNull(message = "enggak boleh null")
        private String name;

        @NotNull(message = "tidak boleh kosong ")
        @Min(value = 1)
        private Integer categoryId;
        @Min(value = 0,message = "minimal data diisi dimulai dengan 0 ")
        private String stok;
    }
}
