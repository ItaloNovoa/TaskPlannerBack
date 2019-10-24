package TaskPlanner.Back.Pojos;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Task {
    @Id
    private String id;
    private String description;
    private int priority;
    private Date dueDate;    
    private String state;
    private User propietario;

    public Task() {
    }

    public Task(String description, int priority, Date dueDate,String state,
            User propietario) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.state = state;
        this.propietario = propietario;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getPropietario() {
        return propietario;
    }

    public void setPropietario(User propietario) {
        this.propietario = propietario;
    }

                
}