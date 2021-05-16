package Team4.Booksys.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Team4.Booksys.VO.CustomerVO;
import Team4.Booksys.service.LoginService;
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
    		System.out.println("Áßº¹ ¾ÆÀÌµð °¨ÁöµÊ");
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
    	System.out.print("nooo..");
    	return "failed";
    }
   

// login
	@Autowired
	LoginService loginService;

	@PostMapping(value = "/signIn.do")
	public String signIn(HttpSession session,HttpServletRequest req) {
		String id=req.getParameter("id");
		String pw=req.getParameter("password");
		
		
		if (loginService.loginCheck(id, pw)) { 
			session.setAttribute("loginCheck", true);
			session.setAttribute("id", id);
			return "home";
		} else {
			System.out.print("False");
			return "/index";
		}

	}
//logout
	@RequestMapping(value="/logOut.do")
	public String logOut(HttpSession session) {
		session.setAttribute("loginCheck", null);
		session.setAttribute("id", null);
		
		return "/index";
	}
	

}
