package abn.train.Training.producats;

import abn.train.Training.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag( name = "Products", description = "Products APIs")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;


    @Operation(description = "This API To Get All Products", summary = "All Products")
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts(){
        return productsService.getAllProducts();
    }

    @Operation(description = "This API To Get Product By ID", summary = "Product By ID")
    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        return productsService.getProductById(id);
    }

    @Operation(description = "This API To Update Product By ID", summary = "Update Product By ID")
    @PutMapping("/product")
    public ResponseEntity<MessageResponse> updateProduct(@RequestBody Products products){
        return productsService.updateProduct(products);
    }

    @Operation(description = "Store New Product", summary = "New Products")
    @PostMapping("/product")
    public ResponseEntity<MessageResponse> storeProducts(@RequestBody Products products){
        return productsService.storeProduct(products);
    }

    @Operation(description = "This API To Get Product By ID", summary = "Product By ID")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Long id){
        return productsService.deleteProducts(id);
    }
}
