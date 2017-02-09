package no.njm.data;

import no.njm.rest.model.Person;
import org.jboss.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
class DatabaseRepository implements PersonRepository {

    private static final Logger log = Logger.getLogger(DatabaseRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT id, firstname, lastname FROM person");
        List<Object[]> resultList = query.getResultList();
        resultList.forEach(record -> persons.add(personFromResult(record)));
        return persons;
    }

    @Override
    public Optional<Person> getPerson(long id) {
        Query query = entityManager.createNativeQuery("SELECT id, firstname, lastname FROM person WHERE id = :id");
        query.setParameter("id", id);
        try {
            Object[] result = (Object[]) query.getSingleResult();
            return Optional.of(personFromResult(result));
        } catch (NoResultException e) {
            log.debug("Could not find person with id: " + id);
            return Optional.empty();
        }
    }

    private Person personFromResult(Object[] result) {
        Integer id = (Integer) result[0];
        String firstName = (String) result[1];
        String lastName = (String) result[2];
        return new Person(id, firstName, lastName);
    }
}
