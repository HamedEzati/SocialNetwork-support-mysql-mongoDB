package ir.hamed.socialnetwork.security.jwt.mysql.service;

import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@ConditionalOnProperty(name = {"mysqldb","jwt"})
public class UserMysqlDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserMysqlRepository userMysqlRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMysql user = userMysqlRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserMysqlDetailsImpl.build(user);
	}

}
