package no.njm.data;

import no.njm.data.entity.Identity;
import org.jboss.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Singleton
class DatabaseRepository implements IdentityRepository {

    private static final Logger log = Logger.getLogger(DatabaseRepository.class);

    @PersistenceContext
    private EntityManager em;

    public List<Identity> listIdentities() {
        return (List<Identity>) em.createNativeQuery("SELECT id, firstname, lastname FROM identity", Identity.class)
                                  .getResultList();
    }

    @Override
    public Optional<Identity> findIdentity(long id) {
        Query query = em.createNativeQuery("SELECT id, firstname, lastname FROM identity WHERE id = :id", Identity.class);
        query.setParameter("id", id);
        try {
            Identity identity = (Identity) query.getSingleResult();
            return Optional.of(identity);
        } catch (NoResultException e) {
            log.debug("Could not find person with id: " + id);
            return Optional.empty();
        }
    }
}
