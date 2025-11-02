package com.max.carpincho.service;

import com.max.carpincho.controller.dto.ProductDTO;
import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.entity.Product;
import com.max.carpincho.service.interfaces.ICategoryService;
import com.max.carpincho.service.interfaces.IProductResponseService;
import com.max.carpincho.service.interfaces.IProductService;
import com.max.carpincho.service.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.max.carpincho.service.util.ProductMapper.*;

@Service
public class ProductResponseServiceImpl implements IProductResponseService {

    @Autowired
    private IProductService productService;

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

    @Override
    public List<ProductDTO> setUp(){

        			//Create CATEGORIES
			Category clothes = Category.builder()
					.nameCategory("clothes")
					.build();
			Category rare = Category.builder()
					.nameCategory("rare")
					.build();
			Category special = Category.builder()
					.nameCategory("special")
					.build();
			Category premium = Category.builder()
					.nameCategory("premium")
					.build();

			//Create PRODUCTS
			Product tShirt = Product.builder()
					.title("T Shirt")
					.srcImg("tshirt.jpg")
					.description("Cotton Shirt")
					.price(12500)
					.categories(List.of(clothes, rare))
					.build();
			Product tShirtU = Product.builder()
					.title("T Shirt University")
					.srcImg("tshirtu.jpg")
					.description("Cotton Shirt Sublimated")
					.price(16000)
					.categories(List.of(clothes, premium, special))
					.build();
			Product tShirtB = Product.builder()
					.title("T Shirt Blue")
					.srcImg("tshirtb.jpg")
					.description("Blue Cotton Shirt")
					.price(13500)
					.categories(List.of(clothes, rare, special))
					.build();
			Product coat = Product.builder()
					.title("Coat")
					.srcImg("coat.jpg")
					.description("Coat")
					.price(15000)
					.categories(List.of(clothes, rare))
					.build();
			Product coatC = Product.builder()
					.title("Cotton Coat")
					.srcImg("coatC.jpg")
					.description("Cotton Coat")
					.price(18000)
					.categories(List.of(special, clothes))
					.build();
			Product shoe = Product.builder()
					.title("Shoe sport")
					.srcImg("shoe.jpg")
					.description("Football Shoe")
					.price(25000)
					.categories(List.of(clothes, rare, special, premium))
					.build();

        return this.productService.saveAll(List.of(tShirt, tShirtB, tShirtU, coat, coatC, shoe))
                .stream().map(ProductMapper::toProductDTO).toList();
    }


}
