package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {

	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
	int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
	String findUserId(UserInfoDTO pDTO) throws Exception;
}
