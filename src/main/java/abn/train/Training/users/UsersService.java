package abn.train.Training.users;

import abn.train.Training.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<Users> getUserByID(Long id) {
        Users users = usersRepository.findById(id).orElse(null);

        return users != null ?
                ResponseEntity.status(HttpStatus.OK).body(users)
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<MessageResponse> storeNewUser(Users users) {

        if (users != null) {
            try {
                Users users1 = usersRepository.save(users);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("New User Store Successfully"));
    }

    public ResponseEntity<MessageResponse> deleteUserById(Long id) {
        Users users = usersRepository.findById(id).orElse(null);

        if (users == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Can't Find User with This ID(" + id + ")"));
        }
        try{
            usersRepository.delete(users);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User Deleted Successfully"));
    }

    public ResponseEntity<MessageResponse> updateUser(Users users) {
        // Check The Existence Only
        Users users1 = usersRepository.findById(users.getUserId()).orElse(null);

        if (users1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Can't Find User with This ID(" + users.getUserId() + ")"));
        }

        try{
            usersRepository.save(users);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User Updates Successfully"));
    }
}
