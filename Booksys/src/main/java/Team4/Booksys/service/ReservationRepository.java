package Team4.Booksys.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Team4.Booksys.VO.ReservationVO;

public interface ReservationRepository extends JpaRepository<ReservationVO, Long> {
	@Query("SELECT COUNT(*) FROM RESERVATION WHERE start_time = ?1 AND tid = ?2")
	public int numberOfReservationBytimeAndtid(String start_time, int tid);
	
	public List<ReservationVO> findAllByuid(int uid);
}
