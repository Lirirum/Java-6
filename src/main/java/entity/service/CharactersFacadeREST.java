/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.service;

import entity.Characters;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author lirir
 */
@Stateless
@Path("entity.characters")
public class CharactersFacadeREST  {

  
     private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
   
    public List<Characters> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Characters> query = em.createNamedQuery("Characters.findAll", Characters.class);
        query.setMaxResults(10);
        List<Characters> users = query.getResultList();
        em.close();
        return users;
    }
    
    public Characters findById(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Characters> query = em.createNamedQuery("Characters.findById", Characters.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult(); 
        } catch (NoResultException e) {
            return null;
        }
    }
    
      public Characters findByFullName(String fullName) {
        EntityManager em = emf.createEntityManager();
        Characters character = null;
        String jpql = "SELECT c FROM Characters c WHERE c.fullName = :fullName";    
        try {
            character = em.createQuery(jpql, Characters.class) 
            .setParameter("fullName", fullName)  
            .getSingleResult();  
               
        }
        catch (NoResultException e) {
            System.out.println("Персонаж з fullName: " + fullName + " не знайдений.");
        }
        finally {
            em.close(); 
        }
        return character; 
    }
    
    public void createUser(Characters character) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(character);
        em.getTransaction().commit();
        em.close();
    }

     public void updateCharacter(Characters character) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (em.getFlushMode() != FlushModeType.COMMIT) {
            em.setFlushMode(FlushModeType.COMMIT);
        }

        em.merge(character);
        em.getTransaction().commit();
        em.close();
    }
     
       public void deleteCharacter(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Characters character = em.find( Characters.class, id);
        if (character != null) {
            em.remove(character);
        }
        em.getTransaction().commit();
        em.close();
    }
}
