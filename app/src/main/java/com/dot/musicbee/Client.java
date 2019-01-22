package com.dot.musicbee;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

public class Client implements Runnable{
    Activity activity;
    static ArrayList<String> arrayList=new ArrayList<>();
    public Client(Activity activity) {
        this.activity = activity;
    }
    static WebSocketClient webSocketClient;
    @Override
    public void run()  {
        try {
            gg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void gg() throws Exception{
        //URL url=new URL("ws:okmusicbot.herokuapp.com/ws/bot");
        URI uri=new URI("ws://okmusicbot.herokuapp.com/ws/bot");
        webSocketClient=new WebSocketClient(uri,new Draft_17()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println(handshakedata  +"  CONNECTED #############################################");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"CONNECTED",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onMessage(String message) {
                System.out.println(message +"  MESSAGE #############################################");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"Message Received",Toast.LENGTH_LONG).show();
                    }
                });
                try {
                    JSONObject jsonObject=new JSONObject(message);
                    JSONObject Jmessage=jsonObject.getJSONObject("message");
                    String url=Jmessage.getString("url");
                    arrayList.add(url);
                    if(arrayList.size()==2){
                        Intent intent=new Intent(activity,Songs.class);
                        activity.startActivity(intent);
                    }
                    System.out.println(url +"  URL#############################################");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println(reason +"  CLOSE#############################################"+code);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"CLOSED CONNECTION",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(activity,Songs.class);
                        activity.startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Exception ex) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"ERROR OCCURED",Toast.LENGTH_LONG).show();
                    }
                });
                System.out.println(ex+"  EXCEPTION #############################################");
            }
        };
        webSocketClient.connect();

    }
}
