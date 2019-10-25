package TaskPlanner.Back.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import TaskPlanner.Back.Pojos.*;

@Service
public interface UserService {
    List<User> getUsersList();
    
    User getUserById(String userId);
    
    User createUser(User user);
    
    User updateUser(User user);
    
    void removeUser(String userId);

	User getUserByCorreo(String correo);
}