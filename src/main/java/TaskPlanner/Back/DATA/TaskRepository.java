package TaskPlanner.Back.DATA;

import org.springframework.data.mongodb.repository.MongoRepository;

import TaskPlanner.Back.Pojos.Task;

public interface TaskRepository extends MongoRepository<Task,String> {
    

}