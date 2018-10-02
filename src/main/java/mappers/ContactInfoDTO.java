/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.Person;
import java.util.List;

/**
 *
 * @author Mads
 */
public class ContactInfoDTO {
    
    private String name;
    private String email;
    private List<Integer> phones;

    public ContactInfoDTO(Person person) {
        this.name = person.getFirstName() + " " + person.getLastName();
        this.email = person.getEmail();
//        for(Phone p : person.getPhones())
//        this.phones = phones;
    }
}
