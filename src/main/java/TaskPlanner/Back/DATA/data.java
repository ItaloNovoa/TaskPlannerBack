package TaskPlanner.Back.DATA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import TaskPlanner.Back.Pojos.*;

@Component
@Scope("singleton")
public class data implements dataConnect {
    ArrayList<User> users;
    ArrayList<Task> tasks;
    public data() {
        users=new ArrayList<User>();
        tasks=new ArrayList<Task>();
        User u=new User();
        u.setId(1);
        u.setEmail("dd@hotmail.com");
        u.setName("dd");
        u.setPassword("password");
        users.add(u);
        Task t=new Task();
        t.setDescription("work");
        t.setId("1234");
        t.setDueDate(new Date());
        t.setState("In Progress");
        tasks.add(t);
        assignTaskToUser("1234", u);
        Task t2=new Task();
        t2.setDescription("rest");
        t2.setId("1235");
        t2.setDueDate(new Date());
        t2.setState("Completed");
        tasks.add(t2);
        assignTaskToUser("1235", u);
    }
    
    @Override
    public List<Task> geTasksList() {
        return tasks;
    }

    @Override
    public Task getTaskById(String id) {
        for(Task t:tasks){
            if(t.getId().equals(id)){
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        ArrayList<Task> usertasks=new ArrayList<Task>();
        for(Task t: tasks){
            if(t.getPropietario().getEmail().equals(userId)){
                usertasks.add(t);
            }
        }
        return usertasks;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        for(Task t:tasks){
            if(t.getId().equals(taskId)){
                t.setPropietario(user);
                return t;
            }
        }
        return null;
    }

    @Override
    public void removeTask(String taskId) {
        Task toBeRemoved=null;
        for(Task t:tasks){
            if(t.getId().equals(taskId)){
                toBeRemoved=t;
                break;
            }
        }
        if(!toBeRemoved.equals(null)){
            tasks.remove(toBeRemoved);
        }
    }

    @Override
    public Task updateTask(Task task) {
        int index=-1;
        for(Task t:tasks){
            if(t.getId().equals(task.getId())){
                index=tasks.indexOf(t);
                break;
            }
        }
        if(index!=-1){
            tasks.set(index,task);
            return task;
        }
        return null;
    }

    @Override
    public List<User> getUsersList() {
        return users;
    }

    @Override
    public User getUserById(String userId) {
        for(User u:users){
            if (String.valueOf(u.getId()).equals(userId)){
                return u;
            }
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        int index=-1;
        for(User u:users){
            if(u.getEmail().equals(user.getEmail())){
                index=users.indexOf(u);
                break;
            }
        }
        if(index!=-1){
            users.set(index,user);
            return user;
        }
        return null;

    }

    @Override
    public void removeUser(String userId) {
        User toBeRemoved=null;
        for(User u:users){
            if(u.getEmail().equals(userId)){
                toBeRemoved=u;
                break;
            }
        }
        if(!toBeRemoved.equals(null)){
            users.remove(toBeRemoved);
        }

    }

    @Override
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }
    
}