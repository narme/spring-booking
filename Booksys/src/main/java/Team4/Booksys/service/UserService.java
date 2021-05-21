package Team4.Booksys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.CustomerVO;

@Service
public class UserService {
 
    @Autowired
    UserRepository userRepository;
 
    public void joinUser(CustomerVO vo){
    	System.out.println("ȸ������ ��û ����");
        userRepository.save(vo);
        System.out.println("ȸ������ ��û ����");
    }
    
    public CustomerVO findUserId(String id) {
    	return userRepository.findById(id);
    }
}




