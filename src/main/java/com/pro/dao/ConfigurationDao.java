package com.pro.dao;

import com.pro.beans.Configurations;

public interface ConfigurationDao {
	public Configurations readConfigurationDefault() throws DAOException;
	public Configurations updateConfigurationDefault(Configurations configuration) throws DAOException; //return null si non modifier
}
