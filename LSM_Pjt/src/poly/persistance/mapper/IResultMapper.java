package poly.persistance.mapper;

import config.Mapper;
import poly.dto.ResultDTO;

@Mapper("ResultMapper")
public interface IResultMapper {

	int insertResultInfo(ResultDTO pDTO) throws Exception;
	
	int updateResultInfo(ResultDTO pDTO) throws Exception;
}
