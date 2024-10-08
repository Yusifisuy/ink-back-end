package az.inkstudio.backend;

import az.inkstudio.backend.entity.User;
import az.inkstudio.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class InkBackEndApplication {

    private final UserRepository userRepository;

    @PostConstruct
    public void createUser(){
        List<User> users = Stream.of(
                new User(1,"Vusal","vusalbayramli@gmail.com","{noop}12345","+994505338811",null)

        ).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(InkBackEndApplication.class, args);
    }

}
