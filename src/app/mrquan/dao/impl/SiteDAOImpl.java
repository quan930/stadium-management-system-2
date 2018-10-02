package app.mrquan.dao.impl;

import app.mrquan.dao.ISiteDAO;
import app.mrquan.pojo.Site;
import app.mrquan.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAOImpl implements ISiteDAO {
    @Override
    public Site selectSiteByNumber(String number) {
        Site site = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from site left join " +
                        "(select orderNum,money,orderNumber.number from " +
                        "(select count(*) as orderNum,siteNumber as number from orders where siteNumber = ? and ((cancel = false or cancel is null) and startTime > now())) as orderNumber " +
                        "left join " +
                        "(select sum(a.money)as money,a.siteNumber as number from " +
                        "(select (endTime-startTime)/10000*35 as money,siteNumber from orders " +
                        "where siteNumber = ? and ((cancel = false or cancel is null) and endTime < now())) as a) as money " +
                        "on money.number = orderNumber.number) as a " +
                        "on site.number = a.number "+
                        "where site.number = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,number);
            preparedStatement.setString(2,number);
            preparedStatement.setString(3,number);
            resultSet = preparedStatement.executeQuery();
            List<Site> pojos = select(resultSet);
            if (pojos.size()==1){
                site = pojos.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return site;
    }

    private List<Site> select(ResultSet resultSet) throws SQLException {
        List<Site> pojos = new ArrayList<>();
        Site site = null;
        while (resultSet.next()){
            site = new Site();
            site.setNumber(resultSet.getString(1));//场地编号
            site.setName(resultSet.getString(2));//场地名称
            site.setDistrict(resultSet.getString(3));//所属区域
            site.setStadium(resultSet.getString(4));//所属场馆
            site.setMotionType(resultSet.getString(5));//适合运动类型
            site.setMotionProfile(resultSet.getString(6));//运动简介
            site.setAgeUp(resultSet.getInt(7));//准入年龄上限
            site.setAgeLow(resultSet.getInt(8));//准入年龄下限
            site.setRent(resultSet.getDouble(9));//租金
            site.setOrderNumber(resultSet.getInt(10));//待使用订单
            site.setTurnover(resultSet.getDouble(11));//营业额
            pojos.add(site);
        }
        return pojos;
    }
}
