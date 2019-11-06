package poly.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.IMovieService;

@Controller
public class MovieController {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="MovieService")
	private IMovieService movieService;
	
	@RequestMapping(value="movie")
	private String Movie(ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".Movie start!");
		
		int res=movieService.getMovieInfoFromWeb();
		
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName()+".Movie end!");
		
		return "/movie/movie";
	}
}
