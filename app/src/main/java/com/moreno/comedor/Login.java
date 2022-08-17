package com.moreno.comedor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moreno.comedor.controlers.Diners;
import com.moreno.comedor.databinding.ActivityLoginBinding;
import com.moreno.comedor.databinding.ActivityMainBinding;
import com.moreno.comedor.models.Diner;
import com.moreno.comedor.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private TextView tvUser;
    private TextView tvPassword;
    private Diner diner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvUser=binding.tvUser;
        tvPassword=binding.tvPassword;
        init();
    }
    private void startSesion(){
        String nameUser=tvUser.getText().toString().trim();
        String pasword=tvPassword.getText().toString().trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("diner_tbl");

        reference.orderByChild("nameUser").equalTo(nameUser).limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Diner diner= snapshot.getValue(Diner.class);
                if(diner!=null){
                    if(Utilities.desencriptar(diner.getPasword()).equals(pasword)){
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        intent.putExtra("diner",diner);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this,"No se encontró al usuario",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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
    private void init(){
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSesion();
            }
        });
    }
}