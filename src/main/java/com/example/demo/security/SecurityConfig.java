package com.example.demo.security;
import com.example.demo.service.UserDetailserviceimpl;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  
   
    @Autowired 
    private CustomLogoutHandler customLogoutHandler;

  
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @org.springframework.context.annotation.Lazy
    @Autowired
    private  UserDetailserviceimpl userDetailsService;
    private final CustomLogoutHandler logoutHandler;
    
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,CustomLogoutHandler logoutHandler) {
   this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    this.logoutHandler = logoutHandler;
   
  
}
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .cors(Customizer.withDefaults()) 
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/addnewuser/**","/inscriptionkyword/**","/stagiareskyword/**","/stagiareskywordbygroube/**","/inscriptionskywordbygroube1/**","/addnewAssistant/**","/addusertorol/**","/uploadImag/**","/downloadImage/**","/objet/**","/ajouterinscription/**").permitAll();
                    authConfig.requestMatchers("/login/**","/stagiareskywordbynotegroube/**","/stagiaireDelete/**","/stagiaresCheked/**","/inscriptions/**").permitAll();
                    authConfig.requestMatchers("/assistants/**").permitAll();
                    authConfig.requestMatchers("/stagiaireSujects/**").permitAll();
                    authConfig.requestMatchers("/CommentaireAssistantStagiaire/**","/allsubjects/**","/projets/**","/subjectsbyid/**","/documentType/**").permitAll();
                    authConfig.requestMatchers("/commentairesAssistantStagiaireSubject/**").permitAll();
                    authConfig.requestMatchers("/subjectCategorie/**","/stagiaireQuestions/**","/subjectsbyProjet/**","/phaseProjet/**").permitAll();
                    authConfig.requestMatchers("/questions/**","/questionById/**","/questionBySubject/**").permitAll();
                    authConfig.requestMatchers("/subjects/**").permitAll();
                    authConfig.requestMatchers("/updateEtat/**").permitAll();
                    authConfig.requestMatchers("/indicateurs/**").permitAll();
                    authConfig.requestMatchers("/notifications/**").permitAll();
                    authConfig.requestMatchers("/chat-socket/**","publiersujet/**","/swagger-ui/**","/updatepassword/**","/savestagairequestion/**").permitAll();
                    authConfig.requestMatchers("/chat/**","send/**").permitAll();
                    authConfig.requestMatchers("/getStudent/**","/stagiaires/**","/getallassistants/**","/DefaultGroupe/**","/stagaieGroupe/**","/inscriptionGroupe/**","/AllGroupe/**","/searchGroupes/**","/groupe/**","/sendEmail/**","/ajouterAdmin/**","/getallstagairequestions/**","/getAllIndicateurs/**").permitAll();
                    authConfig.requestMatchers("/Test1/**","/Test12/**","/addnewrole/**","/addroletouser/**","/Inscription/**","/dateenvoi/**","/listestagaire/**","/ajouterassistant/**","/listassistant/**","/incrimenter/**","/dicrimenter/**","/logout1/**").permitAll();
                    authConfig.requestMatchers("/admin/**").denyAll();
                   
                })
             
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
            
                .userDetailsService(userDetailsService)
             
                 ;

        return http.build();
    }


   /*  @Bean
    UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder1=passwordEncoder();

        var admin = User.builder()
                .username("Willy De Keyser")
                .password(("{noop}password"))
                .roles("USER", "ADMIN")
                .build();
        var user = User.builder()
                .username("Ken De Keyser")
                .password(( passwordEncoder1.encode("password")))
                .roles("ATEST")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }*/
  
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtEncoder JwtEncoder (){
        String secrykey="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5972";
        return new NimbusJwtEncoder(new ImmutableSecret<>(secrykey.getBytes()));

    }
    @Bean
    JwtDecoder jwtDecoder(){
        String secrykey="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5972";
        SecretKeySpec secretKeySpec=new SecretKeySpec(secrykey.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager( daoAuthenticationProvider);
    }
@Bean
CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration corsConfiguration=new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("http://docutrackproximity.s3-website.eu-north-1.amazonaws.com"); // Autorise seulement cette origine
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.setAllowCredentials(true); // Autorise les informations d'authentification

  // corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
    UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**",corsConfiguration);
    return  source;
}

}

