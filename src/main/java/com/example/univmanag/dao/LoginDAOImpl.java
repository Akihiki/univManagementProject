package com.example.univmanag.dao;

import com.example.univmanag.beans.SessionUtils;
import com.example.univmanag.dao.facade.LoginDao;
import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Local(LoginDao.class)
@Stateless
public class LoginDAOImpl implements LoginDao {

    public boolean validate(String user, String password) {
        System.out.println("calling validate" + user + password);
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select uname, password,role,uid from users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("user_id", rs.getInt("uid"));
                if (rs.getBoolean("role") == true)
                    session.setAttribute("role", "admin");
                else
                    session.setAttribute("role", "user");
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public boolean verifyExistence(String user) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select uname from users where uname = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return true;
    }

    public boolean persist(String user, String pwd, String firstName, String lastName, String university, String faculty, Boolean role) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO users(uid,uname,password,firstName,lastName,university,faculty,role) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, String.valueOf((int) (Math.random() * 900) + 25));
            ps.setString(2, user);
            ps.setString(3, pwd);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, university);
            ps.setString(7, faculty);
            ps.setBoolean(8, role);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return true;
    }
}
