package TaskPlanner.Back.Controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import com.mongodb.client.gridfs.model.GridFSFile;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.UserService;

@RestController
@RequestMapping( "api" )
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService UserServices;    
    
    @Autowired
    GridFsTemplate gridFsTemplate;
    
    @RequestMapping("/files/{filename}")
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename) throws IOException {
        try {
            GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(filename)));
            GridFsResource resource = gridFsTemplate.getResource(file.getFilename());
            return ResponseEntity.ok()
                .contentType(MediaType.valueOf(resource.getContentType()))
                .body(new InputStreamResource(resource.getInputStream()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   

    }

    @PostMapping("/files/{name}")
    public String handleFileUpload(@PathVariable("name") String name,@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {
        try {
            gridFsTemplate.store(file.getInputStream(), name, file.getContentType());
            return file.getName();
        } catch (Exception e) {
            return ("Not Found");
        }
    }

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        try {
            return new ResponseEntity<>(UserServices.getUsersList(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "User/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") String userId) {
        try {

            return new ResponseEntity<>(UserServices.getUserById(userId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(value = "CUser/{correo}", method = RequestMethod.GET)
    public ResponseEntity<?> getIdByCorreo(@PathVariable("correo") String correo) {
        try {
            return new ResponseEntity<>(UserServices.getUserByCorreo(correo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(value = "/User", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User u) {
        try {

            return new ResponseEntity<>(UserServices.createUser(u), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(value = "/RUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUser(@PathVariable("id") String userId) {
        try {
            UserServices.removeUser(userId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(value = "/UUser", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User u) {
        try {
            UserServices.updateUser(u);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public Token Login( @RequestBody User login ) throws ServletException  {

        String jwtToken = "";

        if ( login.getEmail() == null || login.getPassword() == null )
        {
            throw new ServletException( "Please fill in username and password" );
        }

        String email = login.getEmail();
        String password = login.getPassword();

        //TODO implement logic to verify user credentials
        User user = UserServices.getUserByCorreo(email);

        if ( user == null )
        {
            throw new ServletException( "User username not found." );
        }

        String pwd = user.getPassword();

        if ( !password.equals( pwd ) )
        {
            throw new ServletException( "Invalid login. Please check your name and password." );
        }
        
        jwtToken = Jwts.builder().setSubject( email ).claim( "roles", "user" ).setIssuedAt( new Date() ).signWith(
        SignatureAlgorithm.HS256, "secretkey" ).compact();
        return new Token( jwtToken );       
    }


    public class Token    {

        String accessToken;

        public Token( String accessToken )
        {
            this.accessToken = accessToken;
        }


        public String getAccessToken()
        {
            return accessToken;
        }

        public void setAccessToken( String access_token )
        {
            this.accessToken = access_token;
        }
    }

}