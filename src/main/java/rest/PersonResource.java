/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import exceptions.PersonNotFoundException;
import exceptions.ValidationErrorException;
import facade.Facade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mappers.PersonDTO;

/**
 * REST Web Service
 *
 * @author Mads
 */
@Path("person")
public class PersonResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Facade f = new Facade(Persistence.createEntityManagerFactory("jpapu"));
    
    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsArray() {
        return Response.ok().entity(gson.toJson(f.getAllPersons())).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Long id) throws PersonNotFoundException {
            if(f.getPerson(id) == null){
                throw new PersonNotFoundException("Unable to find a person with the provided ID");
            }
            return Response.ok().entity(gson.toJson(f.getPerson(id))).build();
    }
    
    @GET
    @Path("/phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("number") int number) throws PersonNotFoundException{
            if(f.getPersonByPhone(number).isEmpty()){
                throw new PersonNotFoundException("Unable to find a person with the provided phone number");
            }
            return Response.ok().entity(gson.toJson(f.getPersonByPhone(number))).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content) throws ValidationErrorException {
            Person person = gson.fromJson(content, Person.class);
            if(person.getFirstName().equals("") || person.getLastName().equals("")){
                throw new ValidationErrorException("First Name or Last Name is missing");
            }
            f.createPerson(person);
            return Response.ok().entity(gson.toJson(person)).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(String content) throws ValidationErrorException, PersonNotFoundException {
            Person person = gson.fromJson(content, Person.class);
            if(person.getEmail().equals("") || person.getFirstName().equals("") || person.getLastName().equals("")){
                throw new ValidationErrorException("Email, First Name or Last Name is missing");
            }
            if(f.getPerson(person.getId()) == null){
                throw new PersonNotFoundException("Unable to delete. No person with the provided ID exists");
            }
            return Response.ok().entity(gson.toJson(f.updatePerson(person))).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") Long id) throws PersonNotFoundException {
            if(f.getPerson(id) == null){
                throw new PersonNotFoundException("Could not delete. No person with provided id exists");
            }
            return Response.ok().entity(gson.toJson(f.deletePerson(id))).build();
    }
    
}
