package com.enjsoft.smitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;


    Caption caption;
    List<Caption> captionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.test);


        String m_localdatapath = this.getFilesDir().getPath() + "/test.smi";
        EnjLog.v("m_localdatapath", m_localdatapath);

        try {
            File f = new File(m_localdatapath);
            FileInputStream fs = new FileInputStream(f);

            CaptionParse(fs);

            fs.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EnjLog.v("자막 길이", captionList.size());
    }
    void CaptionParse(InputStream instream) {
        String xmlcontent = "", line;

        captionList = new ArrayList<>();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream,  "EUC-KR"));

            while ((line = rd.readLine()) != null) {
                xmlcontent += line + "\n";
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            return;
        }


        try {


            StringReader sr = new StringReader(xmlcontent);
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();

            parser.setInput(sr);

            String curtag = "";

            while (true) {
                int etype = parser.getEventType();
                //   EnjLog.v(tag , "type:" +etype);
                if (etype == XmlPullParser.END_DOCUMENT)
                    break;
                if (etype == XmlPullParser.START_DOCUMENT)
                    ;
                else if (etype == XmlPullParser.START_TAG) {
                    String tagname = parser.getName();
                    curtag = tagname;
                    EnjLog.v("start", tagname);

                    if (curtag.equals("SYNC")) {
                         caption = new Caption();

                        int attcount = parser.getAttributeCount();
                        for (int i = 0; i < attcount; i++) {
                            String attname = parser.getAttributeName(i);
                            String attvalue = parser.getAttributeValue(i);

                            if(attname.equals("Start")){
                                caption.setCaption_time(Integer.parseInt(attvalue));;
                            }
                            EnjLog.v(attname, attvalue);
                        }
                    }
                    parser.next();
                    if(curtag.equals("P")){
                        String txt = parser.getText();
                        if(txt == null){
                            txt = "";
                        }
                        caption.setCaption_text(txt);
                    }
                    String txt = parser.getText();
                    EnjLog.v("TAG:", txt);
                } else if (etype == XmlPullParser.END_TAG) {
                    String tagname = parser.getName();
                    if(tagname.equals("SYNC")){
                        captionList.add(caption);
                        EnjLog.v("end", caption.toString());
                    }
                    curtag = "";
                } else if (etype == XmlPullParser.TEXT) {
                }
                parser.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
