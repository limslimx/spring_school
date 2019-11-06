package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NotiDTO;

@Mapper("NotiMapper")
public interface NotiMapper {

	List<NotiDTO> getNotiList() throws Exception;

	int insertNotiInfo(NotiDTO nDTO) throws Exception;

	NotiDTO getNotiDetail(String seq) throws Exception;
	
	int updateNotiInfo(NotiDTO nDTO) throws Exception;
	
	void deleteNotiInfo(NotiDTO nDTO) throws Exception;
	
}
