package Team4.Booksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.ReservationVO;

@Service
public class ReservationService {
	@Autowired
    ReservationRepository Repository;
 
    public void addReservation(ReservationVO vo){
    	System.out.println("예약 추가");
    	Repository.save(vo);
    	System.out.println("예약 추가 성공");
    }
    public int findWaitRank(String time, int tableid) {
    	return Repository.numberOfReservationBytimeAndtid(time, tableid);
    }
    public int findByTableId(int uid){
        return Repository.numberOfReservationByTableId(uid);
    }

    public int countReservationByMonth(String t1, String t2){
        return Repository.numberOfReservationByTime(t1, t2);
    }

    public List<ReservationVO> getReservationList(int uid) {
    	return Repository.findAllByuid(uid);
    } 
}
