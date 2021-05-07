package sivadmin.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sivadmin.models.Bruker;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class BrukerPrincipal implements UserDetails {
    private Long id;

    private String navn;

    private String brukernavn;

    @JsonIgnore
    private String epost;

    @JsonIgnore
    private String passord;

    public BrukerPrincipal(Long id, String navn, String brukernavn, String epost, String passord, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.navn = navn;
        this.brukernavn = brukernavn;
        this.epost = epost;
        this.passord = passord;
        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public static BrukerPrincipal create(Bruker bruker) {
        List<GrantedAuthority> authorities = bruker.getRoller().stream().map(role ->
                new SimpleGrantedAuthority(role.getRolleNavn().name())
        ).collect(Collectors.toList());

        return new BrukerPrincipal(
                bruker.getId(),
                bruker.getNavn(),
                bruker.getBrukernavn(),
                bruker.getEpost(),
                bruker.getPassord(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return brukernavn;
    }

    @Override
    public String getPassword() {
        return passord;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrukerPrincipal that = (BrukerPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
