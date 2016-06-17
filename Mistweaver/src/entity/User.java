package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
@NamedQuery(name = "User.findByEmailPassword",query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"),
@NamedQuery(name="User.findByName", query="select u from User u where u.name like :nome order by u.name"),
@NamedQuery(name="User.findAll", query="select u from User u order by u.name"),
@NamedQuery(name="User.findById", query="select u from User u where u.id = :id")
})

public class User implements Serializable{
	
 	private static final long serialVersionUID = 1L;
 	
    public static final String FIND_BY_EMAIL_PASSWORD = "User.findByEmailPassword";   
 	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(unique= true)
    private String email;
	
	@Column
	private String password;
	
	@Column
    private String name;
    
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
	
	@Column
    private String phone;
	
	@Column
    private String sex;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Character> characteres;
	
	@ManyToOne
	@JoinColumn(name="state_id")
    private State state;
	
	@ManyToOne
	@JoinColumn(name="city_id")
    private City city;

    public User() {
    }
    
	public List<Character> getCharacteres() {
		return characteres;
	}
	
	public List<User> getUsers() {
		return getUsers();
	}
	
	public void setCharacteres(List<Character> characteres) {
		this.characteres = characteres;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
}
