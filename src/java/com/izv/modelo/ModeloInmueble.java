/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.izv.modelo;


import com.izv.hibernate.HibernateUtil;
import com.izv.hibernate.Inmueble;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 2dam
 */
public class ModeloInmueble {
    
    public static List<Inmueble> get(){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    String hql = "from Inmueble";
    Query q = session.createQuery(hql);
    List<Inmueble> inmueble = q.list();
    session.getTransaction().commit();
    session.close();
    return inmueble;
    }
    
    public static void delete(String id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    Inmueble inm = (Inmueble) session.load(Inmueble.class, Integer.parseInt(id));
    session.delete(inm);
    session.getTransaction().commit();
    session.flush(); // Finaliza la operación de todo (cuando se modifica)
    session.close();
    }
    
    public static void insert(Inmueble inm){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(inm);
        session.getTransaction().commit();
    session.flush(); // Finaliza la operación de todo (cuando se modifica)
    session.close();
    }
    
    public static void edit(Inmueble inm){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.update(inm);
    
    session.getTransaction().commit();
    session.flush(); // Finaliza la operación de todo (cuando se modifica)
    session.close();
    }
    
    //Para el metodo editar
    public static Inmueble get(String id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    Inmueble inm = (Inmueble) session.get(Inmueble.class, Integer.parseInt(id));
    
    session.getTransaction().commit();
    session.close();
    
    return inm;
    
    }
}
