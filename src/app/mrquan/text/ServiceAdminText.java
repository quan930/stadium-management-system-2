package app.mrquan.text;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Site;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ServiceAdminText {
    public static void main(String[] args) throws IOException {
        /**
         * number char(10) not null,
         * 	name char(10),
         * 	district char(10),
         * 	stadium char(10),
         * 	motionType char(10),
         * 	motionProfile char(100),
         * 	ageUp int,
         * 	ageLow int,
         * 	rent double,
         * 	picture blob,
         */
        /**
         * insert into site values ('aaa0001','沈阳羽毛球场地','沈阳','沈阳奥体中心','羽毛球','羽毛球是非常好的',50,15,35);
         * insert into site values ('aaa0002','沈阳羽毛球场地','沈阳','沈阳奥体中心','羽毛球','羽毛球是非常好的',50,15,35);
         * insert into site values ('baa0001','成都乒乓球场地','成都','成都体育','乒乓球','乒乓球good good!!!',55,20,40);
         * insert into site values ('baa0002','成都羽毛球场地','成都','成都体育','羽毛球','羽毛球是非常好的',45,15,30);
         */
        /**
         * 顾客添加场地
         */
//        Site site = new Site();
//        site.setNumber("baa0002");
//        site.setName("成都羽毛球场地");
//        site.setDistrict("成都");
//        site.setStadium("成都体育");
//        site.setMotionType("羽毛球");
//        site.setMotionProfile("羽毛球是非常好的");
//        site.setAgeUp(45);
//        site.setAgeLow(15);
//        site.setRent(30.0);
//        site.setPicture(picture("/Users/daquan/Desktop/3.png"));
//        int yOrN = ServiceFactory.getIAdminService().addSite(site);
//        System.out.println(yOrN);
        /**
         * 管理员查询
         */
//        List<Order> orders = ServiceFactory.getIAdminService().listOrder("沈阳奥体中心");
//        for (Order o:orders) {
//            System.out.println(o);
//        }
//        List<Site> sites = ServiceFactory.getIAdminService().listSiteByReserve("沈阳奥体中心");
        List<Site> sites = ServiceFactory.getIAdminService().listSiteByTurnover("沈阳奥体中心");
        for (Site s:sites) {
            System.out.println(s);
        }

    }
}
