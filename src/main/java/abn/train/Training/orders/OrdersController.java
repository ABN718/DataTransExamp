package abn.train.Training.orders;

import abn.train.Training.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag( name = "Orders", description = "Orders APIs")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService OrdersService;

    @Operation(description = "This API To Get All Orders", summary = "All Orders")
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return OrdersService.getAllOrders();
    }

    @Operation(description = "This API To Get Order By ID", summary = "Order By ID")
    @GetMapping("/order/{id}")
    public ResponseEntity<Orders> getAllOrders(@PathVariable("id") Long id){
        return OrdersService.getOrderByID(id);
    }

    @Operation(description = "This API To Update Order By ID", summary = "Update Order By ID")
    @PutMapping("/order")
    public ResponseEntity<MessageResponse> updateOrder(@RequestBody Orders orders){
        return OrdersService.updateOrder(orders);
    }

    @Operation(description = "This API To Update Order By ID", summary = "Update Order By ID")
    @PostMapping("/order")
    public ResponseEntity<MessageResponse> storeOrder(@RequestBody Orders orders){
        return OrdersService.storeOrder(orders);
    }

    @Operation(description = "This API To Delete Order By ID", summary = "Delete Order By ID")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<MessageResponse> deleteOrderById(@PathVariable("id") Long id){
        return OrdersService.deleteOrderById(id);
    }
}
