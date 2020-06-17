/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entities.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jimenaosorio
 */
@Stateless
public class Servicio implements ServicioLocal {

    @PersistenceContext(unitName = "EjemploPetsBDPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    

    @Override
    public List<Perfil> getPerfiles() {
        return em.createQuery("select p from Perfil p").getResultList();
    }

    @Override
    public List<Producto> getProductos() {
        return em.createQuery("select p from Producto p").getResultList();
    }
    
    

    @Override
    public Usuario iniciarSesion(String rut, String clave) {
        try{//Enviamos los dos parámetros de entrada
            return (Usuario) em.createNamedQuery("Usuario.iniciarSesion",Usuario.class).setParameter("rutUser", rut).setParameter("clave", clave).getSingleResult();
            
            
        }catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public Usuario buscarUsuario(String rut) {
        return em.find(Usuario.class, rut);
    }

    @Override
    public Perfil buscarPerfil(int id) {
        return em.find(Perfil.class,id);
    }

    @Override
    public void guardar(Object o) {
        em.persist(o);
    }

    @Override
    public List<Categoria> getCategorias() {
       return em.createNamedQuery("Categoria.findAll").getResultList();
    }

    @Override
    public void sincronizar(Object o) { //update
        em.merge(o);
        em.flush();
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return em.find(Categoria.class, id);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
