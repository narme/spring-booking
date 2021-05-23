package Team4.Booksys.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import Team4.Booksys.VO.ReservationVO;

public interface ReservationRepository extends JpaRepository<ReservationVO, Long> {
	@Query("SELECT COUNT(*) FROM RESERVATION WHERE start_time = ?1 AND tid = ?2")
	public int numberOfReservationBytimeAndtid(String start_time, int tid);

	@Query("SELECT COUNT(*) FROM RESERVATION WHERE tid = ?1")
	public int numberOfReservationByTableId(int uid);

	@Query("SELECT COUNT(*) FROM RESERVATION WHERE start_time between ?1 and ?2")
	public int numberOfReservationByTime(String t1, String t2);

	@Query("SELECT a FROM RESERVATION a WHERE a.start_time = ?1 AND a.tid = ?2 AND a.rank > ?3 ORDER BY a.rank asc")//확인필요
	public List<ReservationVO> findAllReservationBytimeAndtidAndRank(String start_time, int tid, int rank);
	
	@Query("SELECT a FROM RESERVATION a WHERE a.uid = ?1 AND a.isdeleted = 0" )
	public List<ReservationVO> findAllByuidForUser(int tid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET rank = rank - 1 WHERE oid = ?1" )
	public void updateReservationRank(int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET wait = 0 WHERE rank = 0" )
	public void updateReservationWait(int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET isdeleted = 1 WHERE oid = ?1" )
	public void updateReservationIsdeleted(int oid);
	
	public List<ReservationVO> findAllByuid(int uid);
	
	public ReservationVO findByOid(int oid);
}
