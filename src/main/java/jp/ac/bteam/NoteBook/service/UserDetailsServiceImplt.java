package jp.ac.bteam.NoteBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.repository.UserRepository;

@Service
public class UserDetailsServiceImplt implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; // ユーザモデルのRepository
	/**
	 * ユーザの検索を行う
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("serach name : " + username);
		UserModel user = this.userRepository.findByUsernameEquals(username);
		System.out.println(user.toString());
		return user;
	}
}