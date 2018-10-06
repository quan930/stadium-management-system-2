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
        final String sql = "select * from orders where (id = ? and startTime > now()) and (cancel = ? or cancel is ?)";
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
    public List<Order> selectOrderByStadiumAll(String stadium) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> pojos = new ArrayList<>();
        String sql = "select * from orders inner join\n" +
                "\t(select number as siteNumber from site where stadium = ?) as sites\n" +
                "\ton orders.siteNumber = sites.siteNumber\n" +
                "\torder by orders.startTime";//查询场馆订单 开始时间排序
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
    public String add(List<Order> orders) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder reserve = new StringBuilder();//预定信息
        final String per = "select ap.age,ap.balance,ap.abrogate,num.orderNum from (select personnel.id,personnel.age,personnel.balance,ab.abrogate from personnel left join \n" +
                "\t\t(select count(*) as abrogate,id from orders where cancel=true\n" +
                "\t\tgroup by id) as ab\n" +
                "\t\ton personnel.id = ab.id) as ap\n" +
                "\t\t\tleft join (select count(*) as orderNum,id from orders \n" +
                "\t\t\twhere startTime > now() and cancel is not true \n" +
                "\t\t\tgroup by id) as num\n" +
                "\t\ton ap.id = num.id\n" +
                "\t\twhere ap.id = ?";//用户年龄,余额,违约次数,当前订单
        final String ord = "select ageUp,ageLow,rent,timeConflict from site left join\n" +
                "\t(select count(*) as timeConflict,siteNumber as number from orders \n" +
                "\twhere cancel is not true and\n" +
                "\t((endTime > ? and endTime <= ?) or (startTime >= ? and startTime < ?))\n" +
                "\tgroup by siteNumber) as b\n" +
                "\ton site.number = b.number\n" +
                "\twhere site.number = ?";//年龄上限,年龄下限,租金,时间冲突
        final String insert = "insert into orders values (?,?,?,?,?,?,?,?,?)";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(per);
            preparedStatement.setString(1,orders.get(0).getId());
            resultSet = preparedStatement.executeQuery();
            int age = 0;//用户年龄
            double balance = 0;//余额
            int abrogate = 0;//违约次数 可以省略
            int orderNum = 0;//当前订单数量 可以省略
            while (resultSet.next()){
                age = resultSet.getInt(1);
                balance = resultSet.getDouble(2);
                abrogate = resultSet.getInt(3);

                orderNum = resultSet.getInt(4);
            }
//            System.out.println("年龄:"+age+"\t余额:"+balance+"\t违约次数:"+abrogate+"\t当前订单:"+orderNum);
            if (abrogate>3){//违约
                reserve.append("违约次数过多!");
                return reserve.toString();
            }
            if (orderNum+orders.size()>3){
                reserve.append("预定订单过多");
                return reserve.toString();
            }
            double allRent = 0;//全部租金
            boolean timeOrAge = true;//判断年龄时间是否允许
            for (Order o:orders) {
                preparedStatement = con.prepareStatement(ord);
                preparedStatement.setTimestamp(1,new java.sql.Timestamp(o.getStartTime().getTime()));
                preparedStatement.setTimestamp(2,new java.sql.Timestamp(o.getEndTime().getTime()));
                preparedStatement.setTimestamp(3,new java.sql.Timestamp(o.getStartTime().getTime()));
                preparedStatement.setTimestamp(4,new java.sql.Timestamp(o.getEndTime().getTime()));
                preparedStatement.setString(5,o.getSiteNumber());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    if (age<=resultSet.getInt(1)&&age>=resultSet.getInt(2)){//年龄可以
                        resultSet.getInt(4);
                        if (!resultSet.wasNull()){//不为null 时间冲突
                            reserve.append(o.getSiteNumber()+":时间冲突\t");
                            timeOrAge = false;
                        }
                    }else {//年龄不可以
                        reserve.append(o.getSiteNumber()+":年龄不允许");
                        resultSet.getInt(4);
                        if (!resultSet.wasNull()){//不为null 时间冲突
                            reserve.append(",时间冲突");
                        }
                        reserve.append("\t");
                        timeOrAge = false;
                    }
                    double rent =  resultSet.getDouble(3);//租金
                    long hour= (o.getEndTime().getTime()-o.getStartTime().getTime())/1000/60/60;//小时
                    allRent += rent*hour;//全部租金
                }
            }
            if (!timeOrAge){
                return reserve.toString();
            }
            System.out.println("租金"+allRent);
            if (allRent>balance){
                reserve.append("余额不足");
                return reserve.toString();
            }
            /**
             * 成功 向数据库加入数据 批处理
             */
            con.setAutoCommit(false);
            for (Order o:orders) {
                preparedStatement = con.prepareStatement(insert);
                preparedStatement.setString(1,o.getNumber());
                preparedStatement.setTimestamp(2,new java.sql.Timestamp(o.getReservationDate().getTime()));
                preparedStatement.setString(3,o.getSiteNumber());
                preparedStatement.setDate(4,new java.sql.Date(o.getLoanDate().getTime()));
                preparedStatement.setTimestamp(5,new java.sql.Timestamp(o.getStartTime().getTime()));
                preparedStatement.setTimestamp(6,new java.sql.Timestamp(o.getEndTime().getTime()));
                if (o.getOnTime()==null){
                    preparedStatement.setNull(7,Types.DOUBLE);
                }else {
                    preparedStatement.setBoolean(7,o.getOnTime());
                }
                preparedStatement.setString(8,o.getId());
                if (o.getCancel()==null){
                    preparedStatement.setNull(9,Types.DOUBLE);
                }else {
                    preparedStatement.setBoolean(9,o.getCancel());
                }
                preparedStatement.addBatch();//提交
                preparedStatement.executeBatch();
            }
            con.commit();
            con.setAutoCommit(true);
            return "成功";
        } catch (SQLException e) {
            reserve.append("失败");
            e.printStackTrace();
        }finally {//释放资源
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return reserve.toString();
    }

    @Override
    public int update(String number) {//取消订单
        int yOrN = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        final String sql = "update orders set cancel = true where number = ? and (startTime-now())/10000/100 > 1";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,number);
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