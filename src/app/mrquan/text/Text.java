package app.mrquan.text;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dao.impl.PersonnelDAOImpl;
import app.mrquan.dao.impl.SiteDAOImpl;
import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;

import java.util.List;

public class Text {
    public static void main(String[] args){
//        PersonnelDAOImpl personnelDAO = new PersonnelDAOImpl();
//        Personnel personnel = personnelDAO.selectPersonnelById("a00003");
//        System.out.println(personnel);
//        personnel.setSex(true);
//        personnel.setId("adadasda");
//        int m = personnelDAO.update(personnel);
//        System.out.println(m);
//        Personnel personnel = null;
////        System.out.println(personnel.getStadium());
//        try {
//            System.out.println(personnel.getStadium());
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
//        System.out.println("hello world");
//
//        for (int i = 0; i <10 ; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print("* ");
//            }
//            System.out.println("");
//        }
//        int m = SiteDAOImpl.DAS;
//        SiteDAOImpl siteDAO = new SiteDAOImpl();
////        System.out.println(siteDAO.selectSiteByNumber("baa0002"));
//
//        long startTime = System.currentTimeMillis();
////        List<Site> pojos = siteDAO.selectAllSite();
//        List<Site> pojos = siteDAO.selectSiteByName("沈阳羽毛球场地");
//        long endTime = System.currentTimeMillis();
//
//        System.out.println(endTime-startTime);
//        for (int i = 0; i < pojos.size(); i++) {
//            System.out.println(pojos.get(i));
//        }
        List<Site> pojos = DAOFactory.getISiteDAOInstance().selectSiteByName("沈阳羽毛球场");
        for (int i = 0; i < pojos.size(); i++) {
            System.out.println(pojos.get(i));
        }
    }
}