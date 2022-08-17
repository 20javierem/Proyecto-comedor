package com.moreno.comedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.moreno.comedor.controlers.Diners;
import com.moreno.comedor.databinding.ActivityLoginBinding;
import com.moreno.comedor.databinding.ActivityMainBinding;
import com.moreno.comedor.models.Diner;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private TextView tvUser;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvUser=binding.tvUser;
        tvPassword=binding.tvPassword;

    }
    private void startSesion(){
        String nameUser=tvUser.getText().toString().trim();
        String pasword=tvPassword.getText().toString().trim();
        Diners.getByUser(nameUser);
    }
    private void init(){
        Intent intent=new Intent(this,MainActivity.class);
        Diner diner=new Diner();
        intent.putExtra("diner",diner);
        startActivity(intent);
    }
}