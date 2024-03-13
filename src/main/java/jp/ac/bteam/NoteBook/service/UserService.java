package jp.ac.bteam.NoteBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public void updateUser(UserModel user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
