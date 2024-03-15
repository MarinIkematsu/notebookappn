package jp.ac.bteam.NoteBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.DateModel;
import jp.ac.bteam.NoteBook.models.NoteModel;
import jp.ac.bteam.NoteBook.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
    private NoteRepository noteRepository;

    // 日記の詳細を取得
    public NoteModel getNoteByNoteId(Long noteId) {
        return noteRepository.findNoteByNoteId(noteId);
    }
    
    
    // 新規日記の追加
    public NoteModel saveNote(NoteModel noteModel, DateModel dateId) {
    	NoteModel newNote = new NoteModel();
        newNote.setTitle(noteModel.getTitle());
        newNote.setContent(noteModel.getContent());
        noteModel.setDateId(dateId);
		return noteRepository.save(newNote);
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