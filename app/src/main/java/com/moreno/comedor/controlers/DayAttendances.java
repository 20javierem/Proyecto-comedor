package com.moreno.comedor.controlers;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.models.DayAttendance;
import com.moreno.comedor.modelsFirebase.FBDayAttendance;
import com.moreno.comedor.utils.Utilities;

import java.util.Date;
public class DayAttendances {

    public static DayAttendance get(Integer id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("dayAttendance_tbl");
        FBDayAttendance fbDayAttendance=reference.orderByChild("id").equalTo(String.valueOf(id)).get().getResult().getValue(FBDayAttendance.class);
        DayAttendance dayAttendance=new DayAttendance(fbDayAttendance);
        reference.orderByChild("id").equalTo(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FBDayAttendance fbDayAttendance=snapshot.getValue(FBDayAttendance.class);
                dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
                dayAttendance.setState(fbDayAttendance.isState());
                dayAttendance.setPercentageNotAtendet(fbDayAttendance.getPercentageNotAtendet());
                dayAttendance.setPercentageAtendet(fbDayAttendance.getPercentageAtendet());
                dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
                dayAttendance.setMenuDescription(fbDayAttendance.getMenuDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dayAttendance;
    }

    public static DayAttendance getOfDay(Date date){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("dayAttendance_tbl");
        FBDayAttendance fbDayAttendance=reference.orderByChild("date").startAt(Utilities.formatoFecha.format(date)).endAt(Utilities.formatoFecha.format(date)).get().getResult().getValue(FBDayAttendance.class);
        Task task=reference.orderByChild("date").startAt(Utilities.formatoFecha.format(date)).endAt(Utilities.formatoFecha.format(date)).get();

        task.addOnSuccessListener(result -> {

                }).addOnFailureListener(e -> {

                });
        if(fbDayAttendance!=null){
            DayAttendance dayAttendance=new DayAttendance(fbDayAttendance);
            reference.orderByChild("id").equalTo(String.valueOf(dayAttendance.getId())).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    FBDayAttendance fbDayAttendance=snapshot.getValue(FBDayAttendance.class);
                    dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
                    dayAttendance.setState(fbDayAttendance.isState());
                    dayAttendance.setPercentageNotAtendet(fbDayAttendance.getPercentageNotAtendet());
                    dayAttendance.setPercentageAtendet(fbDayAttendance.getPercentageAtendet());
                    dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
                    dayAttendance.setMenuDescription(fbDayAttendance.getMenuDescription());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return dayAttendance;
        }
        return null;
    }
}
