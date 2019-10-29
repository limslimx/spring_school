package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.ContactMapper;
import poly.service.IContactService;

@Service("ContactService")
public class ContactService implements IContactService {

	@Resource(name="ContactMapper")
	private ContactMapper contactMapper;

	@Override
	public UserDTO getUserInfo(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return contactMapper.getUserInfo(uDTO);
	}
	
	
}
