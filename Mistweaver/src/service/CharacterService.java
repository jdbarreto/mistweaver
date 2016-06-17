package service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import entity.Character;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class CharacterService {

	@Inject
	private EntityManager em;

	@Transactional
	public void insert(Character character) {
		character.setCreateDate(new Date());
		em.persist(character);
		}

	@Transactional
	public List<Character> characterList() {
		Query q = em.createNamedQuery("Characters.findAll");
		return q.getResultList();
	}

	@Transactional
	public void remove(Character character) {
		em.remove(em.contains(character) ? character : em.merge(character));
	}

	@Transactional
	public void alter(Character Character) {
		em.merge(Character);
	}

	public Character findByName(String characterName) {
		 Query q = em.createNamedQuery("Characters.findByName");
		 q.setMaxResults(1);
		 q.setParameter("characterName", characterName+"%");
		 Character character = (Character) q.getSingleResult();
		 return character;
	}

}
