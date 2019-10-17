package TaskPlanner.Back.DATA;

import java.util.List;

import org.springframework.stereotype.Service;

import TaskPlanner.Back.Pojos.*;

@Service
public interface dataConnect{
    List<Task> geTasksList();
    
    Task getTaskById(String id);
    
    List<Task> getTasksByUserId(String userId);
    
    Task assignTaskToUser(String taskId, User user);
    
    void removeTask(String taskId);
    
    Task updateTask(Task task);
    List<User> getUsersList();
    
    User getUserById(String userId);
    
    User createUser(User user);
    
    User updateUser(User user);
    
    void removeUser(String userId);

	Task addTask(Task task);
    
}