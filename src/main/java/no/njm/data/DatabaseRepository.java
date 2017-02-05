package no.njm.data;

import no.njm.model.Person;
import org.jboss.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
class DatabaseRepository implements PersonRepository {

    private static final Logger log = Logger.getLogger(DatabaseRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> listPersons() {
        List<Person> persons = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT firstname, lastname FROM person");
        List<Object[]> resultList = query.getResultList();

        long i = 0;
        for (Object[] result : resultList) {
            persons.add(new Person(i, (String) result[0], (String) result[1]));
            i++;
        }
        return persons;
    }

    @Override
    public Optional<Person> getPerson(long id) {
        List<Person> filered = listPersons().stream()
                                            .filter(p -> p.getId() == id)
                                            .collect(Collectors.toList());
        if (filered.size() > 0) {
            return Optional.of(filered.get(0));
        }
        return Optional.empty();
    }
}
