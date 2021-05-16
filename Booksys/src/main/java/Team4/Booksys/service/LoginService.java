package Team4.Booksys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.CustomerVO;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;

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

}
