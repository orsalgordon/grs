package com.grs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @JsonProperty("product_id")
    public int id;
    public String name;
    public String description;
    public String price;
    public String image;
    public String brand;
}
