package com.moreno.comedor.fragments.menus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.moreno.comedor.controlers.DayAttendances;
import com.moreno.comedor.databinding.FragmentMenusBinding;
import com.moreno.comedor.models.DayAttendance;

import java.util.Date;


public class MenusFragment extends Fragment {
    private FragmentMenusBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MenusViewModel menusViewModel = new ViewModelProvider(this).get(MenusViewModel.class);
        binding = FragmentMenusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DayAttendances.getOfDay(binding,new Date());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}