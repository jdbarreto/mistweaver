package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import entity.City;
import entity.State;
import entity.User;

import service.CityService;
import service.StateService;
import service.UserService;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean inclusao;
	private boolean logged;

	private User loggedUser;

	private String email, password;

	private List<City> cities;

	private List<State> states;
	
	private User user;
	
	private City city;

	@Inject
	private CityService cityService;

	@Inject
	private StateService stateService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private MenuController menuController;

	private State selectedState;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void userInsert(String page) {
		userService.userInsert(user);
		refresh();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário incluído com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		user = new User();
		menuController.setPage(page);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "");
	}
	
	public void doLogin(String page) throws Exception {

		User userFound = (User) userService.loginVerify(email, password);
		
		if (userFound == null) {
			logged=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos!", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			logged = true;
			loggedUser = userFound;
			menuController.setPage(page);
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null, "");
		}
	}
	
	public void doRegister(String page) throws Exception {
			menuController.setPage(page);
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null, "");
			refresh();
	}

	public void doLogout(String page) {
		loggedUser = null;
		logged = false;
		menuController.setPage(page);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "");
	}

	public boolean isLogged() {
		return logged;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	@PostConstruct
	public void init() {
		user = new User();
		inclusao = true;
		selectedState = null;
		states = stateService.stateList();
		cities = new ArrayList<City>();
	}
	
	public void criarNova() {
		init();
		refresh();
	}
	
	private void refresh() {
		selectedState = null;
	    FacesContext context = FacesContext.getCurrentInstance();
	    Application application = context.getApplication();
	    ViewHandler viewHandler = application.getViewHandler();
	    UIViewRoot viewRoot = viewHandler.createView(context, 
	    		context.getViewRoot().getViewId());
	    context.setViewRoot(viewRoot);		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State getSelectedState() {
		return selectedState;
	}

	
	
	public void setSelectedState(State selectedState) {
		this.selectedState = selectedState;
	}

	public void filterCityByStates() {
		if (selectedState != null) {
			cities = cityService.listCitiesByState(selectedState.getId());
		} else {
			cities = new ArrayList<>();
		}
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}
	
}
