package poly.service;

import java.util.List;

import poly.dto.ResultDTO;

public interface ISoccerService {

	List<ResultDTO> getSoccerResult() throws Exception;
	
	int updateResultInfo(ResultDTO pDTO) throws Exception;
}
