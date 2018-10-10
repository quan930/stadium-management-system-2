package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/adminAdd")
public class AdminAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        if(!ServletFileUpload.isMultipartContent(request)){
//            System.out.println("提交的不是表单数据");
            return;
        }
        Site site = new Site();
        Personnel personnel = (Personnel) request.getSession().getAttribute("user");
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem s:items) {
                if (s.isFormField()){
                    String name = new String(s.getFieldName().getBytes("iso-8859-1"), "utf-8");
                    String value=new String(s.getString().getBytes("ISO-8859-1"),"utf-8");
                    switch (name){
                        case "number":
                            site.setNumber(value);
                            break;
                        case "name":
                            site.setName(value);
                            break;
                        case "district":
                            site.setDistrict(value);
                            break;
                        case "ageUp":
                            site.setAgeUp(Integer.valueOf(value));
                            break;
                        case "ageLow":
                            site.setAgeLow(Integer.valueOf(value));
                            break;
                        case "rent":
                            site.setRent(Double.valueOf(value));
                            break;
                        case "motionType":
                            site.setMotionType(value);
                            break;
                        case "motionProfile":
                            site.setMotionProfile(value);
                            break;
                        default:
                            break;
                    }
//                    System.out.println(name);
                }else {
//                    System.out.println("有文件");
                    byte[] headOne;
                    InputStream in = s.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
                    byte[] b = new byte[1000];
                    int n;
                    while ((n = in.read(b)) != -1) {
                        bos.write(b, 0, n);
                    }
                    in.close();
                    bos.close();
                    headOne = bos.toByteArray();
                    Byte[] headTwo = new Byte[headOne.length];
                    int i = 0;
                    for (byte c : headOne) headTwo[i++] = c;
                    site.setPicture(headTwo);
                    System.out.println(headTwo.length);
                }
            }
            site.setStadium(personnel.getStadium());
            System.out.println(site);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        int m = ServiceFactory.getIAdminService().addSite(site);
        if (m==1){
            out.print("<script language='javascript'>alert('成功');window.location.href='adminAdd.jsp';</script>");
        }else {
            out.print("<script language='javascript'>alert('失败');window.location.href='adminAdd.jsp';</script>");
        }
    }
}