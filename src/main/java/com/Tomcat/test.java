package com.Tomcat;

import com.Tomcat.enums.Status;
import com.Tomcat.model.enttis.User;
import com.Tomcat.repository.UserRepository;
import com.Tomcat.repository.userRepositoryImpl.UserRepositoryJPA;

public class test {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryJPA();
        userRepository.createUser(new User(0,"Movses","Harutyunyan",2002,
                "movsesharutyunyan2@gmail.com","MTIzNDU2NzhM", Status.ACTIVE,null,"50959060"));
    }

}
