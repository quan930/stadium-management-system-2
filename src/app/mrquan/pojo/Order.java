package app.mrquan.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private String number;//订单编号
    private Date reservationDate;//预定日期
    private String siteNumber;//预定场地编号
    private Date loanDate;//租借日期
    private Date startTime;
    private Date endTime;
    private Boolean onTime;//是否按时到达
    private String id;//顾客id
    private Boolean cancel;//取消true

    @Override
    public String toString() {
        return "订单编号:"+number+"\t预定日期:"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(reservationDate)
                +"\t预定场地编号:"+siteNumber+
                "\t租借场地日期:"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(loanDate)+
                "\t租借场地开始时间:"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(startTime)+
                "\t租借场地结束时间:"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(endTime)+
                "\t是否按时到场:"+onTime+"\t顾客ID:"+id+"\t取消:"+cancel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
