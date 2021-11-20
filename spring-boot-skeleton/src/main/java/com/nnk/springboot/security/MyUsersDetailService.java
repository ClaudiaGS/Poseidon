package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class MyUsersDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(String) 
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName + "is not allowed to access this page");
        }
        return new MyUserPrincipal(user);
    }
}
