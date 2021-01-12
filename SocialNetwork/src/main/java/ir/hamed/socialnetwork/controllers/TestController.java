package ir.hamed.socialnetwork.controllers;

import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.CustomRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class TestController {

    @Autowired(required = false)
    CustomRepository<UserMysql,Long> userMysqlRepository;

    @PostMapping("/test")
    public ResponseEntity<?> createToken(){
        Iterable<UserMysql> userMysql = userMysqlRepository.findByUsername("admin");
        Iterator<UserMysql> userIt = userMysql.iterator();
        while (userIt.hasNext()){
            System.out.println(userIt.next());
        }
        return ResponseEntity.ok(userMysql);
    }
}
