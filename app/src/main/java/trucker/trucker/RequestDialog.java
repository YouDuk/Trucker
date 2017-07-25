package trucker.trucker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestDialog extends Dialog {
    Context con;
    Activity activity;
    TextView ok;
    TextView message;


    public RequestDialog(@NonNull Context context, Activity activity) {
        super(context);
        con = context;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_request);

        ok = (TextView) findViewById(R.id.ok);
        message = (TextView) findViewById(R.id.message);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JSONObject json = new JSONObject();
                try {
                    json.put("code", "02");
                    json.put("message", message.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SplashActivity.client.sendMessage(json.toString());
                    }
                }).start();

                dismiss();
            }
        });


    }
}
