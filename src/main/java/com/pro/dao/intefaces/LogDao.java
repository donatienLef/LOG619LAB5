package com.pro.dao.intefaces;

import com.pro.beans.Logs;

import java.util.List;

public interface LogDao {

    public void addLog(String email, String message);
    public List<Logs> getLogs();
}
