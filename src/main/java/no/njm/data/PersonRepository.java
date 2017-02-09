package no.njm.data;

import no.njm.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> getPersons();

    Optional<Person> getPerson(long id);
}
