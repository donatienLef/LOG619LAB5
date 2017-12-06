package com.pro.dao.implementation;

import com.pro.beans.Logs;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.LogDao;

import java.util.List;

public class LogDaoImpl extends BaseDao implements LogDao {

    private String SQL_INSERT_LOG = "INSERT INTO `logs`(`account`, `message`) VALUES (?,?)";

    public LogDaoImpl(DAOFactory daoFactory) {
        super(daoFactory);
    }


    @Override
    public void addLog(String email, String message) {
        updateSQL(SQL_INSERT_LOG, email, message);
    }

    @Override
    public List<Logs> getLogs() {
        return null;
    }


}
