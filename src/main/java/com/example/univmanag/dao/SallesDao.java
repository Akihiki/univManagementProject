package com.example.univmanag.dao;
import com.example.univmanag.beans.Ressources;
import com.example.univmanag.beans.Salle;
import com.example.univmanag.beans.Salles;

import java.util.Date;
import java.util.List;

public interface SallesDao {
    public boolean addSalle(int i, String nom, int capacite, String departement, String s) ;
    public List<Salles> getSalles(String show);
    public List<Salle> getAllSalles(String show);
    void makeSalleUnReserved(String nom);
    void makeSalleReserved(String nom);

    void deleteSalle(String nom);

    void reserveSalle(String nom, String date1, String date2);
}
