package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.FreeNotiDTO;

@Mapper("FreeNotiMapper")
public interface FreeNotiMapper {

	List<FreeNotiDTO> getFreeNotiList() throws Exception;
	
	int insertFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception;
	
	FreeNotiDTO getFreeNotiDetail(FreeNotiDTO fnDTO) throws Exception;
	
	int updateFreeNotiInfo(FreeNotiDTO fnDTO) throws Exception;
	
	int deleteNotiInfo(FreeNotiDTO fnDTO) throws Exception;
}
