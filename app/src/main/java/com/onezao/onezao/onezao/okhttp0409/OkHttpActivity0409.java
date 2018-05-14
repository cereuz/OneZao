package com.onezao.onezao.onezao.okhttp0409;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onezao.onezao.onezao.R;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zero on 2018/4/9 0009.
 */

public class OkHttpActivity0409 extends Activity  implements View.OnClickListener{

    TextView tv_code_0409;
    TextView tv_message_0409;
    TextView tv_body_0409;
    EditText et_okhttp_0409;

    String et_url_0409 = "http://59.110.166.176:8080/mitanapi/userinfo/walletinfo";
    String et_url_0514 = "http://59.110.166.176:8080/mitanapi/userinfo/recharge";

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //通过Bundle发送
              Bundle bundle = msg.getData();
            int responseCode = bundle.getInt("responseCode");
            String responseMessage = bundle.getString("responseMessage");
            String responseBody = bundle.getString("responseBody");
            tv_code_0409.setText(String.valueOf(responseCode));
            tv_message_0409.setText(responseMessage);
            tv_body_0409.setText(responseBody);

            //选择发送消息
/*           switch (msg.what){
                case 100 :
                    String responseCode =(String)msg.obj;
//                    Toast.makeText(OkHttpActivity0409.this, responseCode, Toast.LENGTH_SHORT).show();
                    tv_code_0409.setText(responseCode);
                case 101 :
                    String responseMessage =(String)msg.obj;
//                    Toast.makeText(OkHttpActivity0409.this, responseMessage, Toast.LENGTH_SHORT).show();
                    tv_body_0409.setText(responseMessage);
                case 102 :
                    String responseBody =(String)msg.obj;
//                    Toast.makeText(OkHttpActivity0409.this, responseBody, Toast.LENGTH_SHORT).show();
                    tv_message_0409.setText(responseBody);
            }*/
        };
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_0409);

        Button btn_okhttp_0409 = (Button) findViewById(R.id.btn_okhttp_0409);
        btn_okhttp_0409.setOnClickListener(this);
        Button btn_okhttp_0514 = (Button) findViewById(R.id.btn_okhttp_0514);
        btn_okhttp_0514.setOnClickListener(this);

        tv_code_0409 = (TextView) findViewById(R.id.tv_code_0409);
        tv_message_0409 = (TextView) findViewById(R.id.tv_message_0409);
        tv_body_0409 = (TextView) findViewById(R.id.tv_body_0409);


        et_okhttp_0409 = (EditText) findViewById(R.id.et_okhttp_0409);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_okhttp_0409 :
//                Toast.makeText(this,"okhttp0409----https://www.baidu.com",Toast.LENGTH_SHORT).show();
                et_okhttp_0409.setText(et_url_0409);
                getDatasync(et_url_0409);
                break;
            case R.id.btn_okhttp_0514 :
                et_okhttp_0409.setText(et_url_0514);
                rechargeMT(et_url_0514);
                break;

        }
    }

    //充值
    public void rechargeMT(final String et_url){
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //POST传递请求参数
                        FormBody.Builder params=new FormBody.Builder();
                        params.add("app_flag", "mt");
                        params.add("user_uid", "mt57555cb118b3968f4021221ec1595b40");
                        params.add("user_id", "1082");
                        params.add("money", "100");
                        params.add("type", "2");
                        params.add("state", "1");
                        params.add("version", "1.0.1");
                        params.add("device", "honor");

                        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                        Request request = new Request.Builder()
                                .url(et_url)//请求接口。如果需要传参拼接到接口后面。
                                .post(params.build())
                                .build();//创建Request 对象
                        Response response = null;
                        response = client.newCall(request).execute();//得到Response 对象
                        if (response.isSuccessful()) {
                            //直接obtain
                            Message msg = Message.obtain();
                            msg.what = 100;

/*                        msg.obj = "response.code()=="+response.code();
//                        handler.sendMessage(msg);//发送消息

                        msg.what = 101;
                        msg.obj = "response.message()=="+response.message();
//                        handler.sendMessage(msg);//发送消息

                        msg.what = 102;
                        msg.obj = "res=="+response.body().string();
                        handler.sendMessage(msg);//发送消息*/

                            //传递一个数据量较大的数据
                            Bundle bundle = new Bundle();
                            bundle.putInt("responseCode", response.code());
                            bundle.putString("responseMessage", response.message());
                            bundle.putString("responseBody", response.body().string());
                            msg.setData(bundle);
                            handler.sendMessage(msg);//发送消息

                            Log.d("kwwl","response.code()=="+response.code());
                            Log.d("kwwl","response.message()=="+response.message());
                            Log.d("kwwl","resBody=="+response.body().string());
                            //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    //获取账户余额信息
    public void getDatasync(final String et_url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //POST传递请求参数
                    FormBody.Builder params=new FormBody.Builder();
                    params.add("app_flag", "mt");
                    params.add("user_id", "1082");
                    params.add("user_uid", "mt57555cb118b3968f4021221ec1595b40");
                    params.add("version", "1.0.1");
                    params.add("device", "honor");

                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url(et_url)//请求接口。如果需要传参拼接到接口后面。
                            .post(params.build())
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        //直接obtain
                        Message msg = Message.obtain();
                        msg.what = 100;

/*                        msg.obj = "response.code()=="+response.code();
//                        handler.sendMessage(msg);//发送消息

                        msg.what = 101;
                        msg.obj = "response.message()=="+response.message();
//                        handler.sendMessage(msg);//发送消息

                        msg.what = 102;
                        msg.obj = "res=="+response.body().string();
                        handler.sendMessage(msg);//发送消息*/

                        //传递一个数据量较大的数据
                        Bundle bundle = new Bundle();
                        bundle.putInt("responseCode", response.code());
                        bundle.putString("responseMessage", response.message());
                        bundle.putString("responseBody", response.body().string());
                        msg.setData(bundle);
                        handler.sendMessage(msg);//发送消息

                        Log.d("kwwl","response.code()=="+response.code());
                        Log.d("kwwl","response.message()=="+response.message());
                        Log.d("kwwl","resBody=="+response.body().string());
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
