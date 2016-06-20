package service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import entity.Character;
import entity.User;

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
	public List<Character> characterList(User user) {
		Query q = em.createNamedQuery("Characters.findByUserId");
		q.setParameter("id", user.getId());
		return q.getResultList();
	}

	@Transactional
	public void remove(Character character) {
		em.remove(em.contains(character) ? character : em.merge(character));
	}

	@Transactional
	public void alter(Character character) {
		em.merge(character);
	}
	
	@Transactional
	public Character findById(Character character) {
		Query q = em.createNamedQuery("Characters.findById");
		q.setParameter("id", character.getId());
		return (Character) q.getSingleResult();
	}
}
