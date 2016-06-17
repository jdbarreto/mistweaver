package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.City;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class CityService {

	@Inject
	private EntityManager em;

	@Transactional
	public City findByName(String city) {
		Query q = em.createNamedQuery("City.findByName");
		q.setParameter("city", city);
		return (City)q.getSingleResult();
	}

	@Transactional
	public List<City> cityList() {
		Query q = em.createNamedQuery("City.findAll");
		return q.getResultList();
	}

	@Transactional
	public City findById(Integer id) {
		Query q = em.createNamedQuery("City.findById");
		q.setParameter("id", id);
		return (City)q.getSingleResult();
	}
	

	public List<City> listCitiesByState(Integer state_id) {
		Query q = em.createNamedQuery("City.findAllByStateId");
		q.setParameter("idState", state_id);
		return q.getResultList();
	}
}
