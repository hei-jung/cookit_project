package net.teamcadi.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cameraBtn = (Button)findViewById(R.id.cameraBtn);
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        Button homeBtn = (Button)findViewById(R.id.homeBtn);
        Button refBtn = (Button)findViewById(R.id.refBtn);
        Button commBtn = (Button)findViewById(R.id.commBtn);
        Button recipeBtn = (Button)findViewById(R.id.recipeBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(cameraIntent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        refBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refIntent = new Intent(MainActivity.this, RefriActivity.class);
                startActivity(refIntent);
            }
        });

        commBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commIntent = new Intent(MainActivity.this, CommActivity.class);
                startActivity(commIntent);
            }
        });

        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(MainActivity.this, RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });
    }
}
