package jp.ac.bteam.NoteBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.bteam.NoteBook.models.DateModel;
import jp.ac.bteam.NoteBook.models.NoteModel;
import jp.ac.bteam.NoteBook.service.DateService;
import jp.ac.bteam.NoteBook.service.NoteService;

@Controller
@RequestMapping("/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
    private DateService dateService;

    @Autowired
    public NoteController(NoteService noteService, DateService dateService) {
        this.noteService = noteService;
        this.dateService = dateService;
    }
 // 全ユーザーの日記の日付一覧表示
    @GetMapping("/all")
    public String getAllDates(Model model) {
        List<DateModel> dates = dateService.getAllDates();
        model.addAttribute("dates", dates);
        return "note/all_note"; // HTMLテンプレート名
    }

    // ログイン中のユーザーの日記の日付一覧表示
    @GetMapping("/my")
    public String getUserDates(Model model) {
        Long userId = 123L; // 仮のログインユーザーID
        List<DateModel> userDates = dateService.getUserDates(userId);
        model.addAttribute("userDates", userDates);
        return "note/my_note"; // HTMLテンプレート名
    }
    // ログイン中のユーザーの日記の新規追加
    // 作成する日記の日付を選択します
    @GetMapping("/my/date_create")
    public String showCreateNotePage(Model model) {
        model.addAttribute("dateModel", new DateModel());
        return "note/date_create"; // 日付選択ページのテンプレート名
    }
    // 選択した日付をsaveして日記の内容を作成するページにも持っていく

    
    // 選択した日付の日記の内容を書きます
    @GetMapping("/my/note_create")
    public String showCreateNoteForm(Model model) {
        model.addAttribute("noteModel", new NoteModel());
        return "note/note_create"; // 日記作成ページのテンプレート名
    }
    // 選択した日付➡作成した日記をsaveしてmyにリダイレクトします
    


    // userIdとdateIdに一致する日記の詳細表示
    @GetMapping("/{noteId}")
    public String showNoteDetail(@PathVariable Long dateId,Long userId, Model model) {
        List<NoteModel> note = noteService.getNoteByDateIdUserId(dateId, userId);
        model.addAttribute("note", note);
        return "note/note_detail"; // HTMLテンプレート名
    }

    
    // 日記の編集
    @GetMapping("/{noteId}/edit")
    public String showEditNoteForm(@PathVariable Long dateId,Long userId, Model model) {
        List<NoteModel> note = noteService.getNoteByDateIdUserId(dateId, userId);
        model.addAttribute("note", note);
        return "note/note_edit"; // HTMLテンプレート名
    }

    @PostMapping("/{noteId}/edit")
    public String editNote(@PathVariable Long noteId, @ModelAttribute("note") NoteModel noteModel) {
        noteModel.setNoteId(noteId);
        noteService.updateNote(noteModel);
        return "redirect:/notes/my"; // リダイレクト先のURL
    }

    // ログイン中のユーザーの日記の削除
    @PostMapping("/{noteId}/delete")
    public String deleteNoteById(@PathVariable Long noteId) {
        noteService.deleteNoteById(noteId);
        return "redirect:/notes/my"; // リダイレクト先のURL
    }
}