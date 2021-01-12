package ir.hamed.socialnetwork.security.oauthjwt.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@ConditionalOnProperty(name = {"oauthjwt","mongodb"})
public class AuthorizationServerConfigurationOauthJwtMongo extends AuthorizationServerConfigurerAdapter {
    @Value("${client-id}")
    private String clientid;
    @Value("${client-secret}")
    private String clientSecret;
    @Value("${access-token-expire}")
    private Integer accessTokenExpire;
    @Value("${refresh-token-expire}")
    private Integer refreshTokenExpire;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(privateKey);
//        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(tokenEnhancer());
//    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientid)
                .secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "client_credentials", "refresh_token")
                .scopes("all")
                .accessTokenValiditySeconds(accessTokenExpire)
                .refreshTokenValiditySeconds(refreshTokenExpire);


    }
}
