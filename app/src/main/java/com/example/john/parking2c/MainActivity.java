package com.example.john.parking2c;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;



import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    String result;
    EditText carNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncHttpClient client = new AsyncHttpClient();

                carNumber = findViewById(R.id.CarNumber);
                final String url = "http://parking.2click.money/ExtApi/carState?id="+carNumber.getText()+"&accToken=fdf909e4j3f03jikdsjfpsdg9sdfd0ifjsdik";

                client.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        assert result != null;
                        result = new String(responseBody);
                        checkPayment();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        assert result != null;
                        result = "Ошибка на сервере";

                    }
                });


            }
        });
    }
    private void checkPayment(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Номер авто: "+carNumber.getText());
        builder.setMessage(result);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
