package TaskPlanner.Back.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import TaskPlanner.Back.DATA.UserRepository;
import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository database;

    @Override
    public List<User> getUsersList() {
        //return database.getUsersList();
        return null;
    }

    @Override
    public User getUserById(String userId) {
        //return database.getUserById(userId);
        return null;
    }
     @Override
    public Object getIdByCorreo(String correo) {        
        //return database.getIdByCorreo(correo);
        return null;
    }

    @Override
    public User createUser(User user) {
        return database.save(new User(user.getName(),user.getEmail(),user.getPassword()));
    }

    @Override
    public User updateUser(User user) {
        //return database.updateUser(user);
        return null;
    }

    @Override
    public void removeUser(String userId) {
        //database.removeUser(userId);
    }

   

}