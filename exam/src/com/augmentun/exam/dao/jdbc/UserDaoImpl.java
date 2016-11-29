package com.augmentun.exam.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.augmentun.exam.dao.UserDao;
import com.augmentun.exam.model.User;
public class UserDaoImpl implements UserDao{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByName (String username) {
        User user = null;
        String sql = "SELECT id ,user_name, password FROM user WHERE user_name = ?";
        Object[] param = new Object[] {username};
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                user.setId(id);
                user.setUserName(userName);
                user.setPassword(password);
            return user;
          }

        };
        List<User> list = jdbcTemplate.query(sql, param, rowMapper);
        if (list.size() != 0) {
            user = list.get(0);
        }

        return user;
    }
}
