package com.giamp.tareatabs;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;


import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Tarea_Tabs extends AppCompatActivity {
    LinearLayout tb2;
    EditText ed1;
    TextView tv1;

    VideoView vid;
    String vidAddress;
    Uri uV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea__tabs);
        vid = (VideoView) (findViewById(R.id.v1));
        tv1 = (TextView) (findViewById(R.id.tv1));
        tb2 = (LinearLayout) (findViewById(R.id.tab2));
        ed1 = (EditText) (findViewById(R.id.email));
        vidAddress = "android.resource://" + getPackageName() + "/" + R.raw.ppap;
        uV = Uri.parse(vidAddress);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(vid);
        vid.setMediaController(mc);
        vid.setVideoURI(uV);


        Resources res = getResources();
        final TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("Inicio");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Inicio", res.getDrawable(android.R.drawable.btn_star));
        tabs.addTab(spec);
        spec = tabs.newTabSpec("Registro");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Registro", res.getDrawable(android.R.drawable.sym_action_email));
        tabs.addTab(spec);
        spec = tabs.newTabSpec("Contacto");
        spec.setIndicator("Contacto", res.getDrawable(android.R.drawable.sym_contact_card));
        spec.setContent(R.id.tab3);
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(), "Ha cambiado de tab", Toast.LENGTH_SHORT).show();
                if (tabs.getCurrentTab() == 0) {
                    vid.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vid.start();

                        }
                    }, 2000);
                }
                if (tabs.getCurrentTab() != 0) {
                    vid.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vid.pause();
                            ed1.setText(null);
                            tv1.setText(null);


                        }
                    }, 2000);
                }
                if(tabs.getCurrentTab() == 1){
                    Toast.makeText(getApplicationContext(), "Cuando termine pulse en cualquier espacio de la pantalla", Toast.LENGTH_LONG).show();
                }
            }
        });
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    String usInput = ed1.getText().toString();

                    if(usInput.matches("")){
                        tv1.setText("No ha introducido nada porfavor intente denuevo");

                    }
                    else{

                        tv1.setText("Su email es: "+ed1.getText().toString());

                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();


                }

            }
        });

    }
}


