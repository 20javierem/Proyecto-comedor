package com.moreno.comedor.fragments.barcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.moreno.comedor.MainActivity;
import com.moreno.comedor.databinding.FragmentBarcodeBinding;

public class BarcodeFragment extends Fragment {
    private FragmentBarcodeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BarcodeViewModel barcodeViewModel =
                new ViewModelProvider(this).get(BarcodeViewModel.class);

        binding = FragmentBarcodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void init(){
        try {
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            if(MainActivity.diner!=null){
                Bitmap bitmap= barcodeEncoder.encodeBitmap(MainActivity.diner.getDni(), BarcodeFormat.CODABAR,850,400);
                binding.tvDNI.setText(MainActivity.diner.getDni());
                binding.barcode.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}