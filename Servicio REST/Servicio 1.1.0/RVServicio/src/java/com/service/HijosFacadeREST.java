/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.entidades.Hijos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ari
 */
@Stateless
@Path("com.entidades.hijos")
public class HijosFacadeREST extends AbstractFacade<Hijos> {

    @PersistenceContext(unitName = "RVServicioPU")
    private EntityManager em;

    public HijosFacadeREST() {
        super(Hijos.class);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Hijos entity) {
        super.create(entity);
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Hijos entity) {
        super.edit(entity);
    }

    @POST
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hijos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @POST
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hijos> findAll() {
        return super.findAll();
    }

    @POST
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hijos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @POST
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
