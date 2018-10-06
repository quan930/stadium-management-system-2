package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;
import app.mrquan.service.IClientService;

import java.util.List;

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
        return DAOFactory.gitIOrderDAOInstance().selectOrderByUser(id);
    }

    @Override
    public List<Order> listOrders(String id) {
        return DAOFactory.gitIOrderDAOInstance().selectOrderByUserAll(id);
    }

    @Override
    public int changePersonnel(Personnel personnel) {
        return DAOFactory.gitIPersonnelDAOInstance().update(personnel);
    }

    @Override
    public int cancelReserve(String number) {
        return DAOFactory.gitIOrderDAOInstance().update(number);
    }

    @Override
    public String reserve(List<Order> orders) {
        return DAOFactory.gitIOrderDAOInstance().add(orders);
    }
}