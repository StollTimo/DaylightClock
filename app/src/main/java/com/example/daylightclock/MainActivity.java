package com.example.daylightclock;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daylightclock.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private int mStackLevel = 0;
    private FloatingActionButton addAlarmButton;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private LinkedList<String> alarmList = new LinkedList<String>(Arrays.asList("test0", "test1", "test2"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().setFragmentResultListener("time", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String StringResult = result.getString("bundleKey");
                alarmList.add(StringResult);
                System.out.println(StringResult);
                updateAlarmList();
            }
        });

        addAlarmButton = findViewById(R.id.addAlarmButton);
        addAlarmButton.setOnClickListener(view -> showTimePickerDialog());

        recyclerView = findViewById(R.id.alarmList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter = new ListAdapter(alarmList));

    }
//ss
    private void updateAlarmList(){
        listAdapter.notifyItemChanged(alarmList.size());
    }

    public void showTimePickerDialog() {
        mStackLevel++;

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        TimePickerFragment newTimePickerFragment = TimePickerFragment.newInstance("Test1", "Test2");
        newTimePickerFragment.show(ft, "dialog");
    }
}