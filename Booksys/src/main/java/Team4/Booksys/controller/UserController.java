package Team4.Booksys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Team4.Booksys.VO.CustomerVO;
import Team4.Booksys.VO.EventVO;
import Team4.Booksys.VO.ReservationVO;
import Team4.Booksys.VO.modefiedReservation;
import Team4.Booksys.service.ReservationRepository;
import Team4.Booksys.service.ReservationService;
import Team4.Booksys.service.TableService;
import Team4.Booksys.service.UserRepository;
import Team4.Booksys.service.UserService;
import Team4.Booksys.service.EventService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@ResponseBody // return to body
	@RequestMapping(value = "/joinUs.do", method = RequestMethod.POST)
	public String joinUs(HttpServletRequest req, CustomerVO vo) {
		vo.setVal_id(req.getParameter("id"));
		if (userRepository.findById(vo.getVal_id()) != null) {
			System.out.println("중복아이디 감지");
			return "<script> alert('중복된 아이디 입니다.');  location.href= '/join'; </script>";
		}
		vo.setVal_password(req.getParameter("PASSWORD"));
		vo.setVal_name(req.getParameter("name"));
		vo.setVal_phonenumber(req.getParameter("phonenumber"));
		
		/*확인 부분*/
		if(vo.getVal_id().equals("")||vo.getVal_name().equals("")||vo.getVal_password().equals("")||vo.getVal_phonenumber().equals(""))
			return "<script> alert('정보를 모두 입력해주세요.');  location.href= '/join'; </script>";
		/*끝*/
		
		userService.joinUser(vo);
		return "<script> location.href= '/index'; </script>";
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
	
	@ResponseBody // return to body
	@PostMapping(value = "/signIn.do", produces = "text/html; charset=UTF-8")
	public String signIn(HttpSession session, HttpServletRequest req) {
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		if (id.equals("")) {
			return "<script> alert('아이디를 입력해주세요.');  location.href= '/index'; </script>";
		}
		if (pw.equals("")) {
			return "<script> alert('비밀번호를 입력하세요');  location.href= '/index'; </script>";
		}
		if (userRepository.findById(id) == null) {
			return "<script> alert('없는 아이디 입니다.');  location.href= '/index'; </script>";
			// return "index";
		}

		if (userService.loginCheck(id, pw)) {
			System.out.println("\n" + id + "님 login");

			// 유저의 oid도 세션에 함께 저장한다. (DB연동관련)
			CustomerVO vo = userRepository.findById(id);
			int oid = vo.getVal_oid();
			session.setAttribute("oid", oid);
			// 코드끝

			session.setAttribute("loginCheck", true);
			session.setAttribute("id", id);
			session.setAttribute("level", vo.getVal_level());
			
			//if (vo.getVal_level() == 0)
			return "<script> alert('로그인 되셨습니다!');  location.href= '/home'; </script>";

		} else {
			System.out.println("False");
			return "<script> alert('아이디와 비밀번호가 일치하지 않습니다.');  location.href= '/index'; </script>";
		}

	}

	// logout
	@RequestMapping(value = "/logOut.do")
	public String logOut(HttpSession session) {
		session.setAttribute("loginCheck", null);
		session.setAttribute("id", null);
		session.setAttribute("level", null);
		return "/index";
	}

	// page mapping
	@RequestMapping(value = "/home", produces = "text/html; charset=UTF-8")
	public String home(HttpSession session,Model model) {
		if (session.getAttribute("loginCheck") == null)
			return "index";
		
		
		model.addAttribute("userid", session.getAttribute("id"));
		return "home";
	}
	
	
	@RequestMapping(value = "/adminhome")
	public String adminhome(HttpSession session,Model model) {
		/*관리자 레벨인지 확인*/
		if (session.getAttribute("loginCheck") == null || (int)session.getAttribute("level")==0)
			return "index";
		
		model.addAttribute("userid", session.getAttribute("id"));
		return "adminhome";
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
	public String showUserReservation(HttpServletRequest request, Model model) { // 예약리스트 조회관련 코드 추가함 ㅁㅁ
		HttpSession session = request.getSession(true);// 현재 세션 로드
		int currentOid = (int) session.getAttribute("oid");
		String currentid = (String) session.getAttribute("id");
		List<ReservationVO> list = ReservationService.getReservationList(currentOid);
		ArrayList<modefiedReservation> list2 = new ArrayList<modefiedReservation>();
		for (ReservationVO vo : list) {
			int oid = vo.getVal_oid();
			int people_number = vo.getVal_people_number();
			int rank = vo.getVal_rank();
			int tid = vo.getVal_tid();
			String start_time = vo.getVal_start_time();
			modefiedReservation mReserv = new modefiedReservation();
			mReserv.setVal_oid(oid);
			mReserv.setVal_people_number(people_number);
			mReserv.setVal_rank(rank);
			mReserv.setVal_start_time(start_time);
			mReserv.setVal_tid(tid);
			list2.add(mReserv);
		}
		model.addAttribute("list", list2);
		model.addAttribute("userid", currentid);
		return "showUserReservation";
	}

	@Autowired
	ReservationService ReservationService;

	@Autowired
	TableService TableService;

	@RequestMapping(value = "/addReservation")
	public String addReservation(HttpServletRequest request, ReservationVO vo, Model model) {

		HttpSession session = request.getSession(true);// 현재 세션 로드
		vo.setVal_uid((int) session.getAttribute("oid"));// 세션의 oid값 가져오기
		vo.setVal_people_number(Integer.parseInt(request.getParameter("num_people")));// 인원수 가져오기

		String date = request.getParameter("date");// 날짜 가져오기
		String time = request.getParameter("time");// 시간 가져오기
		String datetime = date + " " + time;
		vo.setVal_start_time(datetime);// 날짜 + 시간 가져오기

		// 자동배정의 tid 구하기
		int tid = 1;
		int mintablewait = 9999;
		int numOftable = TableService.numberofTable(); // 테이블의개수
		for (int j = 1; j <= numOftable; j++) {
			int i = ReservationService.findWaitRank(datetime, j);
			if (i < mintablewait) {
				mintablewait = i;
				tid = j;
			}
		}

		int i = ReservationService.findWaitRank(datetime, tid);// 동일날짜 동시간대에 있는 예약의 개수 리턴
		if (i != 0)/* 이미 해당시간에 예약이 존재한다면 */ {
			vo.setVal_wait(1);// 예약이 존재한다.
			vo.setVal_rank(i);// 대기순서는 i
		} else/* 해당시간에 예약이 없다면 */ {
			vo.setVal_wait(0);
			vo.setVal_rank(0);
		}
		
		vo.setVal_tid(tid);
		ReservationService.addReservation(vo);		
		model.addAttribute("userid", session.getAttribute("id"));
		return "home";
	}
	
	@Autowired
	EventService EventService;
	@RequestMapping(value = "/addReservationEvent")
	public String addReservationEvent(HttpServletRequest request, ReservationVO vo,EventVO evo,Model model) {

		HttpSession session = request.getSession(true);// 현재 세션 로드
		vo.setVal_uid((int) session.getAttribute("oid"));// 세션의 oid값 가져오기
		vo.setVal_people_number(Integer.parseInt(request.getParameter("num_people")));// 인원수 가져오기

		String date = request.getParameter("date");// 날짜 가져오기
		String time = request.getParameter("time");// 시간 가져오기
		String datetime = date + " " + time;
		vo.setVal_start_time(datetime);// 날짜 + 시간 가져오기

		// 자동배정의 tid 구하기
		int tid = 1;
		int mintablewait = 9999;
		int numOftable = TableService.numberofTable(); // 테이블의개수
		for (int j = 1; j <= numOftable; j++) {
			int i = ReservationService.findWaitRank(datetime, j);
			if (i < mintablewait) {
				mintablewait = i;
				tid = j;
			}
		}
		
		int i = ReservationService.findWaitRank(datetime, tid);// 동일날짜 동시간대에 있는 예약의 개수 리턴
		if (i != 0)/* 이미 해당시간에 예약이 존재한다면 */ {
			vo.setVal_wait(1);// 예약이 존재한다.
			vo.setVal_rank(i);// 대기순서는 i
		} else/* 해당시간에 예약이 없다면 */ {
			vo.setVal_wait(0);
			vo.setVal_rank(0);
		}

		vo.setVal_tid(tid);
		ReservationService.addReservation(vo);
		//여기까지 reservation 처리
		//여기부터 event 처리
		int reserv_oid = vo.getVal_oid(); //새로 추가된 예약의 oid정보 event의 외래 키로 쓰임
		evo.setVal_rid(reserv_oid);
		evo.setVal_event_type(request.getParameter("type"));
		evo.setVal_event_song(request.getParameter("song"));
		evo.setVal_event_memo(request.getParameter("memo"));
		
		EventService.addEvent(evo);
		//이벤트 저장 끝
		model.addAttribute("userid", session.getAttribute("id"));
		return "home";
	}
	@Autowired
	ReservationRepository ReservationRepository;

	@RequestMapping(value = "/callModifyReserve/{oid}", produces = "text/html; charset=UTF-8")
	public String modifyReserve(@PathVariable int oid,HttpSession session,Model model) {
		System.out.println("oid is "+oid);
		
		//session.setAttribute("reservationOid", oid);
		
		model.addAttribute("userid", session.getAttribute("id"));
		//model.addAttribute("reserveOid", session.getAttribute("reservationOid"));
		model.addAttribute("reserveOid", oid);
		
		ReservationVO vo=ReservationRepository.findByOid(oid);
		//model.addAttribute("reservePeople", vo.getVal_people_number());
		//model.addAttribute("vo",vo);
		
		
		ArrayList<modefiedReservation> list2 = new ArrayList<modefiedReservation>();
		modefiedReservation mReserv = new modefiedReservation();
		mReserv.setVal_oid(vo.getVal_oid());
		mReserv.setVal_people_number(vo.getVal_people_number());
		mReserv.setVal_rank(vo.getVal_rank());
		mReserv.setVal_start_time(vo.getVal_start_time());
		mReserv.setVal_tid(vo.getVal_tid());
		list2.add(mReserv);
		
		model.addAttribute("vo",vo.getVal_start_time());
		
		model.addAttribute("list",list2);
		
		return "modifyReserve";
		//ReservationVO vo=ReservationRepository.findByOid(oid);
	
		
		//return "modifyReserve";
	}

	
	@RequestMapping(value = "/callDeleteReserve/{oid}", produces = "text/html; charset=UTF-8")
	public String callDeleteReserve(@PathVariable int oid,HttpSession session) {
		 ReservationService.deleteReservationbyoid(oid);
		return "redirect:/showUserReservation";
	
	}
	

}
