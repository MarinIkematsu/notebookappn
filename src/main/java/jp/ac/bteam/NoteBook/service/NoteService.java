package jp.ac.bteam.NoteBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.NoteModel;
import jp.ac.bteam.NoteBook.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    // userIdとdateIdに一致する日記の詳細を取得
    public List<NoteModel> getNoteByDateIdUserId(Long dateId, Long userId) {
        return noteRepository.findByDateIdAndUserId(dateId, userId);
    }
    
    
    // 新規日記の追加
    public NoteModel saveNote(NoteModel noteModel) {
        return noteRepository.save(noteModel);
    }


    // 日記の編集　（未完成）
    public NoteModel updateNote(NoteModel noteModel) {
        return noteRepository.save(noteModel);
    }

    // 選択したnoteIdと一致する日記の削除　（いるかわからない➡日付ごと削除させるか）
    public void deleteNoteById(Long noteId) {
        noteRepository.deleteById(noteId);
    }
}