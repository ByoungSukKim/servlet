package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
/*@Primary*/
public class OauthLoginRepository implements OuathRepository{

    private final DataSource dataSource;

    public OauthLoginRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Kakao> OauthloginAccount(Kakao paramK) {

        String sql = "select * from authorLogin where kakao = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, paramK.getKakao());
            rs = pstm.executeQuery();

            if(rs.next()) {

                Kakao kakao = new Kakao();
                kakao.setId(rs.getString("id"));
                kakao.setPw(rs.getString("pw"));
                kakao.setKakao(rs.getString("kakao"));
                kakao.setNaver(rs.getString("naver"));
                kakao.setGoogle(rs.getString("google"));
                kakao.setFaceBook(rs.getString("faceBook"));
                kakao.setPhone(rs.getString("phone"));

                return Optional.of(kakao);

            } else {

                return Optional.empty();

            }


        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close (Connection conn, PreparedStatement pstm) {
        try {
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

}
