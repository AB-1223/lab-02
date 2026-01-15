package com.example.listycity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    private int selectedIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        Button btnAddCity = findViewById(R.id.btn_add_city);
        Button btnDeleteCity = findViewById(R.id.btn_delete_city);
        Button btnConfirm = findViewById(R.id.btn_confirm);
        EditText etCityName = findViewById(R.id.et_city_name);
        View bottomRow = findViewById(R.id.bottom_row);
        String []cities = { "Edmonton", "Vancouver","Toronto"};
        dataList = new ArrayList<>();
        dataList = new ArrayList<>(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);


        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });

        btnAddCity.setOnClickListener(v -> {
            etCityName.setText("");
            bottomRow.setVisibility(View.VISIBLE);
            etCityName.requestFocus();
        });


        // CONFIRM -> add & hide
        btnConfirm.setOnClickListener(v -> {
            String newCity = etCityName.getText().toString().trim();
            if (newCity.isEmpty()) return;


            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
            bottomRow.setVisibility(View.GONE);
        });


        // DELETE CITY -> delete selected
        btnDeleteCity.setOnClickListener(v -> {
            if (selectedIndex == -1) return;


            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();
            selectedIndex = -1;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
