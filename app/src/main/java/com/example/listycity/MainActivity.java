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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declare the variables so you will be able to reference it later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    // button section
    Button AddCityButton;
    Button DeleteCityButton;
    Button ConfirmButton;

    // input text

    EditText InputText;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initlization
        cityList = (ListView) findViewById(R.id.city_list);
        AddCityButton = (Button) findViewById(R.id.AddCityButton);
        DeleteCityButton = (Button)findViewById(R.id.DeleteCityButton);
        ConfirmButton = (Button)findViewById(R.id.ConfirmButton);
        InputText = (EditText)findViewById(R.id.InputCity);


        String []cities = {"Edmonton", "Calgary", "Toronto", "Moscow", "Sydney" , "Berlin", "Vienna" , "Tokyo", "Beijing", "Osaka" , "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


//        AddCityButton.setOnClickListener(new View.OnClickListener(){
//        });
    }
}