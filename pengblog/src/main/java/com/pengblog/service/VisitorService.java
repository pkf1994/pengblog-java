package com.pengblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengblog.bean.Visitor;
import com.pengblog.dao.IvisitorDao;

@Service("visitorService")
public class VisitorService implements IvisitorService{
	
	@Autowired
	private IvisitorDao visitorDao;

	@Override
	public int saveVisitor(Visitor visitor) {
		
		int visitor_id = visitorDao.insertVisitor(visitor);
		
		return visitor_id;
	}
	
}
