package no.njm.data;

import no.njm.data.entity.Identity;

import java.util.List;
import java.util.Optional;

public interface IdentityRepository {

    List<Identity> listIdentities();

    Optional<Identity> findIdentity(long id);

    Identity storeIdentity(Identity identity);
}
