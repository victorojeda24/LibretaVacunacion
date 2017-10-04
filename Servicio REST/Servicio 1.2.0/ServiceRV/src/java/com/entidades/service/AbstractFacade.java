/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades.service;

import java.util.List;
import javax.persistence.EntityManager;
import com.entidades.Hijos;
import com.entidades.Usuarios;
import java.util.LinkedList;
import javax.persistence.Query;

/**
 *
 * @author ari
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    private EntityManager em;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public Usuarios buscarUsuario(Integer id) {
        return (Usuarios) find(id);
    }
    
    public List<Hijos> hijosIdUsuarios(Integer id) {

        Usuarios usuario = buscarUsuario(id);
        if (usuario == null) {
            return new LinkedList<Hijos>();
        }

        Query query = em.createQuery("SELECT h FROM Hijos h JOIN Usuarios usu on h.id_usuario = usu.id;")
                .setParameter("id_usuario", usuario);
        List<Hijos> list = query.getResultList();
        return list;
    }
    
}
