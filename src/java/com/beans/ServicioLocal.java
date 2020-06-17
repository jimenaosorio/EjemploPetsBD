/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.ejb.Local;
import com.entities.*;
import java.util.List;

/**
 *
 * @author jimenaosorio
 */
@Local
public interface ServicioLocal {

    List<Perfil> getPerfiles();

    List<Producto> getProductos();

    Usuario iniciarSesion(String rut, String clave);

    Usuario buscarUsuario(String rut);

    Perfil buscarPerfil(int id);

    void guardar(Object o);

    List<Categoria> getCategorias();

    void sincronizar(Object o);

    Categoria buscarCategoria(int id);
    
}
