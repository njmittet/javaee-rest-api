package no.njm.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="identity")
@NamedNativeQueries({
        @NamedNativeQuery(name = "selectIdentities", query = "SELECT id, firstname, lastname FROM identity", resultClass = Identity.class),
        @NamedNativeQuery(name = "selectIdentityById", query = "SELECT id, firstname, lastname FROM identity WHERE id = :id", resultClass = Identity.class)
})
public class Identity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public Identity() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
