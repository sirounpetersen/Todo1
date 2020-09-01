package com.example.simpletodo;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnadd;
    EditText etitem;
    RecyclerView rvitems;
    ItemsAdapter itemsAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnadd);
        etitem = findViewById(R.id.etitem);
        rvitems = findViewById(R.id.rvitems);


        loadItems();
        items.add("Buy milk");
        items.add("Go to the gym");
        items.add("Have fun!");

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                //delete the item from the model
                items.remove(position);
                //notify the adapter
                itemsAdapter.notifyItemsRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };
        itemsAdapter = new ItemsAdapter(items, onClickListener);
        rvitems.setAdapter(Adapter);
        rvitems.setLayoutManager(new LinearLayoutManager(this));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etitem.getText().toString();
                //notify adapter that an item is inserted
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size() - 1);

                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT);
                saveItems();
            }

        });

        private File getDataFile () {
            return new File(getFilesDir(), "data.txt");
        }
        //this function will load items by reading every line of the data file

        private void loadItems () {
            try {
                items = new ArrayList<>(FileUtils.readlines(getDataFile(), Charset.defaultCharset()));
            } catch (IDException e) {
                e.printStackTrace();
                log.e("MainActivity", "Error reading items", e);
                items = new ArrayList<>();
            }
        }

        private void saveItems() {
            FileUtils.writeLines(getDataFile(), items);
        } catch(IDException e){
            log.e("MainActivity", "Error writing items", e);
        }

    }
}