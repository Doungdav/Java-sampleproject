package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepository {
    private final static String connectUrl = "jdbc:postgresql://localhost:5432/postgres";
    private final static String username = "postgres";
    private final static String password = "123";

    public static List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(connectUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select* from user_tb");
            ){
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("passcode"));
                allUsers.add(user);
            }
            return allUsers;


        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }
}
