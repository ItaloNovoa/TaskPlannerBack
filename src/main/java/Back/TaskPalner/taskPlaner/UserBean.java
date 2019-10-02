package Back.TaskPalner.taskPlaner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBean implements UserService {

    @Autowired
    private User user;

    @Override
    public List<User> getUsersList() {
        return null;
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void removeUser(String userId) {

    }


}