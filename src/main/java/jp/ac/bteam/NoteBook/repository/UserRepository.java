package jp.ac.bteam.NoteBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.bteam.NoteBook.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}

