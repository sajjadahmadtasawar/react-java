package no.ssb.sivadmin.payload.request

import javax.validation.constraints.NotBlank

 class LoginRequest {
    @NotBlank
    private String username

    @NotBlank
    private String password

     String getUsername() {
        return username
    }

     void setUsername(String username) {
        this.username = username
    }

     String getPassword() {
        return password
    }

     void setPassword(String password) {
        this.password = password
    }
}