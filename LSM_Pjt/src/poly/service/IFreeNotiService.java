package poly.service;

import java.util.List;

import poly.dto.FreeNotiDTO;

public interface IFreeNotiService {

	List<FreeNotiDTO> getFreeNotiList() throws Exception;

	int insertFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception;
	
	FreeNotiDTO getFreeNotiDetail(FreeNotiDTO fnDTO) throws Exception;
	
	int updateFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception;
	
	int deleteNotiInfo(FreeNotiDTO fnDTO) throws Exception;
}
