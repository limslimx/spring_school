package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.UserMapper;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service("UserService")
public class UserService implements IUserService {
	
	@Resource(name="UserMapper")
	private UserMapper userMapper;
	
	@Resource(name="MailService")
	private IMailService mailService;
	
	@Override
	public int insertUserInfo(UserDTO pDTO) throws Exception{
		
		int res=0;
		
		if(pDTO==null) {
			pDTO=new UserDTO();
		}
		
		UserDTO rDTO=userMapper.getUserExists(pDTO);
		
		if(rDTO==null) {
			rDTO=new UserDTO();
		}
		
		if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res=2;
		} else {
			int success=userMapper.insertUserInfo(pDTO);
			
			if(success>0) {
				res=1;
				
				//mail 발송 로직 추가
				MailDTO mDTO=new MailDTO();
				
				mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
				mDTO.setTitle("회원가입을 축하드립니다.");
				mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name())+"님의 회원가입을 환영합니다.");
				
				
				mailService.doSendMail(mDTO);
				
			} else {
				res=0;
			}
		}
		
		return res;
	}

	@Override
	public int signInCheck(UserDTO pDTO) throws Exception {
		
		int res=0;
		
		UserDTO rDTO=userMapper.signInCheck(pDTO);
		
		if(rDTO==null) {
			rDTO=new UserDTO();
		}
		
		if(CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res=1;
		}
		return res;
	}

	@Override
	public String findUserId(UserDTO pDTO) throws Exception {
		int res=0;
		
		UserDTO fDTO=userMapper.findUserId(pDTO);
		
		if(fDTO==null) {
			fDTO=new UserDTO();
		}

		if(CmmUtil.nvl(fDTO.getUser_id()).length()>0) {
			res=1;
		}
		
		return fDTO.getUser_id();
	}

	@Override
	public UserDTO idCheck(String id) throws Exception {
		
		return userMapper.idCheck(id);
	}

	@Override
	public UserDTO emailCheck(String email) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.emailCheck(email);
	}

	@Override
	public int findUserPw(UserDTO pDTO) throws Exception {
		
		int res=0;
		String passwd=pDTO.getPasswd();
		
		userMapper.findUserPw(pDTO);
		
		UserDTO uDTO=userMapper.getUserInfo(pDTO);
		
		if(uDTO!=null) {
			res=1;
			
			//mail 발송 로직 추가
			MailDTO mDTO=new MailDTO();
			
			mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(uDTO.getEmail())));
			mDTO.setTitle("Socsche의 임시비밀번호입니다.");
			mDTO.setContents("임시비밀번호는 "+CmmUtil.nvl(passwd)+"입니다.");
			
			
			mailService.doSendMail(mDTO);
			
		} else {
			res=0;
		}
		return res;
	}

	@Override
	public UserDTO getMypage(UserDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getMypage(pDTO);
	}

	@Override
	public int changeName(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.changeName(uDTO);
	}

	@Override
	public int changeEmail(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.changeEmail(uDTO);
	}

	@Override
	public UserDTO pwCheck(UserDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.pwCheck(pDTO);
	}
	
	@Override
	public int changePw(UserDTO uDTO) throws Exception {
		
		return userMapper.changePw(uDTO);
	}

	@Override
	public int deleteUserInfo(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteUserInfo(uDTO);
	}

}
