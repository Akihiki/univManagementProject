package com.example.univmanag.dao;

import com.example.univmanag.beans.Amphis;
import com.example.univmanag.dao.facade.AmphiDao;
import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Local(AmphiDao.class)
@Stateless
public class AmphisDAOImpl implements AmphiDao {

    public List<Amphis> getAmphis(String show, Date search_date_debut, Date search_date_fin) {
        Connection con = null;
        PreparedStatement ps = null;
        List<Amphis> amphisList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            assert con != null;
            if (show.equals("all"))
                ps = con.prepareStatement("Select nom, capacite, available,image from amphis");
            else if (show.equals("available")){
                ps = con.prepareStatement("Select nom, capacite, available,image from amphis where available=?");
            ps.setBoolean(1, true);}
            else if (show.equals("taken")){
                ps = con.prepareStatement("Select nom, capacite, available,image from amphis where available=?");
            ps.setBoolean(1, false);}

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                int capacite = rs.getInt("capacite");
                boolean available = rs.getBoolean("available");
                String image = rs.getString("image");
                //Assuming you have a user object
                Amphis amphis = new Amphis(nom, capacite, available, image);

                amphisList.add(amphis);
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return amphisList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(amphisList);
        return amphisList;
    }

    @Override
    public void makeAmphiReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update amphis set available=? where nom=?");
            ps.setBoolean(1, false);
            ps.setString(2, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void deleteAmphi(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("delete from amphis where nom=?");
            ps.setString(1, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void makeAmphiUnReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update amphis set available=? where nom=?");
            ps.setBoolean(1, true);
            ps.setString(2, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }
    public boolean addAmphi(int i, String nom, int capacite, boolean available, String s) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO amphis(id,nom,capacite,available,image) values (?,?,?,?,?)");
            ps.setInt(1, i);
            ps.setString(2, nom);
            ps.setInt(3, capacite);
            ps.setBoolean(4, false);
            ps.setString(5, s);
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
