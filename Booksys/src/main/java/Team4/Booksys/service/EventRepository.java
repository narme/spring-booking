package Team4.Booksys.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Team4.Booksys.VO.EventVO;

public interface EventRepository extends JpaRepository<EventVO, Long> {
	 public EventVO findByRid(int rid);
	 
	 @Transactional
	 @Modifying(clearAutomatically = true)
	 @Query("UPDATE EVENT SET event_type = ?1, event_song = ?2, event_memo = ?3 WHERE rid = ?4" )
	 public void updateEvent(String event_type, String event_song, String event_memo, int rid);
}
