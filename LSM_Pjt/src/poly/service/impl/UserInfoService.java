package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserInfoDTO;
import poly.persistance.mapper.UserInfoMapper;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {
	
	@Resource(name="UserInfoMapper")
	private UserInfoMapper userInfoMapper;
	
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
		return 0;
	}

}
