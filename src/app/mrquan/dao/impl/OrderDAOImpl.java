package app.mrquan.dao.impl;

import app.mrquan.dao.IOrderDAO;
import app.mrquan.pojo.Order;
import app.mrquan.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements IOrderDAO {
    @Override
    public List<Order> selectOrderByUser(String id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> pojos = new ArrayList<>();
        String sql = "select * from orders where (id = ? and startTime > now()) and (cancel = ? or cancel is ?)";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setNull(3,Types.BOOLEAN);
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
    public List<Order> selectOrderByUserAll(String id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> pojos = new ArrayList<>();
        String sql = "select * from orders where id = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
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
    public String add(Order order) {
        return null;
    }

    @Override
    public int update(Order order) {//取消订单
        int yOrN = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "update orders set cancel = true where number = ? and (startTime-now())/10000/100 > 1";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,order.getNumber());
            yOrN = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,null);
        }
        return yOrN;
    }

    private List<Order> select(ResultSet resultSet) throws SQLException {
        List<Order> pojos = new ArrayList<>();
        Order order = null;
        while (resultSet.next()){
            order = new Order();
            order.setNumber(resultSet.getString(1));//订单编号
            order.setReservationDate(resultSet.getTimestamp(2));//预定日期
            order.setSiteNumber(resultSet.getString(3));//预定场地编号
            order.setLoanDate(resultSet.getDate(4));//租借日期
            order.setStartTime(resultSet.getTimestamp(5));
            order.setEndTime(resultSet.getTimestamp(6));
            Boolean balance = resultSet.getBoolean(7);//是否按时到场
            if (resultSet.wasNull()){//为null
                order.setOnTime(null);
            }else {
                order.setOnTime(balance);
            }
            order.setId(resultSet.getString(8));//顾客id
            Boolean cancel = resultSet.getBoolean(9);//取消
            if (resultSet.wasNull()){//为null
                order.setCancel(null);
            }else {
                order.setCancel(cancel);
            }
            pojos.add(order);
        }
        return pojos;
    }
}