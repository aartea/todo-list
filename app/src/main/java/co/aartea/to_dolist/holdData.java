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

    //Public variables to be accessed by methods
public class holdData extends AppCompatActivity{

    static ArrayList<String> ListElements = new ArrayList<>();
    static ArrayList<ArrayList<String>> ListOLists = new ArrayList<>();

    String element, list;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent grabElement = getIntent();   //Intent to grab the element within the master list
            Intent grabList = getIntent();      //Intent to grab the master list
            Intent sendElement = new Intent(holdData.this, MainActivity.class);
            Intent sendList = new Intent(holdData.this,MainActivity.class);

            element = grabElement.getStringExtra("sub_list_member");
            list = grabList.getStringExtra("main_list");

            ListElements.add(element);
            ListOLists.add(ListElements.indexOf(element),ListOLists.get(index));

            sendElement.putExtra("hold_element",ListElements.indexOf(element));
            sendList.putExtra("hold_list",ListOLists.indexOf(element));
            startActivity(sendElement);
            startActivity(sendList);
    }
}
