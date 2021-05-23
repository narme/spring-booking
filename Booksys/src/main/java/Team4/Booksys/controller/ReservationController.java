package Team4.Booksys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Team4.Booksys.VO.ReservationVO;
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
}
