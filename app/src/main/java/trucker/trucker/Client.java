package trucker.trucker;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by seongwonlee on 2017. 7. 3..
 */

public class Client {
    private SocketChannel socketChannel;
    private Charset charset;

    public Client(String ip, int port) throws JSONException {

        this.socketChannel = null;
        charset = Charset.forName("UTF-8");
        Log.e("Error", "서버 접속 시도");
        connection(ip, port);

//        JSONObject json = new JSONObject();
//        json.put("code", "99");
//        String id = json.toString();
//        System.out.println("Sending Data : " + id);
//        sendMessage(id);
    }

    public void sendMessage(String str) {
        try {
            charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = charset.encode(str);
            socketChannel.write(byteBuffer);
            Log.e("Send", str);
        } catch (IOException e) {
        }
    }

    private void connection(String ip, int port) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress(ip, port));
            receive();
            Log.e("Error", "서버 접속");
        } catch (IOException e) {
            Log.e("Error", "서버 접속 불가");
        }
    }

    private void receive() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();
                        String msg = charset.decode(byteBuffer).toString();

                        if (isJSONValid(msg)) {
                            final JSONObject json = new JSONObject(msg);

                            System.out.println("Receive Data : " + msg);
                                Log.e("Response", msg);
                            if (TruckersFragment.isRunning && json.get("code").equals("01")) {
//                                Log.e("CODE 01", "LAT : " + Double.parseDouble(json.get("lat").toString()) + " LON : " + Double.parseDouble(json.get("lon").toString()));

                                TruckersFragment.handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            TruckersFragment.marker_a.setPosition(new LatLng(Double.parseDouble(json.get("lat").toString()), Double.parseDouble(json.get("lon").toString())));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
//                        }


                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                        close();
                        System.exit(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void close() {
        if (!socketChannel.isConnected()) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                System.out.println("Client Close Error" + e);
            }
        }
    }


    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}