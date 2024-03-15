package jp.ac.bteam.NoteBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.bteam.NoteBook.models.DateModel;
import jp.ac.bteam.NoteBook.models.UserModel;
import jp.ac.bteam.NoteBook.repository.DateRepository;

@Service
public class DateService {

	@Autowired private DateRepository dateRepository;

    
    // 全ユーザーの日記の日付一覧を表示
    public List<DateModel> getAllDates() {
        return dateRepository.findAll();
    }
    
    
    // ログイン中のユーザーの日記の日付一覧を表示
   public List<DateModel> getUserDates(DateModel dateId){
	   return dateRepository.findAll();
   }
   
   
    // 追加する日記の日付を保存（date_createで選択した日付）
    public DateModel saveDate(DateModel dateModel,UserModel loginUser) {
    	dateModel.setUserId(loginUser);
        return dateRepository.saveAndFlush(dateModel);
    }
    
    
    // 選択したdateIdと一致する日記の日付を削除
    public void deleteDateById(Long dateId) {
        dateRepository.deleteById(dateId);
    }

    // 追加していくカモ

}
