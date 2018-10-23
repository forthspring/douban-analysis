package com.twq.dao.impl;

import com.twq.dao.TopTenDao;
import com.twq.model.TopTenMovie;
import com.twq.util.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbcTopTenDaoImpl")
public class JDBCTopTenDaoImpl implements TopTenDao {
    @Override
    public List<TopTenMovie> findTopTenMoviesByYear(int year) throws SQLException {
        Connection connection = ConnectionFactory.getHiveJdbcConn();
        Statement stmt = connection.createStatement();
        stmt.execute("SET mapreduce.framework.name=local");
        String sql = "SELECT /*+ MAPJOIN(movie_links)*/  m.movieId, m.moviename, l.url, m.commentscore " +
                "from movie m join movie_links l on m.movieId = l.movieId " +
                "where year=" + year + " " +
                "order by commentscore desc limit 10";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);

        List<TopTenMovie> topTenMovieDtos = new ArrayList<>();
        while (res.next()) {
            TopTenMovie dto = new TopTenMovie();
            dto.setMovieId(res.getString(1));
            dto.setMovieName(res.getString(2));
            dto.setUrl(res.getString(3));
            dto.setCommentScore(res.getFloat(4));
            topTenMovieDtos.add(dto);
        }
        return topTenMovieDtos;
    }
}
