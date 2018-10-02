/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.Person;

/**
 *
 * @author Mads
 */
public class PersonDTO {
    
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
}
