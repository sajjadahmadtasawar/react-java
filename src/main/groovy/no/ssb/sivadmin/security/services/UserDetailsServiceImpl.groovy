package no.ssb.sivadmin.security.services

import no.ssb.sivadmin.models.auth.User
import no.ssb.sivadmin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Service
class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository

    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow({ -> new UsernameNotFoundException("User Not Found with username: " + username) })

        return UserDetailsImpl.build(user)
    }

}