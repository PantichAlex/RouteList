package ru.pantich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pantich.domain.User;
import ru.pantich.repo.RoleRepo;
import ru.pantich.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public boolean saveUser(User user){

        User userFromDB=userRepo.findByLogin(user.getLogin());
        if(userFromDB!=null){

            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
