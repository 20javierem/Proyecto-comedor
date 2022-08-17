package com.moreno.comedor.controlers;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.fragments.diners.DinersFragment;
import com.moreno.comedor.models.Diner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static List<Diner> all(){
        List<Diner> diners=new ArrayList<>();
        try{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference=database.getReference("diner_tbl");
            System.out.println(reference.getDatabase().getApp());
            Map<Integer,Diner> dinerMap=new HashMap<>();
            reference.orderByChild("id").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Diner diner=dataSnapshot.getValue(Diner.class);
                    diners.add(diner);
                    dinerMap.put(diner.getId(),diner);
                    if(DinersFragment.adapter!=null){
                        DinersFragment.adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Diner dinerUpdate=dataSnapshot.getValue(Diner.class);
                    Diner diner=dinerMap.get(dinerUpdate.getId());
                    diner.setDni(dinerUpdate.getDni());
                    diner.setActive(dinerUpdate.isActive());
                    diner.setMale(dinerUpdate.isMale());
                    diner.setPhone(dinerUpdate.getPhone());
                    diner.setNames(dinerUpdate.getNames());
                    diner.setLastNames(diner.getLastNames());
                    if(DinersFragment.adapter!=null){
                        DinersFragment.adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Diner dinerUpdate=dataSnapshot.getValue(Diner.class);
                    Diner diner=dinerMap.get(dinerUpdate.getId());
                    diners.remove(diner);
                    dinerMap.remove(dinerUpdate.getId());
                    if(DinersFragment.adapter!=null){
                        DinersFragment.adapter.notifyDataSetChanged();
                    }
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
        return diners;
    }
}
