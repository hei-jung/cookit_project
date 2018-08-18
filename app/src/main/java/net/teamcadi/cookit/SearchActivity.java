package net.teamcadi.cookit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText inputText = (EditText)findViewById(R.id.inputText);
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        final ListView searchList = (ListView)findViewById(R.id.searchList);

        final ArrayList<String> strList = new ArrayList<String>();
        final ArrayAdapter strAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strList);
        searchList.setAdapter(strAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = inputText.getText().toString();
                strList.add(inputStr);
                strAdapter.notifyDataSetChanged();
            }
        });
    }
}
