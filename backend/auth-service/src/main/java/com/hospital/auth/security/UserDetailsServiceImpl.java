package com.hospital.auth.security;

import com.hospital.auth.entity.User;
import com.hospital.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
/**
 * Implémentation de UserDetailsService pour charger les données utilisateur depuis la base de données.
 * Utilisé par Spring Security pour l'authentification.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Charge un utilisateur par son nom d'utilisateur.
     * Vérifie également si le compte est actif.
     * @param username Le nom d'utilisateur.
     * @return L'objet UserDetails de Spring Security.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé ou est inactif.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        // Sécurité supplémentaire : Empêcher la connexion des comptes inactifs
        if (!user.getActif()) {
            throw new UsernameNotFoundException("User is inactive");
        }
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getActif())
                .build();
    }
    
    /**
     * Convertit le rôle de l'utilisateur en authority Spring Security.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
}

