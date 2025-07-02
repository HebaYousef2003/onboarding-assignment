package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    public static Fragment2Binding fragment2Binding;
    private OnDataPass dataPasser;

    public interface OnDataPass {
        void onDataPass(String name);
    }
    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dataPasser = (OnDataPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDataPass");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment2Binding = Fragment2Binding.inflate(inflater, container, false);
        return fragment2Binding.getRoot();
    }

    public void onContinueClicked() {
        String name = fragment2Binding.editTextName.getText().toString().trim();
        String email = fragment2Binding.editTextEmail.getText().toString().trim();
        String university = fragment2Binding.editTextUniversity.getText().toString().trim();

        int selectedGenderId = fragment2Binding.radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedRadio = getView().findViewById(selectedGenderId);
        String gender = (selectedRadio != null) ? selectedRadio.getText().toString() : "";

        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        dataPasser.onDataPass(name);
    }
}
