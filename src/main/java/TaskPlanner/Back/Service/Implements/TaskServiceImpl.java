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
import TaskPlanner.Back.DATA.TaskRepository;
import TaskPlanner.Back.Pojos.Task;
import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.TaskService;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository database;

    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(appConfiguration.class);
    private MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

   
    @Override
    public Task getTaskById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Task task = mongoOperation.findOne(query, Task.class);
        return task;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user.id").is(userId));
        List<Task> tasks= mongoOperation.find(query, Task.class);
        return tasks;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(taskId));
        Task task = mongoOperation.findOne(query, Task.class);
        task.setPropietario(user);
        mongoOperation.save(task);
        return task;        
    }

    @Override
    public void removeTask(String taskId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(taskId));
        mongoOperation.remove(query, Task.class);
    }

    @Override
    public Task updateTask(Task task) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(task.getId()));
        Task task2 = mongoOperation.findOne(query, Task.class);
        task2.setDescription(task.getDescription());
        task2.setDueDate(task.getDueDate());
        task2.setPriority(task.getPriority());
        task2.setState(task.getState());
        mongoOperation.save(task2);
        return task2;
    }

    @Override
    public List<Task> getTasksList() {
        Query query = new Query();
        List<Task> tasks= mongoOperation.find(query, Task.class);
        return tasks;
    }

    @Override
    public void addTask(Task task) {
       database.save(new Task(task.getDescription(),task.getPriority(),task.getDueDate(),task.getState(),task.getPropietario()));
    }

    
}