package no.njm.rest;

import no.njm.PersonRepository;
import no.njm.model.Person;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/persons")
public class PersonController {

    @Inject
    private PersonRepository persons;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Person> listPersons() {
        return persons.listPersons();
    }

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Response getPerson(@PathParam("id") Long id) {
        Optional<Person> person = persons.getPerson(id);
        if (person.isPresent()) {
            return Response.ok(person.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
