package jp.ac.bteam.NoteBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.bteam.NoteBook.models.DateModel;

public interface DateRepository extends JpaRepository<DateModel, Long> {
}
