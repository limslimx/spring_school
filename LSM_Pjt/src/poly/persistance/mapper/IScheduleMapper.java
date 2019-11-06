package poly.persistance.mapper;

import config.Mapper;
import poly.dto.ScheduleDTO;

@Mapper("ScheduleMapper")
public interface IScheduleMapper {

	int InsertScheduleInfo(ScheduleDTO pDTO) throws Exception;
}
