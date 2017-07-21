package trucker.trucker;

import android.util.Log;

import org.json.JSONException;

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

        connection(ip, port);

//        JSONObject json = new JSONObject();
//        json.put("code", "01");
//        String id = json.toString();
//        System.out.println("Sending Data : " + id);
//        sendMessage(id);
    }

    public void sendMessage(String str) {
        try {
            charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = charset.encode(str);
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
        }
    }

    private void connection(String ip, int port) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress(ip, port));
            receive();

        } catch (IOException e) {
        }
    }

    private void receive() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();
                        String msg = charset.decode(byteBuffer).toString();
                        if(msg!=null && !msg.equals("")){
//                            System.out.println("Receive Data : " + msg);
                            Log.e("Response", msg);
                        }


                    } catch (IOException e) {
                        System.out.println(e);
                        close();
                        System.exit(0);
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
                System.out.println("Client Close Error" + e );
            }
        }
    }
}