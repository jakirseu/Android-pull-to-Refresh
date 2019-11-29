package me.jakir.swipetorefreshinlistview;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayAdapter<String> mAdapter;
    ListView mListView;
    ArrayList<String> rainbowColors = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateListView();

        // add click listener on list item
        registerClickCallBack();
    }


    private void populateListView() {



        // crate list of list_items
        rainbowColors.add("Red");
        rainbowColors.add("Orange");
        rainbowColors.add("Yellow");
        rainbowColors.add("Blue");

        // build adapter
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, rainbowColors);

        // configure list view
        mListView = (ListView) findViewById(R.id.list_view);

        mListView.setAdapter(mAdapter);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

    }


    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view;
                String message = "You clicked # " + position + ", which is : "
                        + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void refreshContent() {

        mSwipeRefreshLayout.setRefreshing(false);
        rainbowColors.add("Another color");
        mAdapter.notifyDataSetChanged();

    }
}