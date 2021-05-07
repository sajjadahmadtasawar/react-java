package sivadmin.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sivadmin.models.Bruker;
import sivadmin.models.Roller;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class BrukerInfo implements UserDetails {
    private Long id;

    private String navn;

    private String brukernavn;

    @JsonIgnore
    private String epost;

    private Boolean erAdmin;


    public BrukerInfo(Long id, String navn, String brukernavn, String epost, Collection<? extends GrantedAuthority> authorities, Boolean erAdmin) {
        this.id = id;
        this.navn = navn;
        this.brukernavn = brukernavn;
        this.epost = epost;
        this.authorities = authorities;
        this.erAdmin = erAdmin;
    }

    public BrukerInfo(Long id, String navn, String brukernavn, String epost, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.navn = navn;
        this.brukernavn = brukernavn;
        this.epost = epost;
        this.authorities = authorities;

        Boolean erAdmin = false;
        if(authorities.contains(Roller.ROLE_ADMIN)) {
            erAdmin = true;
        }

        this.erAdmin = erAdmin;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public static BrukerInfo create(Bruker bruker) {
        List<GrantedAuthority> authorities = bruker.getRoller().stream().map(role ->
                new SimpleGrantedAuthority(role.getRolleNavn().name())
        ).collect(Collectors.toList());

        Boolean erAdmin = false;
        if(authorities.contains(Roller.ROLE_ADMIN)) {
            erAdmin = true;
        }

        return new BrukerInfo(
                bruker.getId(),
                bruker.getNavn(),
                bruker.getBrukernavn(),
                bruker.getEpost(),
                authorities,
                erAdmin
        );
    }

    @Override
    public String getUsername() {
        return brukernavn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
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
        BrukerInfo that = (BrukerInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
