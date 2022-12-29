package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
/*@Primary*/
public class JDBCLoginRepository implements LoginRepository{

    private final DataSource dataSource;

    public JDBCLoginRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Login addAccount(Login login) {

        String sql = "insert into member(id, password) values(?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, login.getId());
            pstm.setString(2, login.getPassword());

            pstm.executeUpdate();

            return login;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }

    }

    @Override
    public Optional<Login> loginAccount(Login login) {

        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, login.getId());
            rs = pstm.executeQuery();
            if(rs.next()) {

                Login login1 = new Login();
                login1.setId(rs.getString("id"));
                login1.setPassword(rs.getString("password"));
                return Optional.of(login1);

            } else {

                return Optional.empty();

            }


        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }
    }

    @Override
    public Optional<LoginEvent> loginAccountEvent(LoginEvent event) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, event.getId());
            rs = pstm.executeQuery();
            if(rs.next()) {

                LoginEvent login1 = new LoginEvent(this);
                login1.setId(rs.getString("id"));
                login1.setPw(rs.getString("password"));
                return Optional.of(login1);

            } else {

                return Optional.empty();

            }


        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }
    }

    @Override
    public Optional<Kakao> OauthloginAccount(Kakao paramK) {
        return Optional.empty();
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
