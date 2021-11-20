package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {
    private User user;
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority>authorities=new ArrayList<>();
        this.user.getRole();
        GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+this.user.getRole());
        authorities.add(authority);
        return  authorities;
    }
    
    public MyUserPrincipal(User user) {
        this.user = user;
    }
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername() ()
     */
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
