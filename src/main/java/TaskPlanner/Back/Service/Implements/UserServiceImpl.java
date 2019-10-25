package TaskPlanner.Back.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import TaskPlanner.Back.Config.appConfiguration;
import TaskPlanner.Back.DATA.UserRepository;
import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository database;

    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(appConfiguration.class);
    private MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

    @Override
    public List<User> getUsersList() {
        Query query = new Query();
        List<User> users= mongoOperation.find(query, User.class);
        return users;
    }

    @Override
    public User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoOperation.findOne(query, User.class);
        return user;
    }
     @Override
    public Object getIdByCorreo(String correo) {        
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(correo));
        User user = mongoOperation.findOne(query, User.class);
        return user;
    }

    @Override
    public User createUser(User user) {
        return database.save(new User(user.getName(),user.getEmail(),user.getPassword()));
    }

    @Override
    public User updateUser(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        User usuario=mongoOperation.findOne(query, User.class);
        usuario.setEmail(user.getEmail());
        usuario.setName(user.getName());
        usuario.setPassword(user.getPassword());
        mongoOperation.save(usuario);
        return usuario;
    }

    @Override
    public void removeUser(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        mongoOperation.remove(query, User.class);
    }   

}