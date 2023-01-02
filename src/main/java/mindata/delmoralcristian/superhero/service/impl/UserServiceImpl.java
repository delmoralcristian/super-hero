package mindata.delmoralcristian.superhero.service.impl;

import mindata.delmoralcristian.superhero.enums.CommonMessage;
import mindata.delmoralcristian.superhero.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Value("${user.admin.name}")
    private String user;

    @Value("${user.admin.password}")
    private String password;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(!user.equalsIgnoreCase(userName)) {
            throw new UsernameNotFoundException(String.format(CommonMessage.USER_NOT_FOUND.getMessage(), userName));
        }
        return new User(user, password, new ArrayList<>());
    }
}
