package no.ssb.sivadmin.payload.response

class JwtResponse {
    private String token
    private String type = "Bearer"
    private Long id
    private String username
    private String email
    private List<String> roles

    JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken
        this.id = id
        this.username = username
        this.email = email
        this.roles = roles
    }

    String getAccessToken() {
        return token
    }

    void setAccessToken(String accessToken) {
        this.token = accessToken
    }

    String getTokenType() {
        return type
    }

    void setTokenType(String tokenType) {
        this.type = tokenType
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    List<String> getRoles() {
        return roles
    }
}