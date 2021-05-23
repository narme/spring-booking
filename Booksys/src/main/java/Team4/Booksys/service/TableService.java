package Team4.Booksys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4.Booksys.VO.TableVO;

import java.util.List;

@Service
public class TableService {
	@Autowired
    TableRepository repository;
	public int numberofTable(){
		return repository.numberOfTable();
	}
	public List<TableVO> getAllTable() {
		return repository.findAll();
	}
}
