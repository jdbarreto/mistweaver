package entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Race.findByName", query="select r from Race r where r.raceName = :raceName"),
	@NamedQuery(name="Race.findAll", query="select r from Race r order by r.raceName"),
	@NamedQuery(name="Race.findById", query="select r from Race r where r.id = :id")
})
public class Race implements Serializable{

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		@Column
		private String raceName;
		
		public Integer getId() {
			return id;
		}
		
		@OneToMany(mappedBy="race", fetch=FetchType.LAZY)
		private List<Character> characters;

		public void setId(Integer id) {
			this.id = id;
		}

		public String getRaceName() {
			return raceName;
		}

		public void setRaceName(String raceName) {
			this.raceName = raceName;
		}

		public List<Character> getCharacters() {
			return characters;
		}

		public void setCharacters(List<Character> characters) {
			this.characters = characters;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((raceName == null) ? 0 : raceName.hashCode());
			result = prime * result + ((characters == null) ? 0 : characters.hashCode());
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
			Race other = (Race) obj;
			if (id == other.id && raceName.equals(other.raceName)) {
				return true;
			}
			return true;
		}
		
		
}