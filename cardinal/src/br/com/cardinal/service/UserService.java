package br.com.cardinal.service;

import br.com.cardinal.model.StatusUser;
import br.com.cardinal.repository.UserRepository;

public class UserService {
    private static final UserRepository userRepository = new UserRepository();

    public static void updateUser(int userId, String statusUser){


        if(statusUser.equals("ACTIVE")){
            statusUser = "BLOCKED";
        }else {statusUser = "ACTIVE";}

        userRepository.update(userId,statusUser);
        System.out.println("User status updated");
    }
}
