package com.example.nikant20.volleydemo.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nikant20.volleydemo.R;
import com.example.nikant20.volleydemo.activity.model.User;
import com.example.nikant20.volleydemo.activity.model.Util;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.buttonLogin)
    Button buttonLogin;

    @BindView(R.id.textViewRegister)
    TextView textViewRegister;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    User user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);
        user = new User();

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonLogin:
                user.setEmail(editTextEmail.getText().toString().trim());
                user.setPassword(editTextPassword.getText().toString().trim());

                loginUser();
                break;
            case R.id.textViewRegister:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;

        }

    }

    void loginUser() {

        stringRequest = new StringRequest(Request.Method.POST, Util.LOGIN_ENDPOINT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");

                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                    if (success == 1) {


                        editor.putBoolean(Util.KEY_LOGREG, true);
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Some Exception", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, "Some Volley Error", Toast.LENGTH_LONG).show();
            }
        })

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("email", user.getEmail());
                map.put("password", user.getPassword());

                return map;

            }
        }
        ;

        requestQueue.add(stringRequest);
    }
}
