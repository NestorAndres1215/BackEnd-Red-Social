package com.na.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.na.backend.message.SeguridadMessage;
import com.na.backend.model.Login;
import com.na.backend.repository.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException(SeguridadMessage.USUARIO_NO_ENCONTRADO.getMensaje());
        }
        return login;
    }

}
