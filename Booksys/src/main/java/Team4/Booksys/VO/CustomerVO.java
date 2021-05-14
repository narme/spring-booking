package Team4.Booksys.VO;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="Customer")
public class CustomerVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;
	private String id;
    private String name;
    private String password;
    private String phonenumber;
    
    
    public void setVal1(String val1) {
        this.id = val1;
    }

    public void setVal2(String val2) {
        this.name = val2;
    }
    
    public void setVal3(String val3) {
        this.password = val3;
    }
    
    public void setVal4(String val4) {
        this.phonenumber = val4;
    }
    
    public void setVal5(int val5) {
        this.oid = val5;
    }
    
    public String getVal_id() {
        return this.id;
    }
    public String getVal_name() {
        return this.name;
    }
    public String getVal_password() {
        return this.password;
    }
    public String getVal_phonenumber() {
        return this.phonenumber;
    }
    public int getVal_oid() {
        return this.oid;
    }
    
}


