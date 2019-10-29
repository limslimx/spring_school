package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.FoodDTO;
import poly.persistance.mapper.IFoodMapper;
import poly.service.IFoodService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("FoodService")
public class FoodService implements IFoodService {

	@Resource(name="FoodMapper")
	private IFoodMapper foodMapper;
	
	private Logger log=Logger.getLogger(getClass());

	@Override
	public int getFoodInfoFromWEB() throws Exception {
		
		log.info(this.getClass().getName()+".getFoodInfoFromWEB start!");
		
		int res=0;
		int i=0;
		
		String url="http://www.kopo.ac.kr/kangseo/content.do?menu=262";
		
		Document doc=null;
		
		doc=Jsoup.connect(url).get();
		
		Elements element=doc.select("table.tbl_table");
		
		Iterator<Element> day=element.select("tbody tr td:nth-child(1)").iterator();
		Iterator<Element> food_name=element.select("tbody tr td:nth-child(3) span").iterator();
		
		FoodDTO pDTO=null;
		
		while(i<5) {
			pDTO=new FoodDTO();
			
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			
			pDTO.setDay(day.next().text().trim());
			
			pDTO.setFood_name(CmmUtil.nvl(food_name.next().text()).trim());
			
			pDTO.setReg_id("admin");
			
			res+=foodMapper.InsertFoodInfo(pDTO);
			i++;
		}
		
		log.info(this.getClass().getName()+".getFoodInfoFromWEB end!");
		return res;
	}
	
}