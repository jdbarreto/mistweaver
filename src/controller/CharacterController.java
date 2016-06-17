package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Character;
import entity.CharacterClass;
import entity.Race;
import entity.User;
import service.CharacterClassService;
import service.CharacterService;
import service.RaceService;

@Named
@ViewScoped
public class CharacterController implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean include;

	private Character character;

	private List<Race> races;

	private List<CharacterClass> characterClasses;

	@Inject
	private UserController userController;

	@Inject
	private CharacterClassService characterClassService;

	@Inject
	private CharacterService characterService;

	@Inject
	private RaceService raceService;

	private String characterName;

	private Race race;

	private CharacterClass characterClass;

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	public List<CharacterClass> getCharacterClasses() {
		return characterClasses;
	}

	public void setCharacterClasses(List<CharacterClass> characterClasses) {
		this.characterClasses = characterClasses;
	}

	public CharacterService getCharacterService() {
		return characterService;
	}

	public void setCharacterService(CharacterService characterService) {
		this.characterService = characterService;
	}

	public RaceService getRaceService() {
		return raceService;
	}

	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

	public List<Character> getCharacters() {
		User u  = userController.getLoggedUser();
		return characterService.characterList(u);
	}

	public boolean isInclude() {
		return include;
	}

	public void setInclude(boolean include) {
		this.include = include;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
	}

	@PostConstruct
	public void init() {
		include = true;
		character = new Character();
		races = raceService.raceList();
		characterClasses = characterClassService.characterClassList();
	}

	public void insert() {
		characterService.insert(character);
		criarNova();
	}

	public void persistCharacter() {
		character.setUser(userController.getLoggedUser());
		if (include) {
			characterService.insert(character);
		} else {
			characterService.alter(character);
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Personagem salvo com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		criarNova();
	}

	public void remove(int characterId) {
		characterService.remove(new Character(characterId));
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Personagem excluído com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		criarNova();
	}

	public void findByName() {
		characterService.findByName(characterName);
		characterName = character.getCharacterName();
		character = characterService.findByName(characterName);
		race = character.getRace();
		characterClass = character.getCharacterClass();
		include = false;
	}

	public void alter() {
		characterService.alter(character);
		// criarNova();
	}

	private void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
	}

	public void criarNova() {
		init();
		refresh();
	}
}
