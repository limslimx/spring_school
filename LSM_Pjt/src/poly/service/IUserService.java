package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	//회원가입하기(회원 정보 등록)
	int insertUserInfo(UserDTO pDTO) throws Exception;
	//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	int signInCheck(UserDTO pDTO) throws Exception;
	String findUserId(UserDTO pDTO) throws Exception;
}
