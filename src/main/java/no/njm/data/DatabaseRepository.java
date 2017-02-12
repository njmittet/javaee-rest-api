package no.njm.data;

import no.njm.data.entity.Identity;
import org.jboss.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

class DatabaseRepository implements IdentityRepository {

    private static final Logger log = Logger.getLogger(DatabaseRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Identity> listIdentities() {
        return (List<Identity>) em.createNamedQuery("selectIdentities").getResultList();
    }

    @Override
    public Optional<Identity> findIdentity(long id) {
        Query query = em.createNamedQuery("selectIdentityById");
        query.setParameter("id", id);
        try {
            Identity identity = (Identity) query.getSingleResult();
            return Optional.of(identity);
        } catch (NoResultException e) {
            log.debug("Could not find person with id: " + id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Identity storeIdentity(Identity identity) {
        em.persist(identity);
        return identity;
    }
}
