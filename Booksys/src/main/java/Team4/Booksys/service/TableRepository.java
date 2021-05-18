 package Team4.Booksys.service;

import org.springframework.data.jpa.repository.JpaRepository;

import Team4.Booksys.VO.TableVO;



public interface TableRepository extends JpaRepository<TableVO, Long> {
		
}