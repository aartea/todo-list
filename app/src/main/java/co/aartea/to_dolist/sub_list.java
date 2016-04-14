package co.aartea.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class sub_list extends AppCompatActivity {
    //Instantiate

    ArrayList<String> elementList;
    ArrayAdapter<String> adapter;

    EditText et;
    ListView lv;

    ActionBar actionBar;

    String saveItem, getString, saveFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize
        elementList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);
        et = (EditText) findViewById(R.id.et);

        adapter = new ArrayAdapter<>
                (sub_list.this,android.R.layout.simple_list_item_1, elementList);

        //Set the adapter!
        lv.setAdapter(adapter);

        Intent i = getIntent();
        saveFromMain = i.getStringExtra("main_list");

        //Action bar - change the name from the source member
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sub_list);

        //Set another intent to pass to another class to handle persistency

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString = et.getText().toString();
                elementList.add(getString);
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
