package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Race;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class RaceService {

	@Inject
	private EntityManager em;

	@Transactional
	public Race findByName(String race) {
		Query q = em.createNamedQuery("Race.findByName");
		q.setParameter("characterClass", race);
		return (Race)q.getSingleResult();
	}

	@Transactional
	public List<Race> raceList() {
		Query q = em.createNamedQuery("Race.findAll");
		return q.getResultList();
	}

	@Transactional
	public Race findById(Integer id) {
		Query q = em.createNamedQuery("Race.findById");
		q.setParameter("id", id);
		return (Race)q.getSingleResult();
	}
}
