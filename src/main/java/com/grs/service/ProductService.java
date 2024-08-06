package com.grs.service;

import com.grs.client.FakeStoreClient;
import com.grs.exception.InternalServerException;
import com.grs.exception.NotFoundException;
import com.grs.model.Event;
import com.grs.model.dto.*;
import com.grs.repository.EventRepository;
import com.grs.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private FakeStoreClient client;

    public List<Product> getProducts() {
        return client.getProducts();
    }
}
