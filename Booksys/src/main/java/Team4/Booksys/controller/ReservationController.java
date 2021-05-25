package Team4.Booksys.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Team4.Booksys.VO.ReservationVO;
import Team4.Booksys.VO.modefiedReservation;
import Team4.Booksys.VO.modefiedReservationDivideDateAndTime;
import Team4.Booksys.service.ReservationRepository;
import Team4.Booksys.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService ReservationService;
	
	@ResponseBody
	@RequestMapping(value = "/cancleReservation.do")
	public String cancleReservation(HttpServletRequest request, Model model) {
		int cancleoid = Integer.parseInt(request.getParameter("cancleoid"));
		ReservationVO vo = ReservationService.findByOid(cancleoid); //취소할 예약의 vo
		ReservationService.removeReservationForUser(vo);
		return "<script> alert('취소 완료');  location.href= '/showUserReservation'; </script>";
	}
	
	@RequestMapping(value = "/callDeleteReserve/{oid}", produces = "text/html; charset=UTF-8")
	public String callDeleteReserve(@PathVariable int oid,HttpSession session) {
		ReservationVO vo = ReservationService.findByOid(oid);
		ReservationService.removeReservationForUser(vo);
		return "redirect:/showUserReservation";			
	}
	
	@Autowired
	ReservationRepository ReservationRepository;

	@RequestMapping(value = "/callModifyReserve/{oid}", produces = "text/html; charset=UTF-8")
	public String modifyReserve(@PathVariable int oid,HttpSession session,Model model) { //아직 작업중인 코드 leewk
		model.addAttribute("userid", session.getAttribute("id"));
		model.addAttribute("reserveOid", oid);
		
		ReservationVO vo=ReservationRepository.findByOid(oid);
		modefiedReservationDivideDateAndTime mReservdt = new modefiedReservationDivideDateAndTime();
		mReservdt.setVal_oid(vo.getVal_oid());
		mReservdt.setVal_people_number(vo.getVal_people_number());
		mReservdt.setVal_rank(vo.getVal_rank());
		mReservdt.setVal_start_time(vo.getVal_start_time());
		mReservdt.setVal_tid(vo.getVal_tid());

		
		model.addAttribute("vo",vo.getVal_start_time());
		
		model.addAttribute("mReserv",mReservdt);
		
		return "modifyReserve";
		
	}
	
}
