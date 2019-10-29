package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface UserMapper {

	//회원 가입하기(회원 정보 등록)
	int insertUserInfo(UserDTO pDTO) throws Exception;
	//회원 가입 전 중복체크하기(DB 조회)
	UserDTO getUserExists(UserDTO pDTO) throws Exception;
	//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	UserDTO signInCheck(UserDTO pDTO) throws Exception;
	UserDTO idCheck(String id) throws Exception;
	UserDTO emailCheck(String email) throws Exception;
	UserDTO findUserId(UserDTO pDTO) throws Exception;
	void findUserPw(UserDTO pDTO) throws Exception;
	UserDTO getUserInfo(UserDTO pDTO) throws Exception;
	UserDTO pwCheck(UserDTO pDTO) throws Exception;
	UserDTO getMypage(UserDTO pDTO) throws Exception;
	int changeName(UserDTO uDTO) throws Exception;
	int changeEmail(UserDTO uDTO) throws Exception;
	int changePw(UserDTO uDTO) throws Exception;
	int deleteUserInfo(UserDTO uDTO) throws Exception;
}
