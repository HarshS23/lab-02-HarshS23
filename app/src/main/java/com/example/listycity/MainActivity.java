package com.example.listycity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayList<String> dataList; // important

    // button section
    Button AddCityButton;
    Button DeleteCityButton;
    Button ConfirmButton;

    // input text

    EditText InputText;

    private int selectedPosition = -1;
    private String selectedCity = null;

    private boolean AddState = false;






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

        // list with some cities
        String []cities = {"Edmonton", "Calgary", "Toronto", "Moscow", "Sydney" , "Berlin", "Vienna" , "Tokyo", "Beijing", "Osaka" , "New Delhi"};
        // list with no cities
        //String []cities = {};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // logic begins

        // tap to select city (handling click event in listview)
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;
                selectedCity = cityAdapter.getItem(position);
                cityList.setItemChecked(position,true); // visual highlight
                Toast.makeText(MainActivity.this, "Selected: "+selectedCity, Toast.LENGTH_SHORT).show();

//                Toast.makeText(MainActivity.this, "This has City has been clicked: "+cityAdapter.getItem(position),
//                        Toast.LENGTH_SHORT).show();

            }
        });

        ConfirmButton.setEnabled(false); // set to false until add city is clicked
        AddCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddState = true;
                ConfirmButton.setEnabled(true);
                InputText.setText("");
                InputText.requestFocus();


            }
        });

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = InputText.getText().toString();

                if(dataList.contains(text)){
                    Toast.makeText(MainActivity.this, "Item has already been added", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(InputText == null|| text.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Item is empty", Toast.LENGTH_SHORT).show();
                    return;

                }

                AddState = false;
                ConfirmButton.setEnabled(false);
                dataList.add(text);
                cityAdapter.notifyDataSetChanged();
                InputText.setText("");
                Toast.makeText(MainActivity.this, "Added: ", Toast.LENGTH_SHORT).show();

            }
        });

        DeleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dataList == null || dataList.isEmpty()){
                    Toast.makeText(MainActivity.this, "Item is empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(selectedPosition < 0 || selectedPosition >= dataList.size()){
                    Toast.makeText(MainActivity.this, "Select a City", Toast.LENGTH_SHORT).show();
                    return;
                }


               String removed = dataList.remove(selectedPosition);
               cityAdapter.notifyDataSetChanged();

               // clear selection after delete
                cityList.clearChoices();
                cityList.requestLayout();

                selectedPosition = -1;
                Toast.makeText(MainActivity.this, "Deleted: "+ removed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}