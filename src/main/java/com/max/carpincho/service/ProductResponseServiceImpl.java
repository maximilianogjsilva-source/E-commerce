package com.max.carpincho.service;

import com.max.carpincho.controller.dto.ProductDTO;
import com.max.carpincho.service.interfaces.ICategoryService;
import com.max.carpincho.service.interfaces.IProductResponseService;
import com.max.carpincho.service.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.max.carpincho.service.util.ProductMapper.*;

@Service
public class ProductResponseServiceImpl implements IProductResponseService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ICategoryService categoryService;


    @Override
    public List<ProductDTO> listAll() {
        //Se consulta a BD por la lista de todos los Product y se devuelve una lista de ProductDTO
        return this.productService.listAllProduct().stream()
                .map(ProductMapper::toProductDTO).toList();

    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        //1_Verifico si existen las categorias en BD. 2_Si alguna no existe la guarda en BD
        productDTO.categories().forEach(categoryService::saveCategoryIfNotExists);

        //Se crea la lista de las categorias
        var categories = productDTO.categories().stream()
                .map(categoryService::findByName).toList();

        //Se guarda el Product en BD
        var productSaved = this.productService.saveProduct(productWithCategories(productDTO, categories));

        //Se devuelve un ProductDTO basado en un Product
        return toProductDTO(productSaved);
    }

    @Override
    public ProductDTO getById(Integer id) {
        //Se consulta a BD por el Product segun su Id y se devuelve un ProductDTO
        return toProductDTO(this.productService.getById(id));
    }

    @Override
    public ProductDTO editProduct(Integer idProduct, ProductDTO newProductDTO) {
        //1_Verifico si existen las categorias en BD. 2_Si alguna no existe la guarda en BD
        newProductDTO.categories().forEach(categoryService::saveCategoryIfNotExists);

        //Se crea la lista de las categorias
        var categories = newProductDTO.categories().stream()
                .map(categoryService::findByName).toList();

        //Se guarda el Product editado en BD
        var productEdited = this.productService.editProduct(idProduct,
                productWithCategories(newProductDTO, categories));

        //Se devuelve un ProductDTO basado en un Product
        return toProductDTO(productEdited);
    }

    @Override
    public ResponseEntity<?> deleteProductById(Integer id) {
        if (this.productService.deleteProductById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
