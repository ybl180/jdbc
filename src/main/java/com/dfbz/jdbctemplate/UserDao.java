package com.dfbz.jdbctemplate;

import com.dfbz.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDao {
    private static JdbcTemplate template = new JdbcTemplate(JdbctemplateUtil.getDataSource());

    public static void main(String[] args) {
        List<User> users = listUser();
        users.stream().forEach(n -> System.out.println(n));

        List<User> lists = listUser2("张", 2, 2);
        lists.stream().forEach(n -> System.out.println(n));

//        User user1 = selectById(1);
//        System.out.println(user1);

//        deleteUserById(1);

        User user = new User();
        user.setName("八戒");
        user.setAge(130);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        user.setCreateTime(date);
        addUser(user);
    }

    //查询全部
    public static List<User> listUser() {
        String sql = "select * from sys_user";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //模糊,分页查询
    public static List<User> listUser2(String name, Integer page, Integer pageSize) {
        String sql = "select * from sys_user where name like ? limit ?,? ";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%", (page - 1) * pageSize, pageSize);
    }

    //通过id查询
    public static User selectById(Integer id) {
        String sql = "select * from sys_user where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    //通过id删除
    public static void deleteUserById(Integer id) {
        String sql = "delete from sys_user where id=?";
        template.update(sql, id);
    }

    //添加用户
    public static void addUser(User user) {
        String sql = "insert into sys_user(name,age,create_time) values(?,?,?)";
        template.update(sql, user.getName(), user.getAge(), user.getCreateTime());
    }
}
