package no.njm;

import no.njm.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Created by njmittet on 31/01/2017.
 */
public interface PersonRepository {

    List<Person> listPersons();

    Optional<Person> getPerson(long id);
}
