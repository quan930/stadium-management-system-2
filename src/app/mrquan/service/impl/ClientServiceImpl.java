package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;
import app.mrquan.service.IClientService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientServiceImpl implements IClientService {
    @Override
    public List<Site> findSiteByName(String name) {
        return DAOFactory.getISiteDAOInstance().selectSiteByName(name);
    }

    @Override
    public List<Site> findSiteByStadium(String stadium) {
        return DAOFactory.getISiteDAOInstance().selectSiteByStadium(stadium);
    }

    @Override
    public List<Site> findSiteByTypeAndDistrict(String motionType, String district) {
        return DAOFactory.getISiteDAOInstance().selectSiteByTypeAndDistrict(motionType,district);
    }

    @Override
    public List<Site> findSiteByReserve(boolean yOrN) {
        return DAOFactory.getISiteDAOInstance().selectSiteByReserveYOrN(yOrN);
    }

    @Override
    public List<Site> listSiteByRent() {
        return DAOFactory.getISiteDAOInstance().selectAllSiteByRent();
    }

    @Override
    public List<Site> listSiteByReserve() {
        return DAOFactory.getISiteDAOInstance().selectAllSiteByOrderNumber();
    }

    @Override
    public List<Order> findOrdersToBeUsed(String id) {
        return DAOFactory.getIOrderDAOInstance().selectOrderByUser(id);
    }

    @Override
    public List<Order> listOrders(String id) {
        return DAOFactory.getIOrderDAOInstance().selectOrderByUserAll(id);
    }

    @Override
    public Map<String, Set<String>> listSportsInit() {
        Map<String,Set<String>> map = new HashMap<>();
        Set<String> name = new HashSet<>();//场地名称
        Set<String> stadium = new HashSet<>();//场馆名称
        Set<String> type = new HashSet<>();//场地类别
        Set<String> district = new HashSet<>();//场地区域
        List<Site> pojos = DAOFactory.getISiteDAOInstance().selectAllSite();
        for (int i = 0; i < pojos.size(); i++) {
            name.add(pojos.get(i).getName());
            stadium.add(pojos.get(i).getStadium());
            type.add(pojos.get(i).getMotionType());
            district.add(pojos.get(i).getDistrict());
        }
        map.put("name",name);
        map.put("stadium",stadium);
        map.put("type",type);
        map.put("district",district);
        return map;
    }

    @Override
    public int changePersonnel(Personnel personnel) {
        return DAOFactory.getIPersonnelDAOInstance().update(personnel);
    }

    @Override
    public int cancelReserve(String number) {
        return DAOFactory.getIOrderDAOInstance().update(number);
    }

    @Override
    public String reserve(List<Order> orders) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (Order o:orders) {
            calendar.add(Calendar.MINUTE,1);//订单编号时间+1 防止订单重复
            o.setNumber(o.getSiteNumber()+new SimpleDateFormat("yyyyMMddHHmm").format(calendar.getTime()));//订单编号
        }
//        for (Order o:orders) {
//            System.out.println(o);
//        }
        return DAOFactory.getIOrderDAOInstance().add(orders);
    }
}