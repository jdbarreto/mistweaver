package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String page;

	@PostConstruct
	public void init() {
		page="login";
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
