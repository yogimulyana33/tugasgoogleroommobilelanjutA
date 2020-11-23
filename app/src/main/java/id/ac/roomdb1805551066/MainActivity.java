package id.ac.roomdb1805551066;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    EditText editText;
    RecyclerView recyclerView;
    Button btnAdd,btnReset;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        editText = findViewById(R.id.edit_text);
        btnAdd = findViewById(R.id.btn_add);
        btnReset = findViewById(R.id.btn_reset);
        recyclerView = findViewById(R.id.recycler_view);

        //initialize db
        database = RoomDB.getInstance(this);
        //store db value in data list
        dataList = database.maindDao().getAll();

        //initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        adapter = new MainAdapter(MainActivity.this,dataList);
        //set adapter
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from edit text
                String sText = editText.getText().toString().trim();
                //check condition
                if (!sText.equals("")){
                    //when text empty = initialize main data
                    MainData data = new MainData();
                    //set text on main data
                    data.setText(sText);
                    //insert text in db
                    database.maindDao().insert(data);
                    //clear edit text
                    editText.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.maindDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all data from db
                database.maindDao().reset(dataList);
                //notify when data is deleted
                dataList.clear();
                dataList.addAll(database.maindDao().getAll());
                adapter.notifyDataSetChanged();

            }
        });

    }
}
