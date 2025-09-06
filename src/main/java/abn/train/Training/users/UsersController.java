package abn.train.Training.users;

import abn.train.Training.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag( name = "Users", description = "Users APIs")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @Operation(description = "This API To Get All Users", summary = "All Users")
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        return usersService.getAllUsers();
    }


    @Operation(description = "This API To Get User By ID ", summary = "User By ID")
    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getAllUsers(@PathVariable("id") Long id){
        return usersService.getUserByID(id);
    }

    @Operation(description = "This API To Update User", summary = "Update User")
    @PutMapping("/user")
    public ResponseEntity<MessageResponse> updateUser(@RequestBody Users users){
        return usersService.updateUser(users);
    }

    @Operation(description = "This API To Store Nrw User", summary = "Store New User")
    @PostMapping("/user")
    public ResponseEntity<MessageResponse> storeUser(@RequestBody Users users){
        return usersService.storeNewUser(users);
    }

    @Operation(description = "This API To Delete User By ID ", summary = "Delete User By ID")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<MessageResponse> deleteUserByID(@PathVariable("id") Long id){
        return usersService.deleteUserById(id);
    }
}
