/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.izv.modelo;

import com.izv.hibernate.HibernateUtil;
import com.izv.hibernate.Fotos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 2dam
 */
public class ModeloFoto {
        
    public static List<Fotos> get(){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    String hql = "from Fotos";
    Query q = session.createQuery(hql);
    List<Fotos> fotos = q.list();
    session.getTransaction().commit();
    session.close();
    return fotos;
    }
    
    /*
 public static List<Fotos> getfoto(String id){
 Session session = HibernateUtil.getSessionFactory().openSession();
 session.beginTransaction();
 String hql = "from Fotos where idinmueble="+id;
 Query q = session.createQuery(hql);
 List<Fotos> fotos = q.list();
 session.getTransaction().commit();
 session.close();
 return fotos;
 }

    */
    
    public static void delete(Fotos fo){
    Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        session.delete(fo);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Fotos inm){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(inm);
        session.getTransaction().commit();
    session.flush(); // Finaliza la operación de todo (cuando se modifica)
    session.close();
    }
    
    public static void edit(Fotos fotos){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.update(fotos);
    
    session.getTransaction().commit();
    session.flush(); // Finaliza la operación de todo (cuando se modifica)
    session.close();
    }
    
    //Para el metodo editar
    public static Fotos get(String id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();  
    Fotos fotos = (Fotos) session.get(Fotos.class, Integer.parseInt(id));    
    session.getTransaction().commit();
    session.close();    
    return fotos;    
    }
}
