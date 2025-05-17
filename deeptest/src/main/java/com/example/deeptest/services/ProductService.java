package com.example.deeptest.services;

import com.example.deeptest.dto.ProductDTO;
import com.example.deeptest.entities.Product;
import com.example.deeptest.repositories.ProductRepository;
import com.example.deeptest.services.exceptions.InvalidDataException;
import com.example.deeptest.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        validateData(dto);
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return  new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            validateData(dto);
            Product entity;
            entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);

            return  new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
    }


    public void validateData(ProductDTO dto) {
        if(dto.getName().isBlank()){
            throw  new InvalidDataException("Campo nome é vazio ou nulo");
        }
        if(dto.getPrice() == null || dto.getPrice() <= 0){
            throw new InvalidDataException("Campo preço é nulo ou negativo");
        }


    }


}
