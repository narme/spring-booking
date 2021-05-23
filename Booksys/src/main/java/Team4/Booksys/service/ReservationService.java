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

    public List<ReservationVO> getReservationListForUser(int uid){
    	return Repository.findAllByuidForUser(uid);
    }
    public void removeReservationForUser(ReservationVO vo) { //유저전용 예약 삭제기능 검증은 안됨
    	int oid = vo.getVal_oid();
    	String start_time = vo.getVal_start_time();
    	int tid =vo.getVal_tid();
    	int rank = vo.getVal_rank();
    	List<ReservationVO> list = Repository.findAllReservationBytimeAndtidAndRank(start_time, tid, rank);
    	if(list.size() == 0) {//이 예약 뒤로 대기중인 예약이 없다면
    		Repository.updateReservationIsdeleted(oid);
    	} 
    		
    	else { //이 예약 뒤로 대기중인 예약이 있다면
    		//대기순서 조절 시작
    		for (ReservationVO vo2 : list) {
    			int nowoid = vo2.getVal_oid();
    			Repository.updateReservationRank(nowoid);//rank 조절
    			Repository.updateReservationWait(nowoid);//wait 조절
    		}
    		Repository.updateReservationIsdeleted(oid);
    	}
    }
    public ReservationVO findByOid(int oid){
        return Repository.findByOid(oid);
    }
}
