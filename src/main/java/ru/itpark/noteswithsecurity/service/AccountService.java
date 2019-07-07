package ru.itpark.noteswithsecurity.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.AccountEntity;
import ru.itpark.noteswithsecurity.repository.AccountRepository;

@Primary // выбери меня (при наличии нескольких бинов, реализующих интерфейс UserDetails, будет выбран мой)
@Service // @Component -> bean -> accountService
// @Service("accountService")
// AccountServiceImpl
public class AccountService implements UserDetailsService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }

    // TODO: можете посмотреть на spring-security-thymeleaf, вместо написания своих методов
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // anonymous
        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    public boolean hasAuthority(String authority) {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(o -> o.getAuthority().equals(authority))
                ;
    }

    public boolean isOwned(int id) {
        AccountEntity entity = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return entity.getId() == 1;
        // TODO: вы можете построить здесь какую угодно логику
    }
}
