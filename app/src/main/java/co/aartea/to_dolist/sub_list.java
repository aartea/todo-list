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



public class sub_list extends AppCompatActivity {
    //Instantiate
    final static int CHARMAX = 10;      //Sets max character count

    ArrayList<String> elementList;
    ArrayAdapter<String> adapter;

    EditText et;
    ListView lv;

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

        //Set toolbar name to list member's name
        getSupportActionBar().setTitle(saveFromMain);       //Passes string value from the intent.
                                                            //Note to self: It's all in intent.

        //Capture data being passed as a parameter in the setIntent method to save it into holdData class.
        Intent k = getIntent();
        k.putExtra("main_list", saveFromMain);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString = et.getText().toString();
                //If user does not input anything, a toast will appear for them to try again!
                if (getString.isEmpty()) {
                    Toast.makeText(sub_list.this, "Try typing out a list item", Toast.LENGTH_SHORT).show();
                }
                //Handle character count
                else if(getString.length() > CHARMAX){
                    Toast.makeText(sub_list.this, "Sorry, can't exceed "+CHARMAX+" characters!", Toast.LENGTH_SHORT).show();
                }
                else{
                    elementList.add(getString);
                    //Set another intent to pass to another class to handle persistency from sub_list
                    Intent j = new Intent(sub_list.this, holdData.class);
                    j.putExtra("sub_list_member", elementList.indexOf(getString));
                    //end intent, continue with method
                    adapter.notifyDataSetChanged();
                    et.getText().clear();
                    Snackbar.make(view, "Added!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                    //Inserted code from outside source: will make keyboard disappear after text is entered.
                    try{
                        InputMethodManager arbitraryName = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                    catch (Exception e){
                    }
                }
            }
        );

            //Longclick remove method
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                     String pos = elementList.get(position);
                     elementList.remove(pos);
                     adapter.notifyDataSetChanged();
                     return true;
                  }
                }
            );
        }//end of onCreate
    }//end of sub_list class
