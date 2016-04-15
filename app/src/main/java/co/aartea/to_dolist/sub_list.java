package co.aartea.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

    String getString, saveFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize
        elementList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv);
        et = (EditText) findViewById(R.id.et);

        adapter = new ArrayAdapter<>
                (sub_list.this,android.R.layout.simple_list_item_1, elementList);

        //Set the adapter!
        lv.setAdapter(adapter);

        Intent i = getIntent();
        saveFromMain = i.getStringExtra("main_list");
        //Set another intent to pass to another class to handle persistency


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString = et.getText().toString();
                elementList.add(getString);
                adapter.notifyDataSetChanged();
                et.getText().clear();
                //if successful, add a Snackbar saying so
                //else, handle error and catch it!
                Snackbar.make(view, "Added!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //et.getText().clear();
                //Inserted code from outside source: will make keyboard disappear after text is entered.
                try{
                    InputMethodManager arbitraryName = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                catch(Exception e){}
            }
        });

        //Longclick remove method
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String pos = elementList.get(position);
                elementList.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
