package com.moreno.comedor.controlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.models.DayAttendance;
import com.moreno.comedor.models.Diner;
import com.moreno.comedor.models.DinerAttendance;
import com.moreno.comedor.modelsFirebase.FBDayAttendance;
import com.moreno.comedor.modelsFirebase.FBDinerAttendance;
import com.moreno.comedor.utils.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DinerAttendances {

    public static void getOfDayAttendance(DayAttendance dayAttendance,FBDayAttendance fbDayAttendance){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("dinnerAttendance_tbl");
        Map<Integer,Diner> dinerMap=new HashMap<>();
    }

    public static DinerAttendance get(Integer id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("dinnerAttendance_tbl");
        FBDinerAttendance fbDinerAttendance=reference.orderByChild("id").equalTo(String.valueOf(id)).get().getResult().getValue(FBDinerAttendance.class);
        DinerAttendance dinerAttendance=new DinerAttendance(fbDinerAttendance);

        reference.orderByChild("id").equalTo(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FBDinerAttendance fbDinerAttendance1=snapshot.getValue(FBDinerAttendance.class);
                dinerAttendance.setAttended(fbDinerAttendance1.isAttended());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dinerAttendance;
    }
}
