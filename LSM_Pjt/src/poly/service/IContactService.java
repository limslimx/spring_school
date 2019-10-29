package poly.service;

import poly.dto.UserDTO;

public interface IContactService {

	UserDTO getUserInfo(UserDTO uDTO) throws Exception;
}
