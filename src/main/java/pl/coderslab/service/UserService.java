package pl.coderslab.service;

import org.mindrot.BCrypt;

public class UserService {

    public static String hashPassword(String password){

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String textPassword, String hashedPassword){

        return BCrypt.checkpw(textPassword, hashedPassword);
    }
}
