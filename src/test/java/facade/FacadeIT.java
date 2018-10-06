/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import mappers.PersonDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class FacadeIT {
    
    private Facade f;
    
    public FacadeIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // testpu must be initialized with testDB.sql in persistence.xml
        // <property name="javax.persistence.sql-load-script-source" value="Scripts/TestDB.sql"/>
        Persistence.generateSchema("testpu", null);
        f = new Facade(Persistence.createEntityManagerFactory("testpu"));
        System.out.println("setup");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class Facade.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        Facade instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPerson method, of class Facade.
     */
    @Test
    public void testCreatePerson() {
        System.out.println("createPerson");
        // the first available id in test database.
        long id = 14;
        Person person = new Person(id, "jan@jan.com", "Jan", "Jansen");
        Facade instance = f;
        PersonDTO p = new PersonDTO(person);
        instance.createPerson(person);
        PersonDTO expResult = p;
        PersonDTO result = instance.getPerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPerson method, of class Facade. 2 test cases, one where the person with the given id exists, and one where the person doesn't exist.
     */
    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        Long id = new Long(1);
        Facade instance = f;
        PersonDTO expResult = new PersonDTO(new Person(id, "Lars@Somewhere.com", "Lars", "Larsen"));
        PersonDTO result = instance.getPerson(id);
        // expresult and result are differnt objects although content is the same.
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonByPhone method, of class Facade. 3 test cases, one where the person with the given phone number exists, 
     * one where there is no person with that phone number, and one where the phone number doesn't exist.
     */
    @Test
    public void testGetPersonByPhone() {
        System.out.println("getPersonByPhone");
        int phone = 56565363;
        Facade instance = f;
        Long expResult = new Long(4);
        List<PersonDTO> result = (List<PersonDTO>) instance.getPersonByPhone(phone);
        assertEquals(expResult, result.get(0).getId());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPersonsByHobby method, of class Facade. 3 test cases, one where the persons with the given hobby are found,
     * one where there are no persons found with that hobby, and one where the hobby doesn't exist.
     */
    @Test
    public void testGetPersonsByHobby() {
        System.out.println("getPersonsByHobby");
        String hobby = "";
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZipCodes method, of class Facade.
     */
    @Test
    public void testGetZipCodes() {
        System.out.println("getZipCodes");
        Facade instance = null;
        List<String> expResult = null;
        List<String> result = instance.getZipCodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsInCity method, of class Facade. 3 test cases, one where the persons are found for the given city, 
     * one where the city has no persons, and one where the city doesn't exist
     */
    @Test
    public void testGetPersonsInCity() {
        System.out.println("getPersonsInCity");
        String city = "";
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsInCity(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePerson method, of class Facade. 2 test cases, one where the person gets updated, and one where the person doesn't exist.
     */
    @Test
    public void testUpdatePerson() {
        System.out.println("updatePerson");
        Person person = null;
        Facade instance = null;
        Person expResult = null;
        Person result = instance.updatePerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class Facade. We have 2 cases, one where the person is deleted, and one where the person doesn't exist.
     */
    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        Long id = null;
        Facade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.deletePerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // Check if person has actually disappeared from database.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPersons method, of class Facade.
     */
    @Test
    public void testGetAllPersons() {
        System.out.println("getAllPersons");
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getAllPersons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByZipcode method, of class Facade.
     */
    @Test
    public void testGetPersonsByZipcode() {
        System.out.println("getPersonsByZipcode");
        String zip = "";
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByZipcode(zip);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByName method, of class Facade.
     */
    @Test
    public void testGetPersonsByName() {
        System.out.println("getPersonsByName");
        String firstName = "";
        String lastName = "";
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByName(firstName, lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
