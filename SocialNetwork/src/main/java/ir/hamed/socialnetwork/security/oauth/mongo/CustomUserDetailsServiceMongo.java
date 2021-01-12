package ir.hamed.socialnetwork.security.oauth.mongo;

import java.util.ArrayList;
import java.util.Collection;

import ir.hamed.socialnetwork.models.entity.mongo.Role;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = {"oauth","mongodb"})
public class CustomUserDetailsServiceMongo implements UserDetailsService {

    @Autowired
    UserMongoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = this.userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user not found."));

        if (dbUser != null) {
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (Role role : dbUser.getRoles()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName().name());
                grantedAuthorities.add(authority);
            }

            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                    dbUser.getUsername(), dbUser.getPassword(), grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
    }

}
