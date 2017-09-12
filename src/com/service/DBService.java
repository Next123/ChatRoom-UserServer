package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.DBManager;

public class DBService {
	
	private int icon;
	
	//查询已注册用户
    public Boolean search(String username) {

        // 获取Sql查询语句
        String logSql = "select * from user where username ='" + username + "'";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return true;
    }
	
	
	//登录
    public Boolean login(String username, String password) {

        // 获取Sql查询语句
        String logSql = "select * from user where username ='" + username
                + "' and password ='" + password + "'";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
            	icon = rs.getInt(4);
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }
    //注册
    public Boolean register(String username, String password,String icon) {
    
        // 获取Sql查询语句
        String regSql = "insert into user(username,password,icon) values('"+ username+ "','"+ password+ "','"+icon+"') ";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
    }
    //登录成功后获取头像
    public int getIcon() {
		return icon;
	}
    
}