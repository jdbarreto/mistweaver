package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.CharacterClass;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class CharacterClassService {

		@Inject
		private EntityManager em;

		@Transactional
		public CharacterClass findByName(String characterClass) {
			Query q = em.createNamedQuery("CharacterClass.findByName");
			q.setParameter("characterClass", characterClass);
			return (CharacterClass)q.getSingleResult();
		}

		@Transactional
		public List<CharacterClass> characterClassList() {
			Query q = em.createNamedQuery("CharacterClass.findAll");
			return q.getResultList();
		}

		@Transactional
		public CharacterClass findById(Integer id) {
			Query q = em.createNamedQuery("CharacterClass.findById");
			q.setParameter("id", id);
			return (CharacterClass)q.getSingleResult();
		}
	
}
