package TaskPlanner.Back.DATA;

import org.springframework.data.mongodb.repository.MongoRepository;

import TaskPlanner.Back.Pojos.User;

public interface UserRepository extends MongoRepository<User,String> {
    

}