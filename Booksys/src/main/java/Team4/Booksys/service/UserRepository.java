package Team4.Booksys.service;



import org.springframework.data.jpa.repository.JpaRepository;

import Team4.Booksys.VO.CustomerVO;

public interface UserRepository extends JpaRepository<CustomerVO, Long> {
	public CustomerVO findById(String username);
}
