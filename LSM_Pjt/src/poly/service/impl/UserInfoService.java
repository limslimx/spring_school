package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.UserInfoMapper;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {
	
	@Resource(name="UserInfoMapper")
	private UserInfoMapper userInfoMapper;
	
	@Resource(name="MailService")
	private IMailService mailService;
	
	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception{
		
		int res=0;
		
		if(pDTO==null) {
			pDTO=new UserInfoDTO();
		}
		
		UserInfoDTO rDTO=userInfoMapper.getUserExists(pDTO);
		
		if(rDTO==null) {
			rDTO=new UserInfoDTO();
		}
		
		if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res=2;
		} else {
			int success=userInfoMapper.insertUserInfo(pDTO);
			
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
	public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {
		
		int res=0;
		
		UserInfoDTO rDTO=userInfoMapper.getUserLoginCheck(pDTO);
		
		if(rDTO==null) {
			rDTO=new UserInfoDTO();
		}
		
		if(CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res=1;
		}
		return res;
	}

	@Override
	public String findUserId(UserInfoDTO pDTO) throws Exception {
		int res=0;
		
		UserInfoDTO fDTO=userInfoMapper.findUserId(pDTO);
		
		if(fDTO==null) {
			fDTO=new UserInfoDTO();
		}

		if(CmmUtil.nvl(fDTO.getUser_id()).length()>0) {
			res=1;
		}
		
		return fDTO.getUser_id();
	}

}
