package no.njm.rest;

import no.njm.data.IdentityRepository;
import no.njm.data.entity.Identity;
import no.njm.rest.model.Person;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/persons")
public class PersonController {

    @Inject
    private IdentityRepository repository;

    @GET
    @Wrapped(element = "persons")
    @Produces({"application/xml", "application/json"})
    public Response getPersons() {
        List<Identity> people = repository.listIdentities();
        List<Person> users = identitiesToPersons(people);

        // Eliminate type erasure when wrapping List<> in a a Response object
        GenericEntity<List<Person>> entity = new GenericEntity<List<Person>>(users) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Response getPerson(@PathParam("id") Long id) {
        Optional<Identity> individual = repository.findIdentity(id);
        if (individual.isPresent()) {
            Person person = identityToPerson(individual.get());
            return Response.ok(person).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response storePerson(@FormParam("firstname") String firstName,
                                @FormParam("lastname") String lastName,
                                @Context UriInfo uriInfo) {
        Identity identity = repository.storeIdentity(new Identity(firstName, lastName));
        return Response.created(identityUri(uriInfo, identity)).build();
    }

    private URI identityUri(@Context UriInfo uriInfo, Identity identity) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(identity.getId()));
        return builder.build();
    }

    private List<Person> identitiesToPersons(List<Identity> people) {
        List<Person> users = new ArrayList<>();
        people.forEach(i -> users.add(identityToPerson(i)));
        return users;
    }

    private Person identityToPerson(Identity i) {
        return new Person(i.getId(), i.getFirstName(), i.getLastName());
    }
}
