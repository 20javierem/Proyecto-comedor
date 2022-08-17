package com.moreno.comedor.controlers;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.models.Diner;

import java.util.HashMap;
import java.util.Map;

public class Diners {

    public static Diner get(Integer id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("diner_tbl");
        Diner diner=reference.orderByChild("id").equalTo(String.valueOf(id)).get().getResult().getValue(Diner.class);

        reference.orderByChild("id").equalTo(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Diner diner1=snapshot.getValue(Diner.class);
                diner.setDni(diner1.getDni());
                diner.setActive(diner1.isActive());
                diner.setPhone(diner1.getPhone());
                diner.setMale(diner1.isMale());
                diner.setNames(diner1.getNames());
                diner.setLastNames(diner1.getLastNames());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return diner;
    }

    public static void all(ArrayAdapter<Diner> adapter){
        try{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference=database.getReference("diner_tbl");
            System.out.println(reference.getDatabase().getApp());
            Map<Integer,Diner> dinerMap=new HashMap<>();
            reference.orderByChild("id").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Diner diner=dataSnapshot.getValue(Diner.class);
                    adapter.add(diner);
                    dinerMap.put(diner.getId(),diner);
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Diner dinerUpdate=dataSnapshot.getValue(Diner.class);
                    Diner diner=dinerMap.get(dinerUpdate.getId());
                    diner.setActive(dinerUpdate.isActive());
                    diner.setMale(dinerUpdate.isMale());
                    diner.setPhone(dinerUpdate.getPhone());
                    diner.setNames(dinerUpdate.getNames());
                    diner.setLastNames(diner.getLastNames());
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Diner dinerUpdate=dataSnapshot.getValue(Diner.class);
                    Diner diner=dinerMap.get(dinerUpdate.getId());
                    adapter.remove(diner);
                    dinerMap.remove(dinerUpdate.getId());
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
