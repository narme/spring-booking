package Team4.Booksys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.EventVO;

@Service
public class EventService {

	@Autowired
	EventRepository Repository;

	public void addEvent(EventVO vo){
		Repository.save(vo);
	}
	public EventVO findbyRid(int rid) {
		return Repository.findByRid(rid);
	}
	
	public void modifyEvent(String event_type, String event_song, String event_memo, int rid) {
		Repository.updateEvent(event_type, event_song, event_memo, rid);
	}
}
