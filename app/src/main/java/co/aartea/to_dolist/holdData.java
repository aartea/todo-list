package co.aartea.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;


/*************************************************************************************************
 * Created by aaron on 4/14/2016.
 *
 * Description: Helper class to retain information as the user will go back and forth throughout the app.
 *
 **************************************************************************************************/


public class holdData extends AppCompatActivity{
    
    static ArrayList<String> ListElements = new ArrayList<>();
    static ArrayList<ArrayList<String>> ListOLists = new ArrayList<>();

    String element, list;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //Set appropriate intents as a data exchange between classes.
            Intent grabElement = getIntent();   //Intent to grab the element within the master list
            Intent grabList = getIntent();      //Intent to grab the master list
            Intent sendElement = new Intent(holdData.this, MainActivity.class);
            Intent sendList = new Intent(holdData.this,MainActivity.class);

            //Variables to hold element
            element = grabElement.getStringExtra("sub_list_member");
            list = grabList.getStringExtra("main_list");

            //Add elements/list to appropriate ArrayList
            ListElements.add(element);
            ListOLists.add(ListElements.indexOf(element),ListOLists.get(index));

            //Push intent to be handled by other classes
            sendElement.putExtra("hold_element",ListElements.indexOf(element));
            sendList.putExtra("hold_list",ListOLists.indexOf(element));

            //Start the activities to initiate intent sequence
            startActivity(sendElement);
            startActivity(sendList);
    }
}
