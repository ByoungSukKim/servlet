package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.BssStation;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BssStationRepository implements StationRepository{

    private final DataSource dataSource;

    public BssStationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Optional<BssStation>> ListBSS() {

        String sql = "select * from TN_BSMS_BSS_INFO";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Optional<BssStation>> resultList = new ArrayList<>();

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);

            rs = pstm.executeQuery();
            while (rs.next()) {

                BssStation bssStation = new BssStation();
                bssStation.setBssId(rs.getString("BSS_ID"));
                bssStation.setBssNm(rs.getString("BSS_NM"));
                bssStation.setBssInstallYmd(rs.getString("BSS_INSTALL_YMD"));
                bssStation.setBssArea(rs.getString("BSS_AREA"));
                bssStation.setBssAddress(rs.getString("BSS_ADDRESS"));
                bssStation.setBssUseYn(rs.getString("BSS_USE_YN"));
                bssStation.setBssLati(String.valueOf(rs.getDouble("BSS_LATI")));
                bssStation.setBssLongi(String.valueOf(rs.getDouble("BSS_LONGI")));
                resultList.add(Optional.of(bssStation));

            }

            return resultList;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }

    }

    @Override
    public BssStation DetailBSS(String id) {

        String sql = "select * from TN_BSMS_BSS_INFO where bss_id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Optional<BssStation> resultList = Optional.of(new BssStation());

        try {

            conn = getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                BssStation bssStation = new BssStation();
                bssStation.setBssId(rs.getString("BSS_ID"));
                bssStation.setBssNm(rs.getString("BSS_NM"));
                bssStation.setBssInstallYmd(rs.getString("BSS_INSTALL_YMD"));
                bssStation.setBssArea(rs.getString("BSS_AREA"));
                bssStation.setBssAddress(rs.getString("BSS_ADDRESS"));
                bssStation.setBssUseYn(rs.getString("BSS_USE_YN"));
                bssStation.setBssLati(String.valueOf(rs.getDouble("BSS_LATI")));
                bssStation.setBssLongi(String.valueOf(rs.getDouble("BSS_LONGI")));
                return bssStation;
            }



        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstm);
        }
        return null;
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
