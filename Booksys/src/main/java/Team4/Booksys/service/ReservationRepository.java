package Team4.Booksys.service;

import org.springframework.data.jpa.repository.JpaRepository;

import Team4.Booksys.VO.ReservationVO;

public interface ReservationRepository extends JpaRepository<ReservationVO, Long> {
	
}
