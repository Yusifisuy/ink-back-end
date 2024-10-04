package az.inkstudio.backend.service;

import az.inkstudio.backend.entity.User;
import az.inkstudio.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User user) {
        return userRepository.save(user);
    }

    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFullname(newUser.getFullname());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setEmail(newUser.getEmail());
            userRepository.save(existingUser);
            return existingUser;
        }else{
            return null;
        }
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
