package com.grs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    public int id;
    public String title;
    public String price;
    public String category;
    public String description;
    public String image;
}
