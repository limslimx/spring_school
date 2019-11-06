package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NotiDTO;
import poly.persistance.mapper.NotiMapper;
import poly.service.INotiService;

@Service("NotiService")
public class NotiService implements INotiService {

	@Resource(name="NotiMapper")
	private NotiMapper notiMapper;
	
	@Override
	public List<NotiDTO> getNotiList() throws Exception {
		
		return notiMapper.getNotiList();
	}

	@Override
	public int insertNotiInfo(NotiDTO nDTO) throws Exception {

		return notiMapper.insertNotiInfo(nDTO);
	}

	@Override
	public NotiDTO getNotiDetail(String seq) throws Exception {

		return notiMapper.getNotiDetail(seq);
	}

	@Override
	public void deleteNotiInfo(NotiDTO nDTO) throws Exception {
		
		notiMapper.deleteNotiInfo(nDTO);
	}

	@Override
	public int updateNotiInfo(NotiDTO nDTO) throws Exception {
		
		return notiMapper.updateNotiInfo(nDTO);
	}

	
}
