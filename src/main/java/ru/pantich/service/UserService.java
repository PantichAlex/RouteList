package ru.pantich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pantich.domain.User;
import ru.pantich.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userFromDb=userRepo.findByLogin(s);
        if(userFromDb==null){
            throw new UsernameNotFoundException(s);
        }
        return new UserPrincipal(userFromDb);
    }

    public boolean saveUser(User user){
        User userFromDb=userRepo.findByLogin(user.getLogin());
        if(userFromDb!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
