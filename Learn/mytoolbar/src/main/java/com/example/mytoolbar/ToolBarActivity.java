package com.example.mytoolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tool_bar);
        Toolbar tb = findViewById(R.id.tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("toolbar", "toolbar被点击了");
            }
        });

        Toolbar tb2 = findViewById(R.id.tb2);
        tb2.setNavigationIcon(R.drawable.ic_launcher_foreground);
        tb2.setTitle("标题");
        tb2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("toolbar2", "toolbar2被点击了");
            }
        });
    }
}
