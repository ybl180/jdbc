package com.dfbz.jdbctemplate;


import com.dfbz.entity.PageBean;
import com.dfbz.entity.User1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static JdbcTemplate template = new JdbcTemplate(JdbctemplateUtil.getDataSource());

    public static void main(String[] args) {
        OrderDao dao = new OrderDao();
//        for (int i = 0; i < 10; i++) {
//            dao.addUser(i % 2 + 1, i, "xxx", i);
//        }
        List<User1> list1 = dao.selectAll(1);
        List<User1> list2 = dao.selectAll(2);
        ArrayList<User1> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.forEach(n -> System.out.println(n));

        System.out.println("-------------------------");

        PageBean pageBean = new PageBean();
        pageBean.setPage(5);
        List<User1> list3 = dao.selectPage(pageBean);
        list3.stream().forEach(n-> System.out.println(n));
    }

    private void addUser(Integer index, Integer id, String name, Integer userId) {
        String sql = "insert into order_? (id,name,user_id)values (?,?,?)";
        template.update(sql, index, id, name, userId);
    }

    private List<User1> selectAll(Integer index) {
        String sql = "select * from order_?";
        return template.query(sql, new BeanPropertyRowMapper<>(User1.class), index);
    }

    private List<User1> selectPage(PageBean pageBean) {
        String sql = "select * from order_1 union select * from order_2 order by id limit ? ,?";
        return template.query(sql, new BeanPropertyRowMapper<>(User1.class), pageBean.getPage(), pageBean.getPageSize());
    }

}
