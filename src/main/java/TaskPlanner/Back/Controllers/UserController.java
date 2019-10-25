package TaskPlanner.Back.Controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import TaskPlanner.Back.Pojos.User;
import TaskPlanner.Back.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping( "api" )
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
	UserService UserServices;
	
	
	@RequestMapping(value="/User",method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers(){
	    try {
	        return new ResponseEntity<>(UserServices.getUsersList(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
	    }
    }
    @RequestMapping(value = "User/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("id") String userId) {
		try {
			
			return new ResponseEntity<>(UserServices.getUserById(userId),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

    }
     @RequestMapping(value = "CUser/{correo}", method = RequestMethod.GET)
	public ResponseEntity<?> getIdByCorreo(@PathVariable("correo") String correo) {
		try {
			return new ResponseEntity<>(UserServices.getUserByCorreo(correo).getId(),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);

		}

    }
    
	@RequestMapping(value = "/User", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User u) {
		try {
			
			return new ResponseEntity<>(UserServices.createUser(u),HttpStatus.CREATED);
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
    
    //
	@RequestMapping( value = "/login", method = RequestMethod.POST )
    public Token login( @RequestBody User login )   throws ServletException {
        String jwtToken = "";

        try {
            if ( login == null ){
                throw new ServletException( "User not found." );
            }
    
            if ( login.getEmail() == null || login.getPassword() == null )
            {
                throw new ServletException( "Por favor ingrese el email y la contraseña" );
            }
    
            String username = login.getEmail();
            String password = login.getPassword();
    
            //TODO implement logic to verify user credentials
            User user = UserServices.getUserById(String.valueOf(UserServices.getUserByCorreo(username).getId()));
            
            
            if ( user == null ){
                throw new ServletException( "User username not found." );
            }
    
            String pwd = user.getPassword();
    
            if ( password.equals( pwd ) == false){
                throw new ServletException( "Invalid login. Please check your name and password." );
            }
    
            jwtToken = Jwts.builder().setSubject( username ).claim( "roles", "user" ).setIssuedAt( new Date() ).signWith(
            SignatureAlgorithm.HS256, "secretkey" ).compact();
            return new Token( jwtToken );
        } catch (Exception e) {
            System.out.println("ERROR");
           return null;
        }
        
        
    }
    public class Token
    {

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