package com.blackbirds.radiobuttoninrecyclerview;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blackbirds.radiobuttoninrecyclerview.Adapter.UserListAdapter;
import com.blackbirds.radiobuttoninrecyclerview.Interface.IRecyclerItemClickListener;
import com.blackbirds.radiobuttoninrecyclerview.ViewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements IRecyclerItemClickListener {

    MainActivityViewModel mainActivityViewModel;
    RecyclerView recyclerUserList;
    UserListAdapter adapter;
    AppCompatTextView txtHello;
    AppCompatButton btnSubmit;

    private boolean isSelected = true;
    private int paymentServiceID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerUserList = findViewById(R.id.recyclerUserList);
        txtHello = findViewById(R.id.txtHello);
        btnSubmit = findViewById(R.id.btnSubmit);

        getUser();

        btnSubmit.setOnClickListener(view -> {
            submitData();
        });
    }

    private void submitData() {
        if (isSelected) {
            Toast.makeText(MainActivity.this, "No payment item has been selected", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Selected Item ID: " + paymentServiceID, Toast.LENGTH_SHORT).show();
        }
    }

    private void getUser() {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getUserList().observe(this, userResponseList -> {
            if (userResponseList != null) {
                Log.e("MainActivity", "Data: " + userResponseList.get(0).getName());
                recyclerUserList.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerUserList.setHasFixedSize(true);
                adapter = new UserListAdapter(this, userResponseList, this);
                recyclerUserList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClickListener(View view, int pos) {
        if (pos == -1) {
            isSelected = true;
        } else {
            isSelected = false;
        }

        if (pos == 0) {
            txtHello.setVisibility(View.VISIBLE);
            paymentServiceID = pos + 1;
        } else {
            txtHello.setVisibility(GONE);
            paymentServiceID = pos + 1;
        }
    }
}