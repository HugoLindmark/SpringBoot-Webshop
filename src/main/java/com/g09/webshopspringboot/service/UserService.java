package com.g09.webshopspringboot.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.g09.webshopspringboot.domain.*;
import com.g09.webshopspringboot.repository.OrderPurchaseRepository;
import com.g09.webshopspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentSession currentSession;
    private OrderPurchaseRepository orderPurchaseRepository;


    @Autowired
    public UserService(UserRepository userRepository, CurrentSession currentSession, OrderPurchaseRepository orderPurchaseRepository) {
        this.userRepository = userRepository;
        this.currentSession = currentSession;
        this.orderPurchaseRepository = orderPurchaseRepository;
    }



    public User addUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return new User();
        } else {
            userRepository.save(user);
            return user;
        }
    }



    //Kontrollerar om username och password finns i databasen?
    public LoginResponse verifyUser(String userName, String password) {
        Optional<User> optionalUser = userRepository.findByUserNameAndPassword(userName, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            currentSession.setUser(user);
            return new LoginResponse(true, user);
        } else {
            return new LoginResponse(false,null);
        }
    }

    public LogoutResponse logOut(HttpSession session) {
        String name = currentSession.getUser().getFirstName() + " " + currentSession.getUser().getLastName();
        session.invalidate();
        return new LogoutResponse(name + " has been logged out.");
    }

    public List<User> selectAllCustomers() {
        return userRepository.findAllByRoleIsNot(Role.ADMIN);
    }

    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

    public CurrentSession getCurrentSession() {
        return currentSession;
    }
}
