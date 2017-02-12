package no.njm.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="identity")
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
