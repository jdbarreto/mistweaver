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
	@NamedQuery(name="CharacterClass.findByName", query="select c from CharacterClass c where c.className = :className"),
	@NamedQuery(name="CharacterClass.findAll", query="select c from CharacterClass c order by c.className"),
	@NamedQuery(name="CharacterClass.findById", query="select c from CharacterClass c where c.id = :id")
})
public class CharacterClass implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String className;
	
	@OneToMany(mappedBy="characterClass", fetch=FetchType.LAZY)
	private List<Character> characters;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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
		result = prime * result + ((className == null) ? 0 : className.hashCode());
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
		CharacterClass other = (CharacterClass) obj;
		if (id == other.id && className.equals(other.className)) {
			return true;
		}
		return true;
	}
	
	
}
