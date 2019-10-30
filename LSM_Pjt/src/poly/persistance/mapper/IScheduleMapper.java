package poly.persistance.mapper;

import config.Mapper;
import poly.dto.ScheduleDTO;

@Mapper("ScheduleMapper")
public interface IScheduleMapper {

	int FindSoccerSchedule(ScheduleDTO pDTO) throws Exception;
}
