package com.shaunlp.nbchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String GET_ROUTE = "http://192.168.1.133:5000/data";
        final String POST_ROUTE = "http://192.168.1.133:5000/meow";
        final String mUsername = "Username";

        final TextView mTextView = (TextView) findViewById(R.id.textView);
        Button mSendButton = (Button) findViewById(R.id.button);
        final EditText mMessageBox = (EditText) findViewById(R.id.editText);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROUTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("An error");

            }
        });

        queue.add(stringRequest);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest postRequest = new StringRequest(Request.Method.POST, POST_ROUTE,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("name", mUsername);
                        params.put("message", mMessageBox.getText().toString());

                        return params;
                    }
                };
                queue.add(postRequest);
                queue.add(stringRequest);
            }
        });
    }
}
