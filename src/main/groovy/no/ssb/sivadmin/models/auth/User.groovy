package no.ssb.sivadmin.models.auth


import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "brukere")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id

    @NotBlank
    @Size(max = 20)
    @Column(unique=true)
    private String username

    @NotBlank
    @Size(max = 50)
    @Email
    private String email

    @NotBlank
    @Size(max = 120)
    private String password

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>()

    User() {
    }

    User(String username, String email, String password) {
        this.username = username
        this.email = email
        this.password = password
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Set<Role> getRoles() {
        return roles
    }

    void setRoles(Set<Role> roles) {
        this.roles = roles
    }
}