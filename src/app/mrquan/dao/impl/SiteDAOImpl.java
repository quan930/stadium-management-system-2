package app.mrquan.dao.impl;

import app.mrquan.dao.ISiteDAO;
import app.mrquan.pojo.Site;
import app.mrquan.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAOImpl implements ISiteDAO {
    //查询全部Site sql 语句
    final private String selectSql = "select b.number,name,district,stadium,motionType,motionProfile,ageUp,ageLow,rent,picture,orderNum,orders.times*b.rent as turnover from \n" +
            "\t(select  site.number,name,district,stadium,motionType,motionProfile,ageUp,ageLow,rent,picture,orderNum from site left join\n" +
            "\t\t(select count(*) as orderNum,siteNumber as number from orders \n" +
            "\t\t\twhere startTime > now() and (cancel = false or cancel is null)\n" +
            "\t\t\tgroup by siteNumber) as orderNum\n" +
            "\t\ton site.number = orderNum.number) as b\t\n" +
            "\tleft join\n" +
            "\t(select sum(a.times) as times,siteNumber as number from\n" +
            "\t\t(select (endTime-startTime)/10000 as times,siteNumber from orders\n" +
            "\t\t\twhere endTime < now() and (cancel = false or cancel is null)) as a\n" +
            "\t\tgroup by siteNumber) as orders\n" +
            "\ton orders.number = b.number ";
    @Override
    public Site selectSiteByNumber(String number) {//场地编号
        Site site = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = selectSql+ "where b.number = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,number);
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

    @Override
    public List<Site> selectSiteByName(String name) {//场地名
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql + "where b.name = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectSiteByStadium(String stadium) {//场馆 预定量排序
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql + "where b.stadium = ? order by b.orderNum desc";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,stadium);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectSiteByStadiumTurnover(String stadium) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql + "where b.stadium = ? order by turnover desc";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,stadium);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectSiteByTypeAndDistrict(String type, String district) {//类别,区域
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql + "where b.motionType = ? and b.district = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,type);
            preparedStatement.setString(2,district);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectSiteByReserveYOrN(boolean yOrN) {//是否预定
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql;
        if (yOrN){//有预定
            sql = selectSql + "where b.orderNum is not null";
        }else {//没有预定
            sql = selectSql + "where b.orderNum is null";
        }
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectAllSiteByRent() {//租金排序
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql+"order by b.rent";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectAllSiteByOrderNumber() {//预定量排序
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql+"order by b.orderNum desc";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public List<Site> selectAllSite() {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> pojos = new ArrayList<>();
        String sql = selectSql;
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            pojos = select(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojos;
    }

    @Override
    public int insert(Site site) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        final String insert = "insert into site values (?,?,?,?,?,?,?,?,?,?)";
        int yOrN = 0;
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(insert);
            preparedStatement.setString(1,site.getNumber());
            preparedStatement.setString(2,site.getName());
            preparedStatement.setString(3,site.getDistrict());
            preparedStatement.setString(4,site.getStadium());
            preparedStatement.setString(5,site.getMotionType());
            preparedStatement.setString(6,site.getMotionProfile());
            preparedStatement.setInt(7,site.getAgeUp());
            preparedStatement.setInt(8,site.getAgeLow());
            preparedStatement.setDouble(9,site.getRent());
            byte[] bytes = new byte[site.getPicture().length];
            for(int j = 0; j < site.getPicture().length; j++) {
                bytes[j] = site.getPicture()[j];
            }
            preparedStatement.setBytes(10,bytes);
            yOrN = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,null);
        }
        return yOrN;
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
            byte [] byteBytes = resultSet.getBytes(10);//图片
            Byte[] bytes = new Byte[byteBytes.length];
            int i = 0;
            for (byte b : byteBytes) bytes[i++] = b;
            site.setPicture(bytes);
            site.setOrderNumber(resultSet.getInt(11));//待使用订单
            site.setTurnover(resultSet.getDouble(12));//营业额
            pojos.add(site);
        }
        return pojos;
    }
}