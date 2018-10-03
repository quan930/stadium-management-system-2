package app.mrquan.dao.impl;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.pojo.Personnel;
import app.mrquan.util.DBUtil;

import java.sql.*;

public class PersonnelDAOImpl implements IPersonnelDAO {
    @Override
    public Personnel selectPersonnelById(String id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Personnel pojo = null;
        String sql="select  personnel.id,password,name,sex,age,telephone,email,balance,district,num as abrogate,administrator,stadium from personnel left join \n" +
                "\t(select count(*) as num,id from orders where cancel=true group by id) as a\n" +
                "\ton personnel.id = a.id\n" +
                "\twhere personnel.id = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                pojo = new Personnel();
                pojo.setId(resultSet.getString(1));//id
                pojo.setPassword(resultSet.getString(2));//密码
                pojo.setName(resultSet.getString(3));//名字
                pojo.setSex(resultSet.getBoolean(4));//性别
                pojo.setAge(resultSet.getInt(5));//年龄
                pojo.setTelephone(resultSet.getString(6));//电话
                pojo.setEmail(resultSet.getString(7));//邮箱
                Double balance = resultSet.getDouble(8);//余额
                if (resultSet.wasNull()){//为null
                    pojo.setBalance(null);
                }else {
                    pojo.setBalance(balance);
                }
                pojo.setDistrict(resultSet.getString(9));//区域
//                pojo.setAbrogate(resultSet.getInt(10));
                Integer abrogate = resultSet.getInt(10);//违约次数
                if (resultSet.wasNull()){//为null
                    pojo.setAbrogate(null);
                }else {
                    pojo.setAbrogate(abrogate);
                }
                pojo.setAdministrator(resultSet.getBoolean(11));//管理员
                pojo.setStadium(resultSet.getString(12));//场馆
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return pojo;
    }

    @Override
    public int update(Personnel pojo){//有待改进
        int yOrN = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "update personnel set id = ?,password = ?,name = ?,sex = ?,age = ?,telephone = ?,email = ?,balance = ?,district = ?,administrator = ?,stadium = ? " +
                "where id = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,  pojo.getId());
            preparedStatement.setString(2,pojo.getPassword());
            preparedStatement.setString(3,pojo.getName());
            preparedStatement.setBoolean(4,pojo.getSex());
            preparedStatement.setInt(5,pojo.getAge());
            preparedStatement.setString(6,pojo.getTelephone());
            preparedStatement.setString(7,pojo.getEmail());
            if (pojo.getBalance()==null){
                preparedStatement.setNull(8,Types.DOUBLE);
            }else {
                preparedStatement.setDouble(8,pojo.getBalance());
            }
            preparedStatement.setString(9,pojo.getDistrict());
            preparedStatement.setBoolean(10,pojo.getAdministrator());
            preparedStatement.setString(11,pojo.getStadium());
            preparedStatement.setString(12,pojo.getId());
            preparedStatement.addBatch();
            yOrN = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,null);
        }
        return yOrN;
    }
}