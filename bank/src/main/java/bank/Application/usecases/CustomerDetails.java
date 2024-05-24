package bank.Application.usecases;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bank.Domain.Customer;

public class CustomerDetails implements UserDetails {

    private Customer customer;

    public CustomerDetails(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.roles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customer.password();
    }

    @Override
    public String getUsername() {
        return customer.email();
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

}
