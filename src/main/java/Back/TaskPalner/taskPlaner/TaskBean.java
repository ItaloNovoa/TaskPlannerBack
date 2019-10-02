package Back.TaskPalner.taskPlaner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskBean implements TaskService {

    @Autowired
    private Task task;

    @Override
    public List<Task> getTasksList() {
        return null;
    }

    @Override
    public Task getTaskById(String id) {
        return null;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return null;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        return null;
    }

    @Override
    public void removeTask(String taskId) {

    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

}