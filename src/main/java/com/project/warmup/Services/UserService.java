package com.project.warmup.Services;

import com.project.warmup.Models.Role;
import com.project.warmup.Models.User;
import com.project.warmup.Repositories.RoleRepository;
import com.project.warmup.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found in database");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName().toUpperCase()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(),authorities);
    }

    public void save(String username,String password){
        User user = new User();
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        role.setName("User");
        roles.add(role);

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void save(String username,String password,String roleName){
        User user = new User();
        Role role = new Role();
        List<Role> roles = new ArrayList<>();

        role.setName(roleName);
        roles.add(role);

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
