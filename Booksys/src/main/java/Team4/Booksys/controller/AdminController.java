package Team4.Booksys.controller;

import Team4.Booksys.VO.CustomerVO;
import Team4.Booksys.VO.ReservationVO;
import Team4.Booksys.VO.TableVO;
import Team4.Booksys.VO.modefiedReservation;
import Team4.Booksys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationService ReservationService;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    TableService tableService;


    public class data{
        String time;
        int count;

        public data(String s, int tmp) {
            time = s;
            count = tmp;
        }
        public String getTime(){
            return time;
        }
        public int getCount(){
            return count;
        }
    }
    //@ResponseBody //return to body
    @RequestMapping(value = "/admin.do", produces = "text/html; charset=UTF-8")
    public String admin(HttpSession session) {
        String id = session.getAttribute("id").toString();
        CustomerVO vo = userRepository.findById(id);
        String msg;
        if (vo.getVal_level() == 1){
            return "/admin/admin";
        }else{
            return "/home";
        }
    }

    @RequestMapping(value = "/showReservation.do", produces = "text/html; charset=UTF-8")
    public String showReservation(HttpServletRequest request, Model model) { //예약리스트 조회관련 코드 추가함 ㅁㅁ
        HttpSession session = request.getSession(true);//현재 세션 로드
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
        //window.open("/admin/showUserReservation", "a","width=400, height=300, left=100, top=50");
        return "/admin/showUserReservation";
    }

    @ResponseBody
    @RequestMapping(value = "/addTable.do", produces = "text/html; charset=UTF-8")
    public String addTable(HttpServletRequest request, TableVO vo) {

        int tableNumber;

        if(request.getParameter("tableNumber") =="") {
            return "<script> alert('테이블 번호를 입력해주세요.');  location.href= 'locateAddTable.do'; </script>";
        }
        tableNumber = Integer.parseInt(request.getParameter("tableNumber"));
        vo.setVal_rid(tableNumber);
        tableRepository.save(vo);

        return "<script> window.close();</script>";
    }


    @RequestMapping(value = "/locateAddTable.do")
    public String locateAddTable() {
        return "/admin/addTable";
    }

    @RequestMapping(value = "/locateShowTable.do")
    public String locateShowTable(Model model){
        model.addAttribute("table", tableService.getAllTable());
        return "/admin/showTable";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTable.do")
    public String deleteTable(HttpServletRequest request){
        int tableNumber;

        if(request.getParameter("tableNumber") =="") {
            return "<script> alert('테이블 번호를 입력해주세요.');  location.href= 'locateShowTable.do'; </script>";
        }
        tableNumber = Integer.parseInt(request.getParameter("tableNumber"));
        //테이블을 사용하고 있는 예약이 있는 경우.
        if(ReservationService.findByTableId(tableNumber) != 0){
            return "<script> alert('현재 해당 테이블을 사용중인 예약이 있습니다.');  location.href= 'locateShowTable.do'; </script>";
        }
        //System.out.println(request.getParameter("tableNumber"));

        tableRepository.deleteById((long) tableNumber);
        return "<script> alert('삭제되었습니다.'); location.href= 'locateShowTable.do'; </script>";
        //return "<script> window.close();</script>";
    }

    @RequestMapping(value = "showReservationByMonth.do")
    public String showReservationByMonth(Model model){

        List<data> datas = new ArrayList<>();

        Date current = new Date();
        Calendar cal = Calendar.getInstance();
        int start_year = 2020 - 1900;
        int start_month = 5 - 1; //
        int tmp;
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd 00:00:00");
        String t1, t2;
        int i = 0;
        while(current.getYear() >= start_year){
            cal.set(start_year + 1900, start_month, 1);

            t1 = format1.format(cal.getTime());
            cal.set(start_year + 1900, start_month, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            t2 = format1.format(cal.getTime());

            //System.out.println(t1);
            //System.out.println(t2);
            tmp = ReservationService.countReservationByMonth(t1, t2);
            datas.add(new data(start_year + 1900 + "년 " + (start_month + 1) + "월", tmp));

            start_month++;
            System.out.println(datas.get(i).count + "   " + datas.get(i).time);
            i++;

            if(start_month > current.getMonth() && start_year == current.getYear())break;

            if(start_month == 12 && current.getYear() >= start_year){
                start_year++;
                start_month = 0;
            }
        }
        model.addAttribute("dataList", datas);
        return "/admin/showUserReservationByMonth";
    }
}
