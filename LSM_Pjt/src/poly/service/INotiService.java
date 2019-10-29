package poly.service;

import java.util.List;

import poly.dto.NotiDTO;

public interface INotiService {

	List<NotiDTO> getNotiList() throws Exception;

	int insertNotiInfo(NotiDTO nDTO) throws Exception;

	NotiDTO getNotiDetail(String seq) throws Exception;
	
	int updateNotiInfo(NotiDTO nDTO) throws Exception;
	
	void deleteNotiInfo(NotiDTO nDTO) throws Exception;
}
