package Team4.Booksys.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Entity(name="RESERVATION")
public class ReservationVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;
	private int uid;
	private int people_number;
	private String start_time;
	//private String end_time;
	private int tid;
	private int wait;
	private int rank;
	private int isdeleted;
	
	public void setVal_oid(int val) {
	    this.oid = val;
	}
	public void setVal_uid(int val) {
        this.uid = val;
	}
	public void setVal_people_number(int val) {
        this.people_number = val;
	}
	public void setVal_start_time(String val) {
        this.start_time = val;
	}
	/*
	public void setVal_end_time(String val) {
        this.end_time = val;
	}*/
	public void setVal_tid(int val) {
	    this.tid = val;
	}
	public void setVal_wait(int val) {
	    this.wait = val;
	}
	public void setVal_rank (int val) {
	    this.rank = val;
	}
	public void setVal_isdeleted (int val) {
	    this.isdeleted = val;
	}
	
	public int getVal_oid() {
	    return this.oid;
	}
	public int getVal_uid() {
	    return this.uid;
	}
	public int getVal_people_number() {
	    return this.people_number;
	}
	public String getVal_start_time() {
	    return this.start_time;
	}
	/*
	public String getVal_end_time() {
	    return this.end_time;
	}*/
	public int getVal_tid() {
	    return this.tid;
	}
	public int getVal_wait() {
	    return this.wait;
	}
	public int getVal_rank() {
	    return this.rank;
	}
	public int getVal_isdeleted() {
	    return this.isdeleted;
	}

}
