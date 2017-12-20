package com.qsgy.oymc;
import android.content.Context;
import android.view.View;
import android.app.*;
import android.os.*;
import android.widget.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class ControlActivity extends Activity
{
    public static Context context;
    ListView lv;
    ArrayList<Map<String,Object>> items=new ArrayList<Map<String,Object>>();//the datas
    SimpleAdapter adepter;//适配器

    LinearLayout wl;//白灯布局文件
    LinearLayout yl;

    Button connect;//连接按钮
    TextView isConnected;//显示是否连接的文本
    EditText sendMessage;//发送内容
    Button sendBtn;//发送按钮
    EditText ipText;//ip地址
    EditText portText;//端口
    boolean isConet=false;


    String tagertIP="";
    int tagertPort;
    Socket socket;
    PrintWriter writer;

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    Toast.makeText(ControlActivity.this,"Connected ",Toast.LENGTH_LONG ).show();
                    isConnected.setText("已链接");
                    break;
                case 0:
                    isConnected.setText("未链接");
                    break;
                case 2:
                    Toast.makeText(ControlActivity.this,"Erorr"+msg.obj,Toast.LENGTH_SHORT ).show();
                    break;

                default:
                    break;
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        context=this;//dan li mo shi
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//set main xml

        lv=(ListView)findViewById(R.id.list);//get listview
        ipText=(EditText) findViewById(R.id.ip_value);//ip
        portText=(EditText) findViewById(R.id.port_value);//port
        sendMessage=(EditText) findViewById(R.id.send_message);
        sendBtn=(Button) findViewById(R.id.send_btn);
        connect=(Button) findViewById(R.id.Connect);
        isConnected=(TextView) findViewById(R.id.isConnected);

        ipText.setText("192.168.4.1");//initial the text of ip
        portText.setText("8080");

        //适配
        adepter=new SimpleAdapter(this, items,
                R.layout.light_lv,new String[]{"name"},
                new int[]{R.id.light_show});
        Adapter adapter = new Adapter();
        lv.setAdapter(adapter);

    }


//连接按钮
    public void Connect(View v) throws IOException {
        if (!isConet)//if not conneted,then to connet
        {

            //在新的线程运行
            Thread t = new Thread() {
                @Override
                public void run() {

                    tagertIP = ipText.getText().toString();
                    tagertPort = Integer.parseInt(portText.getText().toString());

                    try {

                        //

                        socket = new Socket(tagertIP, tagertPort);
                        socket.setSoTimeout(3000);
                        writer = new PrintWriter(socket.getOutputStream());
                        //在子线程中刷新ui
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);//发送message给handler
                        isConet = true;
                    } catch (Exception e) {
                        //Toast.makeText(ControlActivity.this,"error"+e.toString(),Toast.LENGTH_LONG ).show(); // FIXME: 2017/12/3
                        e.printStackTrace();
                        Message message = new Message();
                        message.what = 2;
                        message.obj = e.toString();
                        mHandler.sendMessage(message);//发送message给handler
                    } finally {


                    }
                }
            };
            t.setName("net thread");
            t.start();
        }else {//if connected then
            socket.close();
            isConnected.setText("未连接");
            Toast.makeText(this,"关闭Socket",Toast.LENGTH_SHORT).show();
            isConet=false;
        }
    }

    public void Send(View v)
    {
        if (isConet)
        {
            String sendM=sendMessage.getText().toString()+"\r\n";
            writer.print(sendM);
            writer.flush();
            Toast.makeText(this,"Sended ",Toast.LENGTH_SHORT ).show();
        }
    }

    public void SendLight(String s)
    {
        if (isConet)
        {
            writer.print(s+"\r\n");
            writer.flush();
        }
    }
}

