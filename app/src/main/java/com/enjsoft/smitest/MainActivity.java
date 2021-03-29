package com.enjsoft.smitest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    ImageView m_imageView;
    ArrayList viewList;

    int textCount = 0;

    Button btnCall;

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        //m_imageView = (ImageView) findViewById(R.id.test);
        //m_imageView.setVisibility(View.VISIBLE);


        // imageView가 추가될 linearLayout

        final LinearLayout layout = (LinearLayout) findViewById(R.id.testLayout);


        viewList = new ArrayList();

        final ArrayList tempList = new ArrayList();
        for(int i = 0; i < 100; i ++){
            tempList.add(i + "");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(textCount < tempList.size()) {

                    if(textCount == 0){

                        for(int i = 0; i < 3; i++) {
                            final TextView tempText = new TextView(mContext);
                            int viewId = ViewCompat.generateViewId();
                            EnjLog.v("upTv1.getText() 1 " , viewId);
                            tempText.setId(viewId);
                            viewList.add(viewId);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lp.gravity = Gravity.CENTER;
                            tempText.setLayoutParams(lp);
                            tempText.setText("-");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    layout.addView(tempText);
                                }
                            });
                        }

                        final TextView tempText = new TextView(mContext);
                        int viewId = ViewCompat.generateViewId();
                        tempText.setId(viewId);
                        viewList.add(viewId);
                        EnjLog.v("upTv1.getText() 2 " , viewId);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        tempText.setLayoutParams(lp);
                        tempText.setText(tempList.get(textCount).toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layout.addView(tempText);
                            }
                        });
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                for(int i = 0 ; i < 4; i++) {
//                                    TextView upTv1 = findViewById((Integer) viewList.get(i));
//
//                                    float tempFloat = upTv1.getTextSize();
//                                    EnjLog.v("upTv1.getText() 2 " , upTv1.getTextSize());
//
//                                    ObjectAnimator m_objectAnimator = new ObjectAnimator();
//                                    m_objectAnimator.ofFloat(upTv1, "translationY", -tempFloat).setDuration(2000).start();
//                                }
//                            }
//                        });
                    }else {
                        final TextView tempText = new TextView(mContext);
                        int viewId = ViewCompat.generateViewId();
                        tempText.setId(viewId);
                        viewList.add(viewId);
                        EnjLog.v("upTv1.getText()3 " , viewId);
                        EnjLog.v("viewList.length " , viewList.size());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        tempText.setLayoutParams(lp);
                        tempText.setText(tempList.get(textCount).toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layout.addView(tempText);
                            }
                        });
                        //TranslateAnimation
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0 ; i < 4; i++) {
                                    TextView upTv1 = findViewById((Integer) viewList.get(i));

                                    float tempFloat = upTv1.getTextSize();

                                    ObjectAnimator m_objectAnimator = new ObjectAnimator();
                                    m_objectAnimator.ofFloat(upTv1, "translationY", -tempFloat).setDuration(2000).start();
                                }
                            }
                        });
                        final TextView deleteTextView = findViewById((Integer) viewList.get(0));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layout.removeView(deleteTextView);
                            }
                        });
                        viewList.remove(0);
                    }
                    textCount++;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




//        ImageView iv = new ImageView(this);  // 새로 추가할 imageView 생성
//
//
//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(30);
//        paint.setTextAlign(Paint.Align.CENTER);
//
//        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
//        final Canvas canvas = new Canvas(bitmap);
//        canvas.drawText("안녕하세요오오오옹",bitmap.getWidth()/2 , bitmap.getHeight()/2, paint);
//
//
//        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        iv.setImageBitmap(bitmap);
//
//        layout.addView(iv); // 기존 linearLayout에 imageView 추가



       // ObjectAnimator m_objectAnimator = new ObjectAnimator();
//        m_objectAnimator.ofFloat(m_imageView, "translationY", -1500f).setDuration(3000).start();

    }
}
