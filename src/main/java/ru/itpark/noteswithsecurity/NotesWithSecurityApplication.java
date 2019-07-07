package ru.itpark.noteswithsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.noteswithsecurity.entity.AccountEntity;
import ru.itpark.noteswithsecurity.repository.AccountRepository;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class NotesWithSecurityApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(NotesWithSecurityApplication.class, args);

        var repository = context.getBean(AccountRepository.class);
        var encoder = context.getBean(PasswordEncoder.class);

        // регистрация
        repository.saveAll(
                List.of(
                        new AccountEntity(
                                0,
                                "admin",
                                encoder.encode("admin"),
                                List.of(
                                        new SimpleGrantedAuthority("ADD"),
                                        new SimpleGrantedAuthority("REMOVE")
                                ),
                                true,
                                true,
                                true,
                                true
                        ),
                        new AccountEntity(
                                0,
                                "user",
                                encoder.encode("user"),
                                Collections.emptyList(),
                                true,
                                true,
                                true,
                                true
                        )
                )
        );
    }

}

