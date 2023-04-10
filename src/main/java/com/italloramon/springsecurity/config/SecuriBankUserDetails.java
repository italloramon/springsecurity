package com.italloramon.springsecurity.config;

import com.italloramon.springsecurity.model.Customer;
import com.italloramon.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecuriBankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public SecuriBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customers = customerRepository.findByEmail(username);

        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = customers.get(0).getEmail();
            password = customers.get(0).getPwd();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        return new User(userName, password, authorities);
    }
}
