package com.dfbz.test2;

import com.dfbz.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    public static void main(String[] args) {
        List<User> list = findAll();
        list.stream().forEach(n -> System.out.println(n));

        List<User> list2 = findAll2(2, 2);
        list2.stream().forEach(System.out::println);

        User user = new User();
        user.setName("张某");
        user.setAge(20);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        user.setCreateTime(date);
        addUser(user);
    }

    public static List<User> findAll() {
        String sql = "select id,name,age from sys_user ";
        List<User> result = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            User user;
            while (rs.next()) {
                user = new User();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                user.setId(Long.valueOf(id));
                user.setName(name);
                user.setAge(age);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSteam(conn, ps, rs);
        }
        return result;
    }

    public static List<User> findAll2(Integer pageSize, Integer page) {
        String sql = "select id,name,age from sys_user where 1=1 limit ?,?";
        List<User> result = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (page - 1) * pageSize);
            ps.setObject(2, pageSize);
            rs = ps.executeQuery();
            User user;
            while (rs.next()) {
                user = new User();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                user.setId(Long.valueOf(id));
                user.setName(name);
                user.setAge(age);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSteam(conn, ps, rs);
        }
        return result;
    }

    public static void addUser(User user) {
        String sql = "insert into sys_user (name ,age, create_time)values(?,?,?)";
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getAge());
            ps.setObject(3, user.getCreateTime());
            int flag = ps.executeUpdate();
            if (flag==1) {
                System.out.println("用户添加成功");
            } else {
                System.out.println("用户添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSteam(conn, ps, null);
        }
    }
}
