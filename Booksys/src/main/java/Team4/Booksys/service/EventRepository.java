package Team4.Booksys.service;

import org.springframework.data.jpa.repository.JpaRepository;

import Team4.Booksys.VO.EventVO;

public interface EventRepository extends JpaRepository<EventVO, Long> {
	 
}
