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
}




