package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("ContactMapper")
public interface ContactMapper {

	UserDTO getUserInfo(UserDTO uDTO) throws Exception;
}
