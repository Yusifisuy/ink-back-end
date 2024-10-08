package az.inkstudio.backend.security;

import az.inkstudio.backend.entity.User;
import az.inkstudio.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByFullname(fullname)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found by: "+fullname));
        User user = (User) userRepository.findByEmail("User not found with email: " + email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());
    }
}