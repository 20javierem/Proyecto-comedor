package com.moreno.comedor.controlers;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.databinding.FragmentMenusBinding;
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
        reference.orderByChild("id").equalTo(String.valueOf(id)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FBDayAttendance fbDayAttendance=snapshot.getValue(FBDayAttendance.class);
                dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
                dayAttendance.setState(fbDayAttendance.isState());
                dayAttendance.setPercentageNotAtendet(fbDayAttendance.getPercentageNotAtendet());
                dayAttendance.setPercentageAtendet(fbDayAttendance.getPercentageAtendet());
                dayAttendance.setTotalDinerAttendance(fbDayAttendance.getTotalDinerAttendance());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dayAttendance;
    }

    public static void getOfDay(FragmentMenusBinding binding, Date date){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("dayAttendance_tbl");
        reference.orderByKey().equalTo(Utilities.formatoFecha.format(date)).limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                FBDayAttendance fbDayAttendance=snapshot.getValue(FBDayAttendance.class);
                binding.tvLaunch.setText(fbDayAttendance.getLaunch());
                binding.tvDessert.setText(fbDayAttendance.getDessert());
                binding.tvBeverage.setText(fbDayAttendance.getBeverage());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                FBDayAttendance fbDayAttendance=snapshot.getValue(FBDayAttendance.class);
                binding.tvLaunch.setText(fbDayAttendance.getLaunch());
                binding.tvDessert.setText(fbDayAttendance.getDessert());
                binding.tvBeverage.setText(fbDayAttendance.getBeverage());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
