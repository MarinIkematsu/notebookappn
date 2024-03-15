package jp.ac.bteam.NoteBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; // ユーザモデルのRepository
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void signupUser(UserModel userModel) {
        UserModel user = new UserModel();
        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
    }

	public boolean authenticate(String username, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


}
