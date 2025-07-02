package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    private static final String ARG_USERNAME = "username";
    private String userName;

    public Fragment3() {
        // Required empty public constructor
    }

    // لإنشاء instance مع تمرير اسم المستخدم
    public static Fragment3 newInstance(String username) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString("user_name");  // المفتاح يجب أن يكون نفسه المستخدم في setArguments
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvUserName = view.findViewById(R.id.tvUserName);
        CheckBox cbConfirm = view.findViewById(R.id.cbConfirm);

        tvUserName.setText(userName != null ? userName : "User");

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.setContinueButtonEnabled(false);
        }

        cbConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (activity != null) {
                activity.setContinueButtonEnabled(isChecked);
            }
        });
    }}