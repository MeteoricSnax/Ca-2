/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import mappers.CityInfoDTO;
import mappers.PersonDTO;

/**
 *
 * @author Mads
 */
public class Facade {
    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Adds a Person to the database
    public PersonDTO createPerson(Person person){
        PersonDTO p = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p WHERE p.id = :id");
            dQuery.setParameter("id", person.getId());
            p = (PersonDTO) dQuery.getSingleResult();
        } catch(NoResultException ex){
            return null;
        } finally {
            em.close();
        }
        return p;
    }
    
    // Returns a Person from a given id
    public PersonDTO getPerson(Long id){
        PersonDTO p = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p WHERE p.id = :id");
            dQuery.setParameter("id", id);
            p = (PersonDTO) dQuery.getSingleResult();
        } catch(NoResultException ex){
            return null;
        } finally {
            em.close();
        }
        return p;
    }
    
    // Returns a Person from a given phone number
    public List<PersonDTO> getPersonByPhone(int phone){
        List<PersonDTO> p = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(person) FROM Phone phone JOIN phone.person person WHERE phone.number = :phone");
            dQuery.setParameter("phone", phone);
            p = dQuery.getResultList();
        } catch(NoResultException ex){
            return null;
        } finally {
            em.close();
        }
        return p;
    }
    
    // Returns a list of Persons from a given hobby
    public List<PersonDTO> getPersonsByHobby(String hobby){
        List<PersonDTO> persons = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p JOIN p.hobbies h WHERE p.hobbies.name = :hobby");
            dQuery.setParameter("hobby", hobby);
        } finally {
            em.close();
        }
        return persons;
    }
    
    // Returns a list of all the cities in Denmark
    public List<String> getZipCodes(){
        List<String> zipCodes = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT ci.zip FROM CityInfo ci");
            zipCodes = dQuery.getResultList();
        } finally {
            em.close();
        }
        return zipCodes;
    }
    
    // Returns a list of Persons from a given hobby
    public List<PersonDTO> getPersonsInCity(String city){
        List<PersonDTO> persons = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p JOIN p.address a JOIN a.cityInfo ci WHERE ci.city = :city");
            dQuery.setParameter("city", city);
        } finally {
            em.close();
        }
        return persons;
    }
    
    // Updates a Person with a given ID
    public Person updatePerson(Person person){
        Person p = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.find(Person.class, person.getId());
            em.merge(person);
            em.getTransaction().commit();
            p = em.find(Person.class, person.getId());
        } finally {
            em.close();
        }
        return p;
    }
    
    // Deletes a Person with a given ID
    public PersonDTO deletePerson(Long id){
        Person person = null;
        PersonDTO p = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            person = em.find(Person.class, id);
            em.remove(person);
//            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p WHERE p.id = :id");
//            dQuery.setParameter("id", id);
//            p = (PersonDTO) dQuery.getSingleResult();
            em.getTransaction().commit();
        } catch(NoResultException ex){
//            return null;
        } finally {
            em.close();
        }
        return p;
    }
    
    // Returns a list containing all Persons
    public List<PersonDTO> getAllPersons(){
        List<PersonDTO> persons = null;
        EntityManager em = getEntityManager();
        try {
            Query dQuery = em.createQuery("SELECT NEW mappers.PersonDTO(p) FROM Person p");
            persons = dQuery.getResultList();
        } finally {
            em.close();
        }
        return persons;
    }
}
