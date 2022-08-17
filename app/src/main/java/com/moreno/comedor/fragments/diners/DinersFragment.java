package com.moreno.comedor.fragments.diners;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.moreno.comedor.R;
import com.moreno.comedor.adapters.DinerAdapter;
import com.moreno.comedor.controlers.Diners;
import com.moreno.comedor.databinding.FragmentDinersBinding;
import com.moreno.comedor.models.Diner;

public class DinersFragment extends Fragment {
    private ListView listView;
    private FragmentDinersBinding binding;

    public DinersFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDinersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView=root.findViewById(R.id.listView);
        ArrayAdapter<Diner> adapter=new DinerAdapter(root.getContext(),0);
        Diners.all(adapter);
        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}