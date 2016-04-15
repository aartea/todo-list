package co.aartea.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Instantiate

    String getString;

    ListView lv;
    EditText et;

    ArrayList<String> lists;             //Create an array that will hold array lists
    ArrayAdapter<String> adapter;        //To port over info. from ArrayList to ListView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        lv = (ListView) findViewById(R.id.listView);
        et = (EditText) findViewById(R.id.et);
        lists = new ArrayList<>();
        adapter = new ArrayAdapter<>
                (MainActivity.this,android.R.layout.simple_list_item_1, lists);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set adapter
        lv.setAdapter(adapter);

     //Declares onClick method
     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
     assert fab != null;
     fab.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View view) {
        getString = et.getText().toString();
            if(getString.isEmpty()){
                Toast.makeText(MainActivity.this, "Try typing out a title", Toast.LENGTH_SHORT).show();
            }
            else{
                lists.add(getString);
                adapter.notifyDataSetChanged();
                et.getText().clear();
                Snackbar.make(view, "Added!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
     //Inserted code from outside source: will make keyboard disappear after text is entered. This is not my code.
            try{
                InputMethodManager arbitraryName = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            catch(Exception e){}
         }});

     //Sets the intent
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, sub_list.class);
                i.putExtra("main_list", lists.get(position));
                startActivity(i);
            }
        });

    //Longclick remove method
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String pos = lists.get(position);
                lists.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
