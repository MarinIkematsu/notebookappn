package jp.ac.bteam.NoteBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.bteam.NoteBook.models.NoteModel;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {

	List<NoteModel> findByDateIdAndUserId(Long dateId, Long userId);
}