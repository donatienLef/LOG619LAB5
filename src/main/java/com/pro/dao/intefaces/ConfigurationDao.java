package com.pro.dao.intefaces;

import com.pro.beans.Configurations;
import com.pro.dao.exceptions.DAOException;

public interface ConfigurationDao {
	public Configurations readConfigurationDefault() throws DAOException;
	public Configurations updateConfigurationDefault(Configurations configuration) throws DAOException; //return null si non modifier
}
