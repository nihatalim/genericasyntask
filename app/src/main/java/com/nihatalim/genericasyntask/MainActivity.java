package com.nihatalim.genericasyntask;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nihatalim.genericasyntask.business.GenericFunctionCaller;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {
    private Button btnClick = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnClick = (Button) findViewById(R.id.btnClick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), Integer.toString(caller(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return addition();
                    }
                })), Toast.LENGTH_SHORT).show();
            }
        });

        try {
            GenericFunctionCaller GenericFunctionCaller = new GenericFunctionCaller();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Context getContext(){
        return this;
    }

    public int addition(){
        return 3+6;
    }

    public Integer caller(Callable<Integer> func){
        try {
            return func.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
