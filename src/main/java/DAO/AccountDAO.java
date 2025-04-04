package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {//
    public Account createAccount(String username, String password)
    {
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return new Account(rs.getInt(1), username, password);
       
        } catch (Exception e) { //sqlexception
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("reached");
        }
        return null;
    }

    public Account getAccountbyUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (username != null){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }




    // public Account loginAccount(String username, String password)
    // {

    //     Connection connection = ConnectionUtil.getConnection();
    //     try {
    //         //Write SQL logic here
    //         String sql = "SELECT * FROM account WHERE username = ?";

    //         PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
    //         preparedStatement.setString(1, username);


    //         preparedStatement.executeUpdate();
    //         ResultSet rs = preparedStatement.getGeneratedKeys();
    //         rs.next();
    //         System.out.println(rs.getString("password"));
    //         System.out.println("gaa");
    //         if(rs.getString("password") == password)
    //         {
    //             Account account = new Account(rs.getInt(1), username, password);
    //             return account;
    //         }
    //     } catch (Exception e) { //sqlexception
    //         // TODO: handle exception
    //         System.out.println(e.getMessage());
    //         System.out.println("reached");
    //     }
    //     return null;
    // }
}
