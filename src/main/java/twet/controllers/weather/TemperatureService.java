package twet.controllers.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temperature/")
public class TemperatureService{

	@RequestMapping(value="current", method=RequestMethod.GET)
	public Integer getCurrentTemperature() {
		return 34;
	}

}
