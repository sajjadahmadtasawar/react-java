package no.ssb.sivadmin.payload.request

import java.util.Set

import javax.validation.constraints.*

 class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username

    @NotBlank
    @Size(max = 50)
    @Email
    private String email

    private Set<String> role

    @NotBlank
    @Size(min = 6, max = 40)
    private String password

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

     Set<String> getRole() {
        return this.role
    }

     void setRole(Set<String> role) {
        this.role = role
    }
}