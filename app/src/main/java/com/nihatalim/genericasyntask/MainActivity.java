package com.nihatalim.genericasyntask;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.nihatalim.generictaskbuilder.business.GenericTaskBuilder;
import com.nihatalim.generictaskbuilder.interfaces.*;


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

        this.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final GenericTaskBuilder builder = GenericTaskBuilder.instance();
                builder
                        .Context(getContext())
                        .ProcessTime(11000)
                        .build()
                        .OnPreState(new OnPreState() {
                            @Override
                            public void run() {
                                Log.d("GenericTask:", "OnPreState");
                            }
                        })

                        .OnBackgroundState(new OnBackgroundState() {
                            @Override
                            public Object run(Object[] objects) {
                                Log.d("GenericTask:", "OnBackgroundState");
                                for (int i=1;i<6;i++){
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    builder.getTask().publishProgressTask(i);
                                }
                                return null;
                            }
                        })
                        .OnPostState(new OnPostState() {
                            @Override
                            public void run(Object o) {
                                Log.d("GenericTask:", "OnPostState");
                                Toast.makeText(getContext(), "FINISH", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnProgressUpdateState(new OnProgressUpdateState() {
                            @Override
                            public void run(Object... obj) {
                                builder.getTask().getProgressDialog().setMessage(obj[0].toString());
                            }
                        })

                        .OnTimedOut(new TimeoutProcess() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), getString(R.string.timeout_error), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .execute();
            }
        });

    }

    private Context getContext(){
        return this;
    }
}
