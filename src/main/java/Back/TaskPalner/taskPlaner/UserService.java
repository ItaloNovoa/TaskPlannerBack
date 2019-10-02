package Back.TaskPalner.taskPlaner;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getUsersList();
    
    User getUserById(String userId);
    
    User createUser(User user);
    
    User updateUser(User user);
    
    void removeUser(String userId);
}