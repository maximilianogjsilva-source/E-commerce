package com.max.carpincho.controller;

import com.max.carpincho.controller.dto.ProductDTO;
import com.max.carpincho.persistence.entity.Product;
import com.max.carpincho.service.interfaces.IProductResponseService;
import com.max.carpincho.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    @Autowired
    private IProductResponseService responseService;

    @GetMapping("/list-items")
    public ResponseEntity<List<ProductDTO>> listItems(){
        return ResponseEntity.ok(this.responseService.listAll());
    }

    @PostMapping("/add-item")
    public ResponseEntity<ProductDTO> addItem(@RequestBody @Validated ProductDTO productDTO){
        var prodDTO = this.responseService.saveProduct(productDTO);
        return ResponseEntity.created(URI.create("/demo/get-id/"+prodDTO.id())).body(prodDTO);
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.responseService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateById(
            @PathVariable Integer id, @RequestBody @Validated ProductDTO productDTO){
        return ResponseEntity.ok(this.responseService.editProduct(id, productDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        return this.responseService.deleteProductById(id);
    }

}
