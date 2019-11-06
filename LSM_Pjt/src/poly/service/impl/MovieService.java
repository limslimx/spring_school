package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.MovieDTO;
import poly.persistance.mapper.IMovieMapper;
import poly.service.IMovieService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("MovieService")
public class MovieService implements IMovieService{

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="MovieMapper")
	private IMovieMapper movieMapper;

	@Override
	public int getMovieInfoFromWeb() throws Exception {
		
		log.info(this.getClass().getName()+".getMovieInfoFromWeb start!");
		
		int res=0;
		
		String url="http://www.cgv.co.kr/movies/";
		
		Document doc=null;
		
		doc=Jsoup.connect(url).get();
		
		Elements element=doc.select("div.sect-movie-chart");
		
		Iterator<Element> rank=element.select("strong.rank").iterator();
		Iterator<Element> name=element.select("strong.title").iterator();
		Iterator<Element> reserve=element.select("strong.percent span").iterator();
		Iterator<Element> score=element.select("strong.percent").iterator();
		Iterator<Element> open_day=element.select("span.txt-info").iterator();
		
		log.info("####################################"+rank);
		log.info("####################################"+name);
		log.info("####################################"+reserve);
		log.info("####################################"+score);
		log.info("####################################"+open_day);
		MovieDTO pDTO=null;
		
		while(rank.hasNext()) {
			
			pDTO=new MovieDTO();
			
			pDTO.setRank_check_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			
			
			pDTO.setRank(CmmUtil.nvl(rank.next().text()).trim());
			
			pDTO.setName(CmmUtil.nvl(name.next().text()).trim());
			
			pDTO.setReserve(CmmUtil.nvl(reserve.next().text()).trim());
			
			pDTO.setScore(CmmUtil.nvl(score.next().text()).trim());
			
			pDTO.setOpen_day(CmmUtil.nvl(open_day.next().text()).trim().substring(0,10));
			
			pDTO.setReg_id("admin");
			
			log.info("####################################"+pDTO.getRank());
			log.info("####################################"+pDTO.getName());
			log.info("####################################"+pDTO.getReserve());
			log.info("####################################"+pDTO.getScore());
			log.info("####################################"+pDTO.getOpen_day());
			
			res+=movieMapper.InsertMovieInfo(pDTO);
		}
		
		
		
		log.info(this.getClass().getName()+".getMovieInfoFromWeb end!");
		
		return res;
	}
}
