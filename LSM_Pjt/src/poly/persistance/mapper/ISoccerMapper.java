package poly.persistance.mapper;

import java.util.List;
import config.Mapper;
import poly.dto.ResultDTO;

@Mapper("SoccerMapper")
public interface ISoccerMapper {

	List<ResultDTO> getSoccerResult() throws Exception;
}
