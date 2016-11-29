package com.augmentun.exam.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;
import com.mysql.jdbc.Statement;

public class QuestionDaoImpl {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> findQuestions(Pagination pagination) {
        String sql = "SELECT * FROM question WHERE is_available = 1";
        String keyWord = pagination.getKeyword();

        if (keyWord.length() > 0) {
            //Deal with %
            if (keyWord.contains("%")) {
                keyWord = keyWord.replace("%", "\\%");
            }
            sql += " AND question_name  LIKE '%" + keyWord + "%'";
        }
        //ORDER
        if (pagination.getOrder().equals("DESC")) {
            sql = sql + " ORDER BY question_number DESC LIMIT ?,? ";
        } else {
            sql = sql + " ORDER BY question_number ASC LIMIT ?,? ";
        }
        int offset = pagination.getOffset();
        int pageSize = pagination.getPageSize();
        RowMapper<Question> rowMapper = new RowMapper<Question>() {

            @Override
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setquestionNumber(rs.getString("question_number"));
                question.setQuestionName(rs.getString("question_name"));
                question.setChoiceA(rs.getString("choice_a"));
                question.setChoiceB(rs.getString("choice_b"));
                question.setChoiceC(rs.getString("choice_c"));
                question.setChoiceD(rs.getString("choice_d"));
                question.setCorrectChoice(rs.getString("correct_choice"));
                question.setCreatedTime(rs.getDate("created_time"));
                question.setUpdatedTime(rs.getDate("updated_time"));
                return question;
            }
        };
        List<Question> questionList = jdbcTemplate.query(sql, new Object[] {offset, pageSize}, rowMapper);
        return questionList;
    }

    public Integer getQuestionQty(String keyWord) {
        String sql = "SELECT COUNT(1) FROM question WHERE is_available = 1";
        //WHERE
        if (keyWord.length() > 0) {
            sql += " AND question_name  LIKE '%" + keyWord + "%'";
        }
        Integer result = jdbcTemplate.queryForInt(sql);
        return result;
    }

    public int createQuestion(final Question question) {
        final String sql= "INSERT INTO question (question_number, question_name,choice_a,choice_b,choice_c,choice_d,correct_choice,created_time,updated_time,is_available) VALUES (?,?,?,?,?,?,?,NOW(),null,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, question.getquestionNumber());
                ps.setString(2, question.getQuestionName());
                ps.setString(3, question.getChoiceA());
                ps.setString(4, question.getChoiceB());
                ps.setString(5, question.getChoiceC());
                ps.setString(6, question.getChoiceD());
                ps.setString(7, question.getCorrectChoice());
                ps.setInt(8, question.getIsAvailable());
                return ps;
            }
        }, keyHolder);
        int primaryKey = Integer.parseInt(String.valueOf(keyHolder.getKey().longValue()));
        return primaryKey;
    }

    public Question getQuestionById(final int question_id) {
        Question question = null;
        String sql = "SELECT * FROM question WHERE id = ?";
        Object[] params = new Object[] {question_id};
        RowMapper<Question> rowMapper = new RowMapper<Question>() {

            @Override
            public Question mapRow(ResultSet rs, int arg1) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setquestionNumber(rs.getString("question_number"));
                question.setQuestionName(rs.getString("question_name"));
                question.setChoiceA(rs.getString("choice_a"));
                question.setChoiceB(rs.getString("choice_b"));
                question.setChoiceC(rs.getString("choice_c"));
                question.setChoiceD(rs.getString("choice_d"));
                question.setCorrectChoice(rs.getString("correct_choice"));
                question.setCreatedTime(rs.getDate("created_time"));
                question.setUpdatedTime(rs.getDate("updated_time"));
                question.setIsAvailable(rs.getInt("is_available"));
                return question;
            }

        };
        List<Question> list = jdbcTemplate.query(sql, params, rowMapper);
        if (list.size() != 0) {
            question = list.get(0);
        }
        return question;
    }

    public int updateQuestion(final Question question) {
        String sql = "UPDATE question SET question_number = ?, question_name = ?, choice_a = ?, choice_b = ?, choice_c = ?, choice_d = ?, correct_choice = ?, updated_time = NOW() WHERE id = ?";
        Object[] params = new Object[8];
        params[0] = question.getquestionNumber();
        params[1] = question.getQuestionName();
        params[2] = question.getChoiceA();
        params[3] = question.getChoiceB();
        params[4] = question.getChoiceC();
        params[5] = question.getChoiceD();
        params[6] = question.getCorrectChoice();
        params[7] = question.getId();
        int result = jdbcTemplate.update(sql, params);
        return result;
    }

    public int deleteQuestionById(List<String> deleteList) {
        String deleteIdSql = "";
        for (String id : deleteList) {
           deleteIdSql += id + ",";
        }
        deleteIdSql = deleteIdSql.substring(0, deleteIdSql.length()-1);
        String sql = "DELETE FROM question WHERE id IN ( " + deleteIdSql +" )";
        int result = jdbcTemplate.update(sql);
        return result;
    }

    public int getQuestionQtyById(String questionNumber) {
        int result = 0;
        String sql = "SELECT COUNT(1) FROM question WHERE question_number = ?";
        result = jdbcTemplate.queryForInt(sql, questionNumber);
        return result;
    }
}
