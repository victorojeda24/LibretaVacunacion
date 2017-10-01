/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ari
 */
public class UsuarioServicio {
    
    private static final String INSERT_USUARIO = "insert into usuarios (correo, nombre) " +
                                                "values ('?', '?');";
    
    private static final String DELETE_HIJO_USUARIO = "delete from hijos where id_usuario = '?';";
    
    private static final String DELETE_USUARIO = "delete from usuarios where id = '?';";
    
    private static final String UPDATE_USUARIO = "update usuarios set correo='?', nombre='?' " +
                                                "where id = ?;";
    
    private static final String USUARIOS = "select nombre, correo from usuarios;";
    
    private static final String USUARIO_ID = "select nombre, correo from usuarios " +
                                                "where id = ";
    
    private static final String HIJOS_USUARIO = "select cod_cedula, nombre, apellido " +
                                                "from hijos " +
                                                "where id_usuario = ";
    
    private static final String REGISTROS = "select id_registro, id_vacuna, responsable " +
                                            "from registros " + "where cod_cedula = ";
    
    private static final String USUARIO_EMAIL = "select * from usuarios " +
                                                "where correo = ";
    
    Conexion con;
    Connection conex;
    
    public UsuarioServicio() {
        con = new Conexion();
        conex = null;
    }

    void addUsuario(Usuario u) throws SQLException, ClassNotFoundException {    
//        String sql="insert into \"Usuarios\" (nombre,correo) values(?,?)";

        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(INSERT_USUARIO);
        pst.setString(1,u.getCorreo());
        pst.setString(2,u.getNombre());   
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    }
    //==========================================================================
    
    public void deleteUser(int id) throws ClassNotFoundException, SQLException {

        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(DELETE_HIJO_USUARIO);
        
        pst.setInt(1, id);
        pst.executeUpdate();
        
        pst = conex.prepareStatement(DELETE_USUARIO);
        
        pst.setInt(1, id);
        pst.equals(pst);
        
        pst.close();
        conex.close();
        con.cerrarBD();
    }
    
    /**
     * Esta función sirve para poder editar a un Usuario.
     * @param id Se espera un valor del tipo INT que sea el ID para el usuario
     * a identificar.
     * @param user Se espera que sea un objeto del tipo Usuario.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void editUser(int id, Usuario user) throws SQLException, ClassNotFoundException {
        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(UPDATE_USUARIO);
        pst.setString(1, user.getNombre());
        pst.setString(2, user.getCorreo());
        pst.setInt(3, id);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
    
    /**
     * Esta función no espera algún parámetro para buscar todos los Usuarios
     * @return La lista de Usuarios.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Usuario> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Usuario> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery(USUARIOS);
        while (rs.next()) {
            Usuario user = new Usuario ();
            user.setNombre(rs.getString("nombre"));
            user.setCorreo(rs.getString("correo"));
            lista.add(user);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
    /**
     * Esta función busca todos los hijos correspondientes a un usuario si es
     * que tiene algún hijo.
     * @param userId Se espera un valor del tipo INT que se único del Usuario.
     * @return La lista de hijos del usuario si es que tiene alguno.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Hijo> getHijos(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Hijo> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = HIJOS_USUARIO + userId + ";";
        ResultSet resul = st.executeQuery(sql);
        while (resul.next()) {
            Hijo hijo = new Hijo();
            hijo.setCod_cedula(resul.getInt("cod_cedula"));
            hijo.setNombre(resul.getString("nombre"));
            hijo.setApellido(resul.getString("apellido"));
            lista.add(hijo);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
    /**
     * Esta funciión busca todos los registros correspondientes a un Usuario.
     * @param userId Se espera un valor númerico que identifica de forma única al
     * usuario.
     * @return La listas de registros que correspondente a un usuario.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Registro> getRegistros(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Registro> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = REGISTROS + userId + ";";
        ResultSet resul = st.executeQuery(sql);
        while (resul.next()) {
            Registro registro = new Registro();
            registro.setId_registro(resul.getInt("id_registro"));
            registro.setId_vacuna(resul.getInt("id_vacuna"));
            registro.setResponsable(resul.getString("responsable"));
            lista.add(registro);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
    /**
     * Falta aún revisar aún para poder ultilizar está función
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Fecha> getFechas(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Fecha> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = "select DISTINCT(coalesce(rv.fecha::varchar,'')) fecha "
                  
                   
                   + "from \"RegistroVacuna\" rv "
                   + "join \"Vacunas\" v on v.id_vacuna=rv.id_vacuna "
                   + "join \"Hijos\" h on rv.id_hijo = h.id_hijo "
                   + "where h.id_usuario = "+userId + "and rv.estado = 0";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fecha tm = new Fecha();
            
            tm.setFecha(rs.getString("fecha"));
            
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
    /**
     * Se retorna el usuario identificado por su ID
     * @param id Se espera que tome un valor númerico y para identificar por el
     * mismo a un usuario.
     * @return El usuario identificado.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Usuario getUserById(int id) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet resul = st.executeQuery(USUARIO_ID + id + ";");
        Usuario user = new Usuario();
        while (resul.next()) {
            user.setNombre(resul.getString("nombre"));
            user.setCorreo(resul.getString("correo"));
        }
        conex.close();
        con.cerrarBD();
        return user;
    }
    
    /**
     * Se valida con el email o correo que ingresó este corresponda con lo que
     * se tiene en base de datos
     * @param correo Se espera un email o correo del tipo ejemplo@gmail.com
     * @return El usuario con sus datos o bien retorna un usuario nulo.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */    
    public Usuario isUser(String correo) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Usuario usuario = new Usuario();
        Statement st = conex.createStatement();
        ResultSet resul = st.executeQuery(USUARIO_EMAIL + correo + ";");
        if (resul.next()) {
            usuario.setId(resul.getInt(1));
            usuario.setCorreo(resul.getString(2));
            usuario.setNombre(resul.getString(3));
        }
        else {
            usuario = null;
        }
        return usuario;
    }
}
