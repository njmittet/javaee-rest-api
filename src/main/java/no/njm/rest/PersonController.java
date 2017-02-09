package no.njm.rest;

import no.njm.data.PersonRepository;
import no.njm.model.Person;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/persons")
public class PersonController {

    @Inject
    private PersonRepository repository;

    @GET
    @Wrapped(element = "persons")
    @Produces({"application/xml", "application/json"})
    public Response getPersons() {
        List<Person> persons = repository.listPersons();

        // Eliminate type erasure when wrapping List<> in a a Response object
        GenericEntity<List<Person>> entity = new GenericEntity<List<Person>>(persons) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Response getPerson(@PathParam("id") Long id) {
        Optional<Person> person = repository.getPerson(id);
        if (person.isPresent()) {
            return Response.ok(person.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
