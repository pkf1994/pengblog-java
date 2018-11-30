package com.pengblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengblog.bean.Administrator;
import com.pengblog.dao.IadministratorDao;
import com.pengblog.utils.Md5Util;
import com.pengblog.utils.MyTokenUtil;

@Service("loginService")
public class LoginService implements IloginService{
	
	@Autowired
	private IadministratorDao adminstratorDao;

	@Override
	public Map<String,Object> login(String username, String password) {

		Administrator administrator	= this.adminstratorDao.selectAdministratorByUsername(username);
		
		Map<String,Object> retMap = new HashMap<>();
		
		if(administrator == null) {
			
			retMap.put("loginStatus", 0);
			
			retMap.put("loginMsg", "wrong username");
			
			return retMap;
		}
		
		String salt = Md5Util.getSalt(administrator.getAdministrator_saltDate());
		
		String dbPassword = administrator.getAdministrator_password();
		
		password = Md5Util.getMD5(password + salt);
		
		Boolean rightAdministrator = Md5Util.verify(password, dbPassword);
		
		if(!rightAdministrator) {
			
			retMap.put("loginStatus", 0);
			
			retMap.put("loginMsg", "wrong password");
			
		}else{	
			
			retMap.put("loginStatus", 1);
			
			retMap.put("loginMsg", "logged in successfully");
			
			String token = MyTokenUtil.createJWT(6000000, administrator);
			
			retMap.put("token", token);
			
			retMap.put("validTimeMillis", 6000000);
		}
		
		return retMap;
	}

}
