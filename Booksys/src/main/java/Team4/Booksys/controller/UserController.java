package Team4.Booksys.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Team4.Booksys.VO.CustomerVO;
import Team4.Booksys.service.UserRepository;
import Team4.Booksys.service.UserService;

@Controller
public class UserController {
 
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/joinUs.do",method = RequestMethod.POST)
    public String joinUs(HttpServletRequest req,CustomerVO vo) {
    	vo.setVal1(req.getParameter("id"));
    	if(userRepository.findById(vo.getVal_id())!=null) {
    		System.out.println("중복 아이디 감지됨");
    		return "failed";
    	}
    	vo.setVal2(req.getParameter("PASSWORD"));
    	vo.setVal3(req.getParameter("name"));
    	vo.setVal4(req.getParameter("phonenumber"));
    	userService.joinUser(vo);
    	return "index";
    }
    @RequestMapping(value = "/join")
    public String join(){
    	return "join";
    }
    @RequestMapping(value = "/failed")
    public String failed(){
    	return "failed";
    }
   
}


