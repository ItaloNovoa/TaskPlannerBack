package TaskPlanner.Back.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import TaskPlanner.Back.DATA.dataConnect;
import TaskPlanner.Back.Pojos.Task;
import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.TaskService;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    dataConnect database;
   
    @Override
    public Task getTaskById(String id) {
        return database.getTaskById(id);
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return database.getTasksByUserId(userId);
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        return database.assignTaskToUser(taskId, user);
    }

    @Override
    public void removeTask(String taskId) {
        database.removeTask(taskId);
    }

    @Override
    public Task updateTask(Task task) {
        return database.updateTask(task);
    }

    @Override
    public List<Task> getTasksList() {
        return database.geTasksList();
    }

    @Override
    public void addTask(Task task) {
       database.addTask(task);
    }

    
}