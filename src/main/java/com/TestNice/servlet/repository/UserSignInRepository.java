package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.eventClass.SignInEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserSignInRepository implements SignInRepository{

    private final DataSource dataSource;

    @Override
    public String signInUser(SignInEvent event) {

        String result = "";

        String sql = "INSERT INTO authorLogin (id, pw, name, kakao, naver, google, facebook, phone) values (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, event.getId());
            pstm.setString(2, event.getPassword());
            pstm.setString(3, event.getUserName());
            pstm.setString(4, event.getKakao());
            pstm.setString(5, event.getNaver());
            pstm.setString(6, event.getGoogle());
            pstm.setString(7, event.getFacebook());
            pstm.setString(8, event.getPhone());
            pstm.executeUpdate();



        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }

        result = "success";
        return result;
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
