package twet.controllers.news;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/api/news/")
public class NewsService {
	
	@RequestMapping(value="headlines", method=RequestMethod.GET)
	public List<Headline> getHeadlines() {
		return Arrays.asList(
				new Headline("Twetland election polls","Latest polls suggest that Sunday's elections will be a tight race."),
				new Headline("Twetball rising popularity","Twetball is gaining popularity especially among the elderly."),
				new Headline("Elvis in Twetville?","Twetville locals claim sightings of deceased superstar.")
				);
	}

}
