/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.Persistence;
import mappers.PersonDTO;

/**
 *
 * @author Mads
 */
public class FacadeTest {
    public static void main(String[] args) {
        Facade f = new Facade(Persistence.createEntityManagerFactory("jpapu"));
        
        // Test create Person
//        System.out.println("Test createPerson(): ");
//        System.out.println(f.createPerson(new Person("someemail", "Egon", "Hansen")));
//        System.out.println(f.createPerson(new Person("randomemail", "Henrik", "Petersen")));       
        
        // Test get Person
        System.out.println("Test getPerson(): ");
        System.out.println(f.getPerson(1L));
        System.out.println(f.getPerson(2L) + "\n");
        
        // Test get Person by phone
        System.out.println("Test getPersonByPhone(): ");
        System.out.println(f.getPersonByPhone(12345678) + "\n");
        
        // Test update person
//        System.out.println("Test updatePerson(): ");
//        System.out.println(f.updatePerson(new Person (1l, "someemail", "Hans", "Hansen")));
        
        // Test delete person
//        System.out.println("Test deletePerson(): ");
//        System.out.println(f.deletePerson(1L) + "\n");
        
        // Test get all Persons
        System.out.println("Test getAllPersons(): ");
        List<PersonDTO> persons = f.getAllPersons();
        for(PersonDTO p : persons){
            System.out.println(p);
        }
        System.out.println("");
    }
}
