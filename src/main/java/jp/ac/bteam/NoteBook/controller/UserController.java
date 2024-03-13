package jp.ac.bteam.NoteBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
	
	@GetMapping("/")
    public String showIndexFrom() {
        return "user/index";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        // サインアップフォームを表示
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup() {
        // サインアップ処理を実行
        return "redirect:/login"; // ログインページにリダイレクト
    }

    @GetMapping("/login")
    public String showLoginForm() {
        // ログインフォームを表示
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout() {
        // ログアウト処理を実行
        return "redirect:/user/login"; // ログインページにリダイレクト
    }
}
