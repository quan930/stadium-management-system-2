package app.mrquan.pojo;

import java.io.Serializable;

public class Personnel implements Serializable {
    private String id;//key
    private String password;
    private String name;
    private Boolean sex;
    private Integer age;
    private String telephone;
    private String email;
    private Double balance;//余额
    private String district;//区域
    private Integer abrogate;//爽约
    private Boolean administrator;//管理员
    private String stadium;//场馆

    @Override
    public String toString() {
        return "ID:"+id+"\t密码:"+password+"\t名字:"+name+"\t性别:"+sex+"\t年龄:"+age+"\t电话:" +telephone +"\t邮箱:"+email
                +"\t是否管理员:"+administrator+"\t顾客余额:" +balance+"\t顾客区域:"+district+"\t顾客爽约:"+abrogate+"\t管理员场馆:"+stadium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getAbrogate() {
        return abrogate;
    }

    public void setAbrogate(Integer abrogate) {
        this.abrogate = abrogate;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
