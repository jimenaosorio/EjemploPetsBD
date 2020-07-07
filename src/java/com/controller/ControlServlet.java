/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.beans.ServicioLocal;
import com.entities.Categoria;
import com.entities.Perfil;
import com.entities.Producto;
import com.entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.Hash;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author jimenaosorio
 */
@WebServlet(name = "ControlServlet", urlPatterns = {"/control.do"})
@MultipartConfig(location="/tmp", fileSizeThreshold = 1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)

public class ControlServlet extends HttpServlet {

    @EJB
    private ServicioLocal servicio;
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boton=request.getParameter("boton").toString();
        switch(boton)
        {
            case "login": login(request,response);
                            break;
            case "registrar": registrar(request,response);
                            break;
            case "nuevaCategoria": nuevaCategoria(request,response);
                                    break;
            case "editardatos": modificarUsuario(request,response,1);
                            break;
            case "editardatos2": modificarUsuario(request,response,2);
                            break;
            case "nuevoproducto": nuevoProducto(request,response);
                            break;
            
        }
        
    }
    
    protected void nuevoProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre=request.getParameter("nombre");
        String precioStr=request.getParameter("precio");
        String unidadStr=request.getParameter("unidad");
        String descripcion=request.getParameter("descripcion");
        String categoriaStr=request.getParameter("idcategoria");
        String errores="";
        int precio=0, unidad=0, idCategoria=0;
        InputStream stream=null;
        Part foto=request.getPart("foto");
        if(foto!=null){
            stream=foto.getInputStream();
        }
        
        if(nombre.isEmpty()) errores+="Debe ingresar el nombre.<br/>";
        if(descripcion.isEmpty()) errores+="Debe ingresar la descripcion.<br/>";
        if(precioStr.isEmpty()) errores+="Debe ingresar el precio.<br/>";
        try{
            precio=Integer.parseInt(precioStr);
        }catch(Exception ex){
            errores+="El precio debe ser un numero.<br/>";
        }
        if(unidadStr.isEmpty()) errores+="Debe ingresar las unidades.<br/>";
        try{
            unidad=Integer.parseInt(unidadStr);
        }catch(Exception ex)
        {
            errores+="Las unidades deben ser un numero.<br/>";
        }
        if(errores.isEmpty())
        {
            //Buscar la categoria
            idCategoria=Integer.parseInt(categoriaStr);
            Categoria categoria=servicio.buscarCategoria(idCategoria);
            //Crear el producto
            Producto nuevo=new Producto();
            //Agregamos los atributos
            nuevo.setNombreProducto(nombre);
            nuevo.setPrecioProducto(precio);
            nuevo.setUnidadesProducto(unidad);
            nuevo.setDescripcionProducto(descripcion);
            nuevo.setCategoria(categoria);
            if(stream!=null){
                nuevo.setFotoProducto(IOUtils.toByteArray(stream));
            }
            //Guardar el producto en la base de datos
            servicio.guardar(nuevo);
            //Guardar el producto en la lista de su categoria
            categoria.getProductoList().add(nuevo);
            servicio.sincronizar(categoria);
            
            //Mensaje
            request.setAttribute("msg", "Producto creado");
        }
        else{
            request.setAttribute("msg",errores);
        }
        request.getRequestDispatcher("admin_producto.jsp").forward(request, response);
    }
    
    protected void modificarUsuario(HttpServletRequest request, HttpServletResponse response, int clave)
            throws ServletException, IOException {
       // String correo=request.getParameter("email").toString();
        String correo2=request.getParameter("email2").toString();
        String clave1=request.getParameter("clave").toString();
        String clave2=request.getParameter("clave2").toString();
        String errores="";
        String mensajes="";
        Usuario usuario;
        if(clave==1)
            usuario=(Usuario)request.getSession().getAttribute("admin");
        else
            usuario=(Usuario)request.getSession().getAttribute("persona");
        
        //Cambio de clave
        if(!clave1.isEmpty()) //quiere cambiar la clave
        {
            if(!clave1.equals(clave2))
            {
                errores=errores.concat("Las claves ingresadas no coinciden<br/>");
            }
            else{
                usuario.setClave(Hash.md5(clave2));
                mensajes+="Se ha actualizado la clave del usuario<br/>";
            }
        }
        //Cambio de correo
        if(!correo2.isEmpty())
        {
            usuario.setEmailUser(correo2);
            mensajes+="Se ha actualizado el correo<br/>";
        }
        if(!mensajes.isEmpty()) //Si hay mensajes es porque quiere cambiar algo
        {
            //Actualizar en la base de datos
            servicio.sincronizar(usuario);
            //Actualizar la sesión
            if(clave==1)
                request.getSession().setAttribute("admin",usuario);
            else
                request.getSession().setAttribute("persona",usuario);
            //Enviamos el mensaje
            request.setAttribute("msg", mensajes);
        }
        else if(errores.isEmpty()) //No hay errores, no se actualiza nada
        {
            request.setAttribute("msg","No se han actualizado datos");
        }
        else{
            request.setAttribute("msg", errores);
        }
        if(clave==1)
            request.getRequestDispatcher("misdatos.jsp").forward(request,response);
        else
            request.getRequestDispatcher("misdatos2.jsp").forward(request,response);
    }
    
    protected void nuevaCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre=request.getParameter("nombre").toString();
        if(nombre.isEmpty()){
            //error: no hay nombre
            request.setAttribute("msg","Debe escribir el nombre de la categoria");
        }
        else{
            //Agregamos la categoria
            Categoria nueva=new Categoria();
            nueva.setNombreCategoria(nombre);
            servicio.guardar(nueva);
            request.setAttribute("msg", "Categoria creada correctamente");
        }
        request.getRequestDispatcher("categoria.jsp").forward(request,response);
    }
    
    protected void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rut=request.getParameter("rut").toString();
        String nombre=request.getParameter("nombre").toString();
        String apellido=request.getParameter("apellido").toString();
        String email=request.getParameter("email").toString();
        String clave=request.getParameter("clave").toString();
        String clave2=request.getParameter("clave2").toString();
        String errores="";
        if(rut.equals(""))
        {
            errores+="Debe ingresar el RUT<br/>";
        }
        if(nombre.equals(""))
        {
            errores+="Debe ingresar su nombre<br/>";
        }
        if(!clave.equals(clave2)){
            errores+="Las claves no coinciden<br/>";
        }
        
        //Si no hay errores
        if(errores.equals(""))
        {
            if(servicio.buscarUsuario(rut)==null) //No está en la base de datos
            {
                Usuario usuario=new Usuario();
                usuario.setRutUser(rut);
                usuario.setNombreUser(nombre);
                usuario.setApellidoUser(apellido);
                usuario.setEmailUser(email);
                usuario.setClave(Hash.md5(clave));
                Perfil perfil=servicio.buscarPerfil(2);
                usuario.setPerfil(perfil);
                servicio.guardar(usuario);
                request.setAttribute("msg","El usuario se ha registrado correctamente");
            }
            else{
                errores+="El usuario ya está registrado";
                request.setAttribute("msg",errores);
            }
        }
        else{
            request.setAttribute("msg", errores);
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
        
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rut=request.getParameter("rut").toString();
        String clave=request.getParameter("clave").toString();
        String errores="";
        if(rut.isEmpty())
        {
            errores=errores.concat("Debe ingresar su RUT <BR/>");
        }
        if(clave.isEmpty())
        {
            errores=errores.concat("Debe ingresar su CLAVE <BR/>");
        }
        if(errores.isEmpty()) //No hay errores
        {
            Usuario user=servicio.iniciarSesion(rut, Hash.md5(clave));
            if(user!=null) //Existe dentro de la base de datos
            {
                if(user.getPerfil().getNombrePerfil().equals("administrador"))
                {
                    //Crea la sesión de administrador
                    request.getSession().setAttribute("admin",user);
                }
                else{
                    //Crea la sesión del cliente
                    request.getSession().setAttribute("persona",user);
                    
                }
                //redireccionar al inicio
                response.sendRedirect("inicio.jsp");
            }
            else{
                //redireccionamos el error
                request.setAttribute("msg", "El usuario no está registrado");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
