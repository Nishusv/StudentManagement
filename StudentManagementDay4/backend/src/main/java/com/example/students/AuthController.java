package com.example.students;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final JwtService jwtService;
 public AuthController(JwtService jwtService){this.jwtService=jwtService;}
 @PostMapping("/login") public Map<String,String> login(@RequestBody LoginRequest request){
   if(!"admin@demo.com".equalsIgnoreCase(request.email()) || !"Admin123!".equals(request.password())) throw new InvalidCredentialsException();
   return Map.of("token",jwtService.generateToken(request.email()),"email",request.email(),"role","ADMIN");
 }
 public record LoginRequest(String email,String password){}
 @ResponseStatus(HttpStatus.UNAUTHORIZED) static class InvalidCredentialsException extends RuntimeException { InvalidCredentialsException(){super("Invalid email or password");} }
 @ExceptionHandler(InvalidCredentialsException.class) @ResponseStatus(HttpStatus.UNAUTHORIZED) public Map<String,String> badLogin(InvalidCredentialsException e){return Map.of("message",e.getMessage());}
}
