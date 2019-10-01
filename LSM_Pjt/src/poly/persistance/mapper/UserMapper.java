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
	UserDTO findUserId(UserDTO pDTO) throws Exception;
}
