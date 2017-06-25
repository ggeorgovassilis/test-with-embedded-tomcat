package twet.controllers.news;

public class Headline {

	protected int id;
	protected String title;
	protected String description;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Headline() {
	}
	
	public Headline(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
