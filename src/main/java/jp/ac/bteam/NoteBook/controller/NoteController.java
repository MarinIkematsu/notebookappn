package jp.ac.bteam.NoteBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.bteam.NoteBook.models.DateModel;
import jp.ac.bteam.NoteBook.models.NoteModel;
import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.service.DateService;
import jp.ac.bteam.NoteBook.service.NoteService;

@Controller
@RequestMapping("/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
    private DateService dateService;
	
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
    public String getUserDates(Model model,DateModel dateId) {
        List<DateModel> userDates = dateService.getUserDates(dateId);
        model.addAttribute("userDates", userDates);
        return "note/my_note"; // HTMLテンプレート名
    }
    // ログイン中のユーザーの日記の新規追加
    // 作成する日記の日付を選択します
    @GetMapping("/my/date_create")
    public String showCreateDateForm(Model model) {
        model.addAttribute("dateModel", new DateModel());
        return "note/date_create"; // 日付選択ページのテンプレート名
    }
    // 選択した日付をsaveして日記の内容を作成するページにも持っていく
    @PostMapping("/my/date_create")
    public String saveDate(Model model, @ModelAttribute DateModel dateModel, @AuthenticationPrincipal UserModel loginUser) {
    	DateModel saveModel = dateService.saveDate(dateModel,loginUser);
    	NoteModel noteModel = new NoteModel();
    	noteModel.setDateId(saveModel);
        model.addAttribute("noteModel", noteModel);
        return "note/note_create"; // 日記作成ページのテンプレート名
    }
    // 選択した日付➡作成した日記をsaveしてmyにリダイレクトします
    @PostMapping("/my/note_create")
    public String saveNote(Model model, @ModelAttribute("noteModel") @Validated NoteModel noteModel, @AuthenticationPrincipal UserModel loginUser) {
    	noteService.saveNote(noteModel); // 日記を保存
        return "redirect:/notes/my";
    }


    // 日記の詳細表示
    @GetMapping("/{noteId}")
    public String showNoteDetail(@PathVariable Long noteId, Model model) {
        NoteModel note = noteService.getNoteByNoteId(noteId);
        model.addAttribute("note", note);
        return "note/note_detail";
    }

    
    // 日記の編集
    @GetMapping("/{noteId}/edit")
    public String showEditNoteForm(@PathVariable Long noteId, Model model) {
        NoteModel note = noteService.getNoteByNoteId(noteId);
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
