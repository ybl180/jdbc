package com.dfbz.dao;

import com.dfbz.entity.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    public static void main(String[] args) {
//        List<User> result = list();

//        List<User> result = select("张");
//        result.stream().forEach(n -> System.out.println(n));

        User user = new User();
        user.setId(123L);
        user.setName("张翼德");
        user.setAge(12);
        user.setSex("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        user.setCreateTime(date);
        user.setDelFlag("1");

        addUser(user);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    private static void addUser(User user) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        String sql = "insert into sys_user (id,name,age,sex,create_time,del_flag ) values (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getId());
            ps.setObject(2, user.getName());
            ps.setObject(3, user.getAge());
            ps.setObject(4, user.getSex());
            ps.setObject(5, user.getCreateTime());
            ps.setObject(6, user.getDelFlag());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模糊查询
     *
     * @param condition 条件
     * @return
     */
    private static List<User> select(String condition) {
        List<User> result = new ArrayList<>();
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String userName = "root";
        String password = "root";
        String sql = "select * from sys_user where name like ?";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, "%" + condition + "%");
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Integer age = rs.getInt("age");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");
                user.setName(name);
                user.setAge(age);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);
                user.setId(Long.valueOf(id));
                result.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 查询全部
     *
     * @return
     */
    private static List<User> list() {
        List<User> result = new ArrayList<>();
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        String sql = "select * from sys_user";
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接  java连接mysql
            Connection conn = DriverManager.getConnection(url, username, password);
            //实例化PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //结果集
            ResultSet rs = ps.executeQuery();
            User user = new User();
            //结果集查询
            while (rs.next()) {
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Integer age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");
                user.setName(name);
                user.setId(Long.valueOf(id));
                user.setAge(age);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);
                user.setSex(sex);
                result.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
