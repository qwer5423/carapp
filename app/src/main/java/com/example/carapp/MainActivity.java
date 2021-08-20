package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dlong.rep.dlroundmenuview.DLRoundMenuView;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuClickListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuTouchListener;
import com.example.carapp.mqtt.Mqtt_Client;

import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private DLRoundMenuView dlRoundMenuView;
    private Context mContext = this;
    private VideoView videoView;
    private ImageButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlRoundMenuView = findViewById(R.id.dl_rmv);
        videoView = (VideoView) this.findViewById(R.id.rtspVideo);
        refresh = (ImageButton) this.findViewById(R.id.refresh);
        Mqtt_Client mqtt_client = new Mqtt_Client();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        dlRoundMenuView.setOnMenuClickListener(new OnMenuClickListener() {
            @Override
            public void OnMenuClick(int position) {
                //Toast.makeText(mContext, "点击了："+position,Toast.LENGTH_SHORT).show();
                //Log.e("TAG", "点击了："+position);
                if (position == -1) {
                    try {
                        mqtt_client.sendMsg("ac");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                /*
                if (position == 0) {
                    Toast.makeText(mContext, "上", Toast.LENGTH_SHORT).show();
                    try {
                        mqtt_client.sendMsg("0");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (position == 1) {
                    Toast.makeText(mContext, "右", Toast.LENGTH_SHORT).show();
                    try {
                        mqtt_client.sendMsg("1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (position == 2) {
                    Toast.makeText(mContext, "下", Toast.LENGTH_SHORT).show();
                    try {
                        mqtt_client.sendMsg("2");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (position == 3) {
                    Toast.makeText(mContext, "左", Toast.LENGTH_SHORT).show();
                    try {
                        mqtt_client.sendMsg("3");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                 */
            }
        });
        dlRoundMenuView.setOnMenuTouchListener((event, position) -> {
        //Toast.makeText(mContext, "点击了："+position+event.toString(),Toast.LENGTH_SHORT).show();
            if (position == 0) {
                Toast.makeText(mContext, "上", Toast.LENGTH_SHORT).show();
                try {
                    mqtt_client.sendMsg("0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (position == 1) {
                Toast.makeText(mContext, "右", Toast.LENGTH_SHORT).show();
                try {
                    mqtt_client.sendMsg("1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (position == 2) {
                Toast.makeText(mContext, "下", Toast.LENGTH_SHORT).show();
                try {
                    mqtt_client.sendMsg("2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (position == 3) {
                Toast.makeText(mContext, "左", Toast.LENGTH_SHORT).show();
                try {
                    mqtt_client.sendMsg("3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //VLC影像開啟
    private void RtspStream(String rtspUrl) {
        videoView.setVideoURI(Uri.parse(rtspUrl));
        videoView.requestFocus();
        videoView.start();
    }

    void refresh() {
        Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
        videoView.setVisibility(View.GONE);
        videoView.stopPlayback();
        videoView.setVisibility(View.VISIBLE);
        RtspStream("http://892c5832b13d.ngrok.io");
    }

}