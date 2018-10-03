package app.mrquan.pojo;

public class Site {
    private String number;
    private String name;
    private String district;//区域
    private String stadium;//场馆
    private String motionType;//运动类型
    private String motionProfile;//运动简介
    private Integer ageUp;//年龄上限
    private Integer ageLow;//年龄下限
    private Double rent;//租金
    private Integer orderNumber;//当前订单数量
    private Double turnover;//营业额

    @Override
    public String toString() {
        return "场地编号:"+number+"\t场地名称:"+name+"\t场地所属区域:"+district+"\t所属场馆:"+stadium+"\t运动类型:"+motionType
                + "\t运动简介:"+motionProfile+"\t年龄上限:"+ageUp+"\t年龄下限:"+ageLow+"\t租金:"+rent+"\t订单数量:"+orderNumber+"\t营业额:"+turnover;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getMotionType() {
        return motionType;
    }

    public void setMotionType(String motionType) {
        this.motionType = motionType;
    }

    public String getMotionProfile() {
        return motionProfile;
    }

    public void setMotionProfile(String motionProfile) {
        this.motionProfile = motionProfile;
    }

    public Integer getAgeUp() {
        return ageUp;
    }

    public void setAgeUp(Integer ageUp) {
        this.ageUp = ageUp;
    }

    public Integer getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(Integer ageLow) {
        this.ageLow = ageLow;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }
}
