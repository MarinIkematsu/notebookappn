package jp.ac.bteam.NoteBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/notes")
    public String showTopPage() {
        // トップページを表示
        return "note/top";
    }
}
