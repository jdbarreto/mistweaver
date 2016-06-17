package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.State;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class StateService {
	
	@Inject
	private EntityManager em;
	
	@Transactional
	public State findByName(String state) {
		Query q = em.createNamedQuery("State.findByName");
		q.setParameter("state", state);
		return (State)q.getSingleResult();
	}

	@Transactional
	public List<State> stateList() {
		Query q = em.createNamedQuery("State.findAll");
		return q.getResultList();
	}

	@Transactional
	public State findById(Integer id) {
		Query q = em.createNamedQuery("State.findById");
		q.setParameter("id", id);
		return (State)q.getSingleResult();
	}
}
