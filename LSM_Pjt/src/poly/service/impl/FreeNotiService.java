package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.FreeNotiDTO;
import poly.persistance.mapper.FreeNotiMapper;
import poly.service.IFreeNotiService;

@Service("FreeNotiService")
public class FreeNotiService implements IFreeNotiService {

	@Resource(name="FreeNotiMapper")
	private FreeNotiMapper freeNotiMapper;
	
	@Override
	public List<FreeNotiDTO> getFreeNotiList() throws Exception {
		// TODO Auto-generated method stub
		return freeNotiMapper.getFreeNotiList();
	}

	@Override
	public int insertFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeNotiMapper.insertFreeNotiInfo(fnDTO);
	}

	@Override
	public FreeNotiDTO getFreeNotiDetail(FreeNotiDTO fnDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeNotiMapper.getFreeNotiDetail(fnDTO);
	}

	@Override
	public int updateFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeNotiMapper.updateFreeNotiInfo(fnDTO);
	}

	@Override
	public int deleteNotiInfo(FreeNotiDTO fnDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeNotiMapper.deleteNotiInfo(fnDTO);
	}
	

}
