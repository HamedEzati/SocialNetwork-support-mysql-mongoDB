package ir.hamed.socialnetwork.security.oauthjwt;


import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@ConditionalOnProperty(name = {"oauthjwt","mysqldb"})
public class CustomUserDetailsServiceOauthJwtMysql implements UserDetailsService {

    @Autowired
    UserMysqlRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMysql dbUser = this.userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user not found."));

        if (dbUser != null) {
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (RoleMysql role : dbUser.getRoles()) {
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
