package Team4.Booksys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/joinUs.do", method = RequestMethod.POST)
	public String joinUs(HttpServletRequest req, CustomerVO vo) {
		vo.setVal_id(req.getParameter("id"));
		if (userRepository.findById(vo.getVal_id()) != null) {
			System.out.println("중복아이디 감지");
			return "failed";
		}
		vo.setVal_password(req.getParameter("PASSWORD"));
		vo.setVal_name(req.getParameter("name"));
		vo.setVal_phonenumber(req.getParameter("phonenumber"));
		userService.joinUser(vo);
		return "/index";
	}

	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/failed")
	public String failed() {
		System.out.print("nooo..");
		return "failed";
	}
	
	// login
	@Autowired
	LoginService loginService;

	@ResponseBody //return to body
	@PostMapping(value = "/signIn.do")
	public String signIn(HttpSession session, HttpServletRequest req) {
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		if (id == "") {
			return "<script> alert('아이디를 입력하세요');  location.href= '/index'; </script>";
		}
		if (pw == "") {
			return "<script> alert('비밀번호를 입력하세요');  location.href= '/index'; </script>";
		}
		if (userRepository.findById(id) == null) {
			return "<script> alert('없는 아이디 입니다.');  location.href= '/index'; </script>";
			// return "index";
		}

		if (loginService.loginCheck(id, pw)) {
			System.out.print("\n" + id + "님 login");
			session.setAttribute("loginCheck", true);
			session.setAttribute("id", id);
			return "<script> alert('로그인 되셨습니다!');  location.href= '/home'; </script>";
			// return "/home";

		} else {
			System.out.print("False");
			return "<script> alert('아이디와 비밀번호가 일치하지 않습니다.');  location.href= '/index'; </script>";
			// return "index";
		}

	}

	//logout
	@RequestMapping(value = "/logOut.do")
	public String logOut(HttpSession session) {
		session.setAttribute("loginCheck", null);
		session.setAttribute("id", null);
		return "/index";
	}

	// page mapping
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/eventReservation")
	public String eventReservation() {
		return "eventReservation";
	}

	@RequestMapping(value = "/noEventReservation")
	public String noEventReservation() {
		return "noEventReservation";
	}
	
	@RequestMapping(value = "/showTableView")
	public String showTableView() {
		return "showTableView";
	}
	
	@RequestMapping(value = "/showUserReservation")
	public String showUserReservation() {
		return "showUserReservation";
	}
}
