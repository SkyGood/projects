package ccst.sh.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ccst.sh.common.utils.DataInsert;
import ccst.sh.common.utils.MD5;

@Repository
public class SystemDao {
   
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void importData(String path) {
        DataInsert.insertData(jdbcTemplate.getDataSource(), path);
    }

    public Boolean login(String username, String password) {
        Integer rows = jdbcTemplate.queryForObject(SystemSqlMapping.SYSTEM_ADMIN_LOGIN, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                return rs.getInt("cnt");
            }
        }, username, MD5.getMD5Code(password));
        return 0 != rows;
    }

    public Boolean deleteUser(Integer userId) throws Exception {
        /*delete user submit comment*/
        Integer delCommentsByUser = jdbcTemplate.update(SystemSqlMapping.DELETE_COMMENTS_BY_USER , userId);
        /*delete user submit like */
        Integer delLikesByUser = jdbcTemplate.update(SystemSqlMapping.DELETE_LIKES_BY_USER, userId);
        /*delete user submit posts*/
            /*select all post_id by user_id*/
        List<Integer> list = jdbcTemplate.query(SystemSqlMapping.FIND_POSTS_BY_USER,
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getInt("post_id");
                    }
                }, userId);
        for (Integer postId : list) {
            /*delete all comments  by post_id*/
            Integer delCommentByPost = jdbcTemplate.update(SystemSqlMapping.DELETE_COMMENTS_BY_POST, postId);
            /*delete all like by post_id*/
            Integer delLikesByPost = jdbcTemplate.update(SystemSqlMapping.DELETE_LIKES_BY_POST, postId);
            /*delete all post*/
            Integer delPost  = jdbcTemplate.update(SystemSqlMapping.DELETE_POST, postId);
        }
        /*delete user by userId*/
        Integer delUser = jdbcTemplate.update(SystemSqlMapping.DELETE_USER, userId);
        return true;
    }

    
}
