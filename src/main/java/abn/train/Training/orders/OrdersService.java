package abn.train.Training.orders;

import abn.train.Training.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public ResponseEntity<List<Orders>> getAllOrders() {
        try{
            List<Orders> orders = ordersRepository.findAll();
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<Orders> getOrderByID(Long id) {
        try{
            Orders orders = ordersRepository.findById(id).orElse(null);
            if (orders == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<MessageResponse> updateOrder(Orders orders) {

        // Check The Existence Only
        Orders orders1 = ordersRepository.findById(orders.getOrderId()).orElse(null);
        if (orders1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try{
            ordersRepository.save(orders);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Order Updates Successfully"));
    }

    public ResponseEntity<MessageResponse> storeOrder(Orders orders) {
        try{
            ordersRepository.save(orders);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Order Stored Successfully"));
    }

    public ResponseEntity<MessageResponse> deleteOrderById(Long id) {

        try{
            Orders orders = ordersRepository.findById(id).orElse(null);
            if (orders == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ordersRepository.delete(orders);
            return ResponseEntity.ok(new MessageResponse("Order Deleted Successfully"));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
