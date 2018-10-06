package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;
import app.mrquan.service.IAdminService;

import java.util.List;

public class AdminServiceImpl implements IAdminService {
    @Override
    public List<Order> listOrder(String stadium) {
        return DAOFactory.gitIOrderDAOInstance().selectOrderByStadiumAll(stadium);
    }

    @Override
    public List<Site> listSiteByReserve(String stadium) {
        return DAOFactory.getISiteDAOInstance().selectSiteByStadium(stadium);
    }

    @Override
    public List<Site> listSiteByTurnover(String stadium) {
        return DAOFactory.getISiteDAOInstance().selectSiteByStadiumTurnover(stadium);
    }

    @Override
    public int changePersonnel(Personnel personnel) {
        return DAOFactory.gitIPersonnelDAOInstance().update(personnel);
    }

    @Override
    public int addSite(Site site) {
        return DAOFactory.getISiteDAOInstance().insert(site);
    }
}
