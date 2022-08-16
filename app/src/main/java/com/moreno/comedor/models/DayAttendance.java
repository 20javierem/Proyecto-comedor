package com.moreno.comedor.models;

import com.moreno.comedor.controlers.DinerAttendances;
import com.moreno.comedor.modelsFirebase.FBDayAttendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayAttendance {

    private Integer id;
    private Date date;
    private boolean state=true;
    private List<DinerAttendance> attendances=new ArrayList<>();
    private Integer totalDinerAttendance=0;
    private Integer totalDinerNotAttendance=0;
    private String percentageAtendet="0%";
    private String percentageNotAtendet="100%";
    private String menuDescription="";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<DinerAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<DinerAttendance> attendances) {
        this.attendances = attendances;
    }

    public Integer getTotalDinerAttendance() {
        return totalDinerAttendance;
    }

    public void setTotalDinerAttendance(Integer totalDinerAttendance) {
        this.totalDinerAttendance = totalDinerAttendance;
    }

    public Integer getTotalDinerNotAttendance() {
        return totalDinerNotAttendance;
    }

    public void setTotalDinerNotAttendance(Integer totalDinerNotAttendance) {
        this.totalDinerNotAttendance = totalDinerNotAttendance;
    }

    public String getPercentageAtendet() {
        return percentageAtendet;
    }

    public void setPercentageAtendet(String percentageAtendet) {
        this.percentageAtendet = percentageAtendet;
    }

    public String getPercentageNotAtendet() {
        return percentageNotAtendet;
    }

    public void setPercentageNotAtendet(String percentageNotAtendet) {
        this.percentageNotAtendet = percentageNotAtendet;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public DayAttendance(FBDayAttendance fbDayAttendance){
        setId(fbDayAttendance.getId());
        setPercentageAtendet(fbDayAttendance.getPercentageAtendet());
        setPercentageNotAtendet(fbDayAttendance.getPercentageNotAtendet());
        setDate(fbDayAttendance.getDate());
        setTotalDinerNotAttendance(fbDayAttendance.getTotalDinerNotAttendance());
        setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
        setState(fbDayAttendance.isState());
        setMenuDescription(fbDayAttendance.getMenuDescription());
        for (Integer integer: fbDayAttendance.getAttendances()){
            getAttendances().add(DinerAttendances.get(integer));
        }
    }
}
