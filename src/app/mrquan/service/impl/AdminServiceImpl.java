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
        return DAOFactory.getIOrderDAOInstance().selectOrderByStadiumAll(stadium);
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
        return DAOFactory.getIPersonnelDAOInstance().update(personnel);
    }

    @Override
    public int addSite(Site site) {
        if (site==null)
            return 0;
        if (site.getNumber()==null)
            return 0;
        if (site.getName()==null)
            return 0;
        if (site.getStadium()==null)
            return 0;
        if (site.getDistrict()==null)
            return 0;
        if (site.getMotionType()==null)
            return 0;
        if (site.getMotionProfile()==null)
            return 0;
        if (site.getAgeUp()==null)
            return 0;
        if (site.getAgeLow()==null)
            return 0;
        if (site.getRent()==null)
            return 0;
        if (site.getPicture()==null)
            return 0;
        if (site.getAgeLow()>=site.getAgeUp())
            return 0;
        return DAOFactory.getISiteDAOInstance().insert(site);
    }
}