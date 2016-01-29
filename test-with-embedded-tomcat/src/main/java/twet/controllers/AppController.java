package twet.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import twet.controllers.weather.TemperatureService;

@RestController
public class AppController {

	@Autowired
	TemperatureService temperatureService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showHomePage(){
		ModelAndView mav = new ModelAndView("index");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		mav.addObject("date", sdf.format(now));
		mav.addObject("temperature",temperatureService.getCurrentTemperature());
		return mav;
	}
	
	
}
