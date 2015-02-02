/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izv.controlador;

import com.izv.hibernate.Fotos;
import com.izv.modelo.ModeloFoto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Sadarik
 */
@WebServlet(name = "ControladorFoto", urlPatterns = {"/controlfoto"})
@MultipartConfig
public class ControladorFoto extends HttpServlet {

      private ArrayList<Fotos> fotos(String id) {
        List<Fotos> imagenes = ModeloFoto.get();
        ArrayList<Fotos> lista = new ArrayList<Fotos>();
        for (int i = 0; i < imagenes.size(); i++) {
            Fotos fo = imagenes.get(i);
            if (fo.getNombre().indexOf("inmueble_" + id + "_") != -1) {
                lista.add(fo);
            }
        }
        return lista;
    }
    
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
       
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;
        
              //... Aquí se empieza a tomar la decisión
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
       // view = request.getParameter("view");
        
        //diferencia entre forward o setredirect ---- si se modifica se hace setredirect, si no un forward
        if(target.equals("foto") && op.equals("select") && action.equals("view") /*|| view ==null*/){
           forward=true;
           destino = "WEB-INF/inmueble/gestionarFotos.jsp";                       
               String id = request.getParameter("id");
               ArrayList<Fotos> lista = fotos(id);
               request.setAttribute("id", id);
               request.setAttribute("fotos", lista);
                                        
            
        }else if(target.equals("foto") && op.equals("insert") && action.equals("op")){
          forward = false;
          String id = request.getParameter("id");
          response.setContentType("text/html;charset=UTF-8");   
          Calendar calendario = new GregorianCalendar();
          Date date = calendario.getTime();
          SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
          String fecha = formatoFecha.format(date);        
          String titulo = "inmueble_" + id + "_" + fecha ; 
          Part archivoPost = request.getPart("archivo");
          String carpeta = getServletContext().getRealPath("/") + "fotos/";
          InputStream input = archivoPost.getInputStream();
          try {
              OutputStream out = new FileOutputStream(carpeta + titulo +".jpg");
              byte[] buffer = new byte[2048];
              int leido;
              try {
                  while ((leido = input.read(buffer)) != -1) {
                       out.write(buffer, 0, leido);
                 }
             } catch (IOException e) {
             } finally {
                out.close();
             }
             } catch (IOException e) {
             } finally {
                input.close();
                Fotos fo = new Fotos();
                fo.setIdinmueble(Integer.parseInt(id));
                fo.setNombre(titulo + ".jpg");
                ModeloFoto.insert(fo);

             }
         destino = "controlfoto?target=foto&op=select&action=view&id=" + request.getParameter("id");
                      
        }   else  if (target.equals("foto") && op.equals("delete") && action.equals("op")) {
          Fotos fo = ModeloFoto.get(request.getParameter("id"));
          ModeloFoto.delete(fo);
          File file = new File(getServletContext().getRealPath("/") + "fotos/" + request.getParameter("foto"));
          file.delete();
          destino = "controlfoto?target=foto&op=select&action=view&id=" + request.getParameter("idinmueble");
                                            
       }     
        if(forward){
            request.getRequestDispatcher(destino).forward(request, response);
        }else {
            response.sendRedirect(destino);
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
