package Team4.Booksys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.CustomerVO;

@Service
public class UserService {
 
    @Autowired
    UserRepository userRepository;
 
    public void joinUser(CustomerVO vo){
    	System.out.println("회원가입 요청 들어옴");
        userRepository.save(vo);
        System.out.println("회원가입 요청 성공");
    }
    
    public boolean loginCheck(String id, String pw) {
		/*test*/
		//System.out.println("   loginCheck 동작 ");
		//System.out.println("id: "+ id+"pw: "+pw);
		
		CustomerVO vo = userRepository.findById(id);
		
		if (vo.getVal_password().contentEquals(pw)) {
			return true;
		}

		else
			return false;
	}
    public CustomerVO isExist(int uid){return userRepository.findByUid(uid);}
	public int increaseNoShowCount(int uid){return userRepository.increaseNoShowCount(uid);}
}




