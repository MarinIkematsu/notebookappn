package jp.ac.bteam.NoteBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/")
    public String showIndexFrom() {
        return "user/index";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("userModel") @Validated UserModel userModel, BindingResult result) {
        // サインアップ処理を実行
    	if (result.hasErrors()) {
            return "user/signup";
        }
        
        // 登録処理を行う
        userService.signupUser(userModel);
        return "redirect:/notes"; // ログインページにリダイレクト
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
    	model.addAttribute("userModel", new UserModel());
        return "user/login";
    }
    @PostMapping("/login")
    public String login(String username,String password, HttpSession session) {
    	 if (userService.authenticate(username, password)) {
             // 認証に成功した場合、セッションを開始してログイン状態にする
             session.setAttribute("username", username);
             return "redirect:/notes"; // ログイン後のリダイレクト先を指定
         } else {
             // 認証に失敗した場合はログインフォームに戻す
             return "redirect:/login?error";
         }
    }
    

    @GetMapping("/logout")
    public String logout() {
        // ログアウト処理を実行
        return "redirect:/"; // ログインページにリダイレクト
    }
}
