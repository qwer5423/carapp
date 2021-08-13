package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.dlong.rep.dlroundmenuview.DLRoundMenuView;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuClickListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuTouchListener;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private DLRoundMenuView dlRoundMenuView;
    private Context mContext = this;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlRoundMenuView = findViewById(R.id.dl_rmv);
        videoView = (VideoView) this.findViewById(R.id.rtspVideo);
        dlRoundMenuView.setOnMenuClickListener(new OnMenuClickListener() {
            @Override
            public void OnMenuClick(int position) {
                //Toast.makeText(mContext, "点击了："+position,Toast.LENGTH_SHORT).show();
                //Log.e("TAG", "点击了："+position);
                if(position==-1){
                    RtspStream("https://3664de286dda.ngrok.io");
                }
            }
        });
        //dlRoundMenuView.setOnMenuTouchListener((event, position) -> {
            //Toast.makeText(mContext, "点击了："+position+event.toString(),Toast.LENGTH_SHORT).show();
        //});
    }


    //VLC影像開啟
    private void RtspStream(String rtspUrl) {
        videoView.setVideoURI(Uri.parse(rtspUrl));
        videoView.requestFocus();
        videoView.start();
    }
}