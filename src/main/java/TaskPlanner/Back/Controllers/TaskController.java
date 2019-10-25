package TaskPlanner.Back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import TaskPlanner.Back.Pojos.Task;
import TaskPlanner.Back.Service.TaskService;

@RestController
@RequestMapping( "api" )
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
   @Autowired
	TaskService TaskService;

	@RequestMapping(value = "Task", method = RequestMethod.GET)
	public ResponseEntity<?> allTasks() {
		try {
			return new ResponseEntity<>(TaskService.getTasksList(), HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "Task/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTaskById(@PathVariable("id") String taskId) {
		try {

			return new ResponseEntity<>(TaskService.getTaskById(taskId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "Task/User/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTasksByUserId(@PathVariable("id") String UserId) {
		try {
			return new ResponseEntity<>(TaskService.getTasksByUserId(UserId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/RTask/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeTask(@PathVariable("id") String TaskId) {
		try {
			TaskService.removeTask(TaskId);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/UTask", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@RequestBody Task task) {
		try {
			TaskService.updateTask(task);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}
	@RequestMapping(value = "/Task", method = RequestMethod.POST)
	public ResponseEntity<?> InsertTask(@RequestBody Task task) {
		try {
			TaskService.addTask(task);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

	}
}