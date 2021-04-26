package no.ssb.sivadmin.models.auth


import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name

    Role() {

    }

    Role(ERole name) {
        this.name = name
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    ERole getName() {
        return name
    }

    void setName(ERole name) {
        this.name = name
    }
}