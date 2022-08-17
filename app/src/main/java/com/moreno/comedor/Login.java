package com.moreno.comedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.moreno.comedor.databinding.ActivityLoginBinding;
import com.moreno.comedor.databinding.ActivityMainBinding;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init(){

    }
}