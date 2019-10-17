package TaskPlanner.Back.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import TaskPlanner.Back.DATA.dataConnect;
import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    dataConnect database;

    @Override
    public List<User> getUsersList() {
        return database.getUsersList();
    }

    @Override
    public User getUserById(String userId) {
        return database.getUserById(userId);
    }

    @Override
    public User createUser(User user) {
        return database.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return database.updateUser(user);
    }

    @Override
    public void removeUser(String userId) {
        database.removeUser(userId);
    }

}