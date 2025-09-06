package abn.train.Training.producats;

import abn.train.Training.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;


    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsRepository.findAll();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Products> getProductById(Long id) {
        Products products = productsRepository.findById(id).orElse(null);

        return products != null ?
                ResponseEntity.status(HttpStatus.OK).body(products)
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<MessageResponse> storeProduct(Products products) {

        if (products == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Invalid Request Body"));
        }
        try {
            productsRepository.save(products);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Product Stored Successfully"));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<MessageResponse> deleteProducts(Long id) {

        Products products = productsRepository.findById(id).orElse(null);

        if (products == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Can't Find Product with This ID(" + id + ")"));
        }

        try{
            productsRepository.delete(products);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Product Deleted Successfully"));
    }

    public ResponseEntity<MessageResponse> updateProduct(Products products) {
        // Check The Existence Only
        Products products1 = productsRepository.findById(products.getProductId()).orElse(null);
        if (products1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Can't Find Product with This ID(" + products.getProductId() + ")"));
        }

        try {
            productsRepository.save(products);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Product Updates Successfully"));
    }
}
