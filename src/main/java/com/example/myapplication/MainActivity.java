package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Fragment2.OnDataPass {

    private ActivityMainBinding binding;
    private int currentFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // نبدأ بتحميل Fragment1
        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {
            Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            if (current instanceof Fragment1) {
                // الانتقال إلى Fragment2
                Fragment2 fragment2 = Fragment2.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;

            } else if (current instanceof Fragment2) {
                // استدعاء الدالة الموجودة في Fragment2 (onContinueClicked)
                ((Fragment2) current).onContinueClicked();
            }

            // لاحقًا نضيف فحص إذا كان current instanceof Fragment3 مثلاً
        });
    }

    // تنفيذ الـ interface بعد الضغط على "Continue" داخل Fragment2
    @Override
    public void onDataPass(String name) {
        Toast.makeText(this, "Name Received: " + name, Toast.LENGTH_SHORT).show();

        // تمرير الاسم إلى Fragment3
        Fragment3 fragment3 = new Fragment3();
        Bundle bundle = new Bundle();
        bundle.putString("user_name", name);
        fragment3.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        currentFragment = 3;
    }

    public void setContinueButtonEnabled(boolean enabled) {
        Button btnContinue = findViewById(R.id.buttonContinue);
        if (btnContinue != null) {
            btnContinue.setEnabled(enabled);
        }
    }
}