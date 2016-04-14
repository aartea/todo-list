package co.aartea.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class sub_list extends AppCompatActivity {

    ArrayList<String> elementList;
    ArrayAdapter<String> adapter;

    EditText et;

    String saveItem;
    String getString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        elementList = new ArrayList<>();
        et = (EditText) findViewById(R.id.et);
        adapter = new ArrayAdapter<>
                (sub_list.this,android.R.layout.simple_list_item_1, elementList);

        Intent i = getIntent();
        saveItem = i.getStringExtra("main_list");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString = et.getText().toString();
                elementList.add(saveItem);
                adapter.notifyDataSetChanged();
                et.getText().clear();
                //if successful, add a Snackbar saying so
                //else, handle error and catch it!
                Snackbar.make(view, "Added item!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //et.getText().clear();
            }
        });
    }

}
