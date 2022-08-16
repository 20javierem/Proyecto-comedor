package com.moreno.comedor.modelsFirebase;

import com.moreno.comedor.models.DayAttendance;
import com.moreno.comedor.models.DinerAttendance;

import java.util.*;

public class FBDayAttendance {
    private Integer id;
    private Date date;
    private boolean state=true;
    private List<Integer> attendances=new ArrayList<>();
    private Integer totalDinerAttendance=0;
    private Integer totalDinerNotAttendance=0;
    private String percentageAtendet="0%";
    private String percentageNotAtendet="100%";
    private String menuDescription="";

    public FBDayAttendance(DayAttendance dayAttendance){
        setId(dayAttendance.getId());
        setPercentageAtendet(dayAttendance.getPercentageAtendet());
        setPercentageNotAtendet(dayAttendance.getPercentageNotAtendet());
        setDate(dayAttendance.getDate());
        setTotalDinerNotAttendance(dayAttendance.getTotalDinerNotAttendance());
        setTotalDinerAttendance(dayAttendance.getTotalDinerAttendance());
        setState(dayAttendance.isState());
        setMenuDescription(dayAttendance.getMenuDescription());
        for (DinerAttendance dinerAttendance:dayAttendance.getAttendances()){
            getAttendances().add(dinerAttendance.getId());
        }
    }
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

    public List<Integer> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Integer> attendances) {
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
}
