package Back.TaskPalner.taskPlaner;



import java.util.Date;

public class Task{
    private String description;
    private String name;
    private String email;
    private String status;
    private Date dueDate;

    public Task(String description, String name, String email, String status, Date dueDate) {
        this.description = description;
        this.name = name;
        this.email = email;
        this.status = status;
        this.dueDate = dueDate;
    }
    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }   
}