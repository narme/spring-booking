package Team4.Booksys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Team4.mariadb.connectionTest;

@SpringBootApplication
public class BooksysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksysApplication.class, args);
		
		//db연동테스트//
		connectionTest test = new connectionTest();
		test.viewtest();
		/////////////
	}

}
