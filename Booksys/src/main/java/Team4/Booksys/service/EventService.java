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

}
