package com.example.univmanag.dao.facade;
import com.example.univmanag.beans.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationsDao {
    public boolean addReservation( Date date_debut, Date date_fin, int user_id, String user_name,int resource_id, String resource_name) ;
    public List<Reservation> getReservation(String show);
    public List<Reservation> getAllReservation(String show, Date search_date_debut, Date search_date_fin);

    public Boolean isReserved(int id);

    void deleteResrvation(int id);
    void cancelResrvation(int id);

}
