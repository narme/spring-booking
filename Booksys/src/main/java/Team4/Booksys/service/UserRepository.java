package Team4.Booksys.service;



import org.springframework.data.jpa.repository.JpaRepository;

import Team4.Booksys.VO.CustomerVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<CustomerVO, Long> {
	public CustomerVO findById(String username);

	@Query("SELECT a FROM USER a WHERE a.oid = ?1")
	public CustomerVO findByUid(int id);


	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE USER SET noshow = noshow + 1 WHERE oid = ?1")
	public int increaseNoShowCount(int uid);
}
