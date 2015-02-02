/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.izv.controlador;

import com.izv.hibernate.Fotos;
import com.izv.hibernate.Inmueble;
import com.izv.modelo.ModeloFoto;
import com.izv.modelo.ModeloInmueble;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 2dam
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
@MultipartConfig
public class Controlador extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
     //1º recibir datos para decidir "gerParameter"
              //2º decidir quien sabe o debe responder
              //3º generar informacion para el que responde "setAttribute()"
              //4º decidir como transfiero el control (2 formas)
              //   redireccion -> en caso de modificacion (insert, delete, update...)
              //   forward dispatch
              //5º transferir el control
              //op: insert, delete, update, select
              //targer: tabla
              //action: do, view
              //view: vista
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;
        
              //... Aquí se empieza a tomar la decisión
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
       // view = request.getParameter("view");
        
        //diferencia entre forward o setredirect ---- si se modifica se hace setredirect, si no un forward
        if(target.equals("inmueble") && op.equals("select") && action.equals("view") /*|| view ==null*/){
            forward=true;
            destino = "WEB-INF/inmueble/index.jsp";
            request.setAttribute("datos", ModeloInmueble.get());
            
        }else if(target.equals("inmueble") && op.equals("delete") && action.equals("op")){
            
            forward = false;
            String id = request.getParameter("id");
                        ModeloInmueble.delete(id);
                        
          ArrayList<Fotos> lista = fotos(id);
                for (int i = 0; i < lista.size(); i++) {
                    Fotos fo=lista.get(i);
                    File file = new File(getServletContext().getRealPath("/") + "fotos/"+fo.getNombre());
                    file.delete();
                    ModeloFoto.delete(fo);
                }
            destino = "control?target=inmueble&op=select&action=view";
         
        }else if (target.equals("inmueble")&& op.equals("insert")&&action.equals("view")){
            forward=true;
            destino = "WEB-INF/inmueble/insert.jsp";
            
        } else if(target.equals("inmueble")&& op.equals("insert")&&action.equals("op")){
            
            forward=false;
            Inmueble inm = new Inmueble();
            inm.setLocalidad(request.getParameter("campo1"));
            inm.setDireccion(request.getParameter("campo2"));
            inm.setTipo(request.getParameter("campo3"));
            inm.setPrecio(new BigDecimal(request.getParameter("campo4")));
            Calendar c = Calendar.getInstance();
            inm.setFechaalta(c.getTime());
            inm.setUsuario(request.getParameter("campo5"));
            ModeloInmueble.insert(inm);
            destino = "control?target=inmueble&op=select&action=view";
            
            
        } //Modificar
        else if(target.equals("inmueble") && op.equals("edit") && action.equals("view")){
            forward = true;
            request.setAttribute("inmueble", ModeloInmueble.get(request.getParameter("id")));
            destino = "WEB-INF/inmueble/edit.jsp";
        }
        else if(target.equals("inmueble") && op.equals("edit") && action.equals("op")){
            forward = false;          
            Inmueble inm = ModeloInmueble.get(request.getParameter("id"));
            inm.setLocalidad(request.getParameter("campo1"));
            inm.setDireccion(request.getParameter("campo2"));
            inm.setTipo(request.getParameter("campo3"));
            inm.setPrecio(new BigDecimal(request.getParameter("campo4")));
            ModeloInmueble.edit(inm);
             destino = "control?target=inmueble&op=select&action=view";
  
            
        } 
    
        
        //transferencia
        if(forward){
            request.getRequestDispatcher(destino).forward(request, response);
        }else {
            response.sendRedirect(destino);
        }
        
        }
    
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
