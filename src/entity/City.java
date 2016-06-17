package entity;

import java.io.Serializable;
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


@Entity
@NamedQueries({
	@NamedQuery(name="City.findByName", query="select c from City c where c.city = :name"),
	@NamedQuery(name="City.findAll", query="select c from City c order by c.city"),
	@NamedQuery(name="City.findById", query="select c from City c where c.id = :id"),
	@NamedQuery(name="City.findAllByStateId", query="select c from City c where c.state.id = :idState")
})
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String city;
	
	@OneToMany(mappedBy="city", fetch=FetchType.LAZY)
	private List<User> users;

	@ManyToOne
	@JoinColumn(name="state_id")
	private State state;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id == other.id && city.equals(other.city)) {
			return true;
		}
		return true;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}	
}
