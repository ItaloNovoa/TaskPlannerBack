package Back.TaskPalner.taskPlaner;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;
    /**
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("task", new User());
        return "index";
    }
    
    @PostMapping("/")
    public String userSubmit(@ModelAttribute User user) throws SQLException {
        Boolean b=userService.añadirUsuario(user.getFirstName(), user.getLastName());
        if(b){
            return "result";
        }else{
            return "repetido";
        }
        
    } */
    
}