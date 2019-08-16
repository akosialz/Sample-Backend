package org.sample.test.service;


import org.apache.log4j.Logger;
import org.sample.test.dao.UtilityDao;
import org.springframework.beans.factory.annotation.Autowired;

// FOR DYNAMIC SCHEMA WHERE SCHEMA VALUE DEPENDS ON THE USER
public abstract class BaseService {
	
	private static Logger logger = Logger.getLogger(BaseService.class);
	
	@Autowired
	private UtilityDao utilityDao;
	
	protected void setCurrentSchema(String schema) throws Exception {
		utilityDao.setSchema(schema);
	}
}
