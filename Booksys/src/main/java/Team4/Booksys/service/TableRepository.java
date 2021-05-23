 package Team4.Booksys.service;

import Team4.Booksys.VO.ReservationVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Team4.Booksys.VO.TableVO;

import java.util.List;


 public interface TableRepository extends JpaRepository<TableVO, Long> {
	@Query("SELECT COUNT(*) FROM TABLE1")
	public int numberOfTable();

}