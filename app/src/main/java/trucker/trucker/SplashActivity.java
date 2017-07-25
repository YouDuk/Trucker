package trucker.trucker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;

public class SplashActivity extends AppCompatActivity {
    private AVLoadingIndicatorView avi;
    static Handler handler;
    Intent intent;
    static Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

//        new SocketUtil();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Client("169.56.80.26", 5001);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();



//        startService(new Intent(SplashActivity.this, WebSocketService.class));


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Animation fade_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);

                            avi.startAnimation(fade_out);
                            avi.hide();
                        }
                    });

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                intent = new Intent(SplashActivity.this, MainActivity.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                });

            }
        }).start();

    }


}
