package app.mrquan.text;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dao.impl.PersonnelDAOImpl;
import app.mrquan.dao.impl.SiteDAOImpl;
import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
//        List<Site> pojos = DAOFactory.getISiteDAOInstance().selectAllSite();
////        List<Site> pojos = DAOFactory.getISiteDAOInstance().selectSiteByName("沈阳羽毛球场地");
//        long endTime = System.currentTimeMillis();
//
//        System.out.println(endTime-startTime);
//        for (int i = 0; i < pojos.size(); i++) {
//            System.out.println(pojos.get(i));
//        }
//        List<Site> pojos = DAOFactory.getISiteDAOInstance().selectSiteByName("沈阳羽毛球场");
//        for (int i = 0; i < pojos.size(); i++) {
//            System.out.println(pojos.get(i));
//        }
//        List<Order> pojos = DAOFactory.gitIOrderDAOInstance().selectOrderByUser("a00002");
//        Order order = null;
//        for (Order o:pojos) {
//            System.out.println(o);
//            o.setId("a00004");
////            order = o;
//        }
////        order = pojos.get(0);
////        pojos.get(0).setId("a00004");
////        pojos.get(0).setNumber("b00002201809151314");
////        pojos.get(1).setId("a00004");
////        pojos.get(1).setNumber("b00002201809160117");
//        long start = System.currentTimeMillis();
//        String s = DAOFactory.gitIOrderDAOInstance().add(pojos);
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//        System.out.println(s);
//        List<Site> sites = DAOFactory.getISiteDAOInstance().selectSiteByStadiumTurnover("沈阳奥体中心");
//        for (Site s:sites) {
//            System.out.println(s);
//        }
        for (int i = 6; i <= 22 ; i++) {
            System.out.println("<option value=\""+String.format("%02d",i)+":00\">"+String.format("%02d",i)+":00</option>");
        }
    }
}