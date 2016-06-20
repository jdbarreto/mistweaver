package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Characters")
@NamedQueries(value = { 
		@NamedQuery(name = "Characters.findAll", query = "select c from Characters c"),
		@NamedQuery(name = "Characters.findById", query = "SELECT c FROM Characters c WHERE c.id = :id"),
		@NamedQuery(name = "Characters.findByUserId", query = "SELECT c FROM Characters c WHERE c.user.id = :id")
})
public class Character implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(unique = true)
	private String characterName;

	@Column
	private String characterSex;

	@ManyToOne
	@JoinColumn(name = "characterClass_id")
	private CharacterClass characterClass;

	@ManyToOne
	@JoinColumn(name = "race_id")
	private Race race;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Character() {
	}

	public Character(int id) {
		this();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getCharacterSex() {
		return characterSex;
	}

	public void setCharacterSex(String characterSex) {
		this.characterSex = characterSex;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

}
