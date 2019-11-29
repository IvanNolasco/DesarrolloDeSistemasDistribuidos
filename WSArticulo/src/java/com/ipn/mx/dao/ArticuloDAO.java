/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.dao;

import com.ipn.mx.entidades.Articulo;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jonat
 */
public class ArticuloDAO {

    public void create(Articulo art) {
        Session session
                = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.saveOrUpdate(art);
            transaction.commit();

        } catch (HibernateException he) {
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
    }
    
    public void update(Articulo art) {
        Session session
                = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.update(art);
            transaction.commit();

        } catch (HibernateException he) {
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
    }
    
    public Articulo find(Articulo articulo) {
        Session session
                = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            
            articulo=(Articulo) session.get(Articulo.class, articulo.getClaveArticulo());
            transaction.commit();

        } catch (HibernateException he) {
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return articulo;
    }
    
    public List findAll() {
        Session session
                = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista=null;
        try {
            transaction.begin();
            
            Query q=session.createQuery("from Articulo");
            lista=q.list();
            transaction.commit();

        } catch (HibernateException he) {
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return lista;
    }
    
    public void delete(Articulo art) {
        Session session
                = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            
            session.delete(art);
            transaction.commit();

        } catch (HibernateException he) {
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
    }
    
    public static void main(String[] args) {
        Articulo a=new Articulo();
        a.setClaveArticulo("art01");
        a.setDescripcion("alimentos");
        a.setExistencias(100);
        a.setPrecio(250);
        ArticuloDAO d=new ArticuloDAO();
        d.create(a);
        
        System.out.println(d.findAll());
    }

}
