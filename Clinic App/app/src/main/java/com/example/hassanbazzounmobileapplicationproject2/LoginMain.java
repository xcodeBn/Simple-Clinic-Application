package com.example.hassanbazzounmobileapplicationproject2;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginMain extends AppCompatActivity {

    RelativeLayout relative_login_main;
    TextView loginBtn;
    ViewFlipper vf;
    private TextInputEditText username;
    private TextInputEditText password;

    sharedInfo sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        sharedInfo = new sharedInfo(getApplicationContext());

        //viewFlipper
        vf = (ViewFlipper) findViewById(R.id.vf);

        //Username and password input
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);

        //login
        loginBtn = (TextView) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setText("");
                vf.setDisplayedChild(0);
                vf.setVisibility(View.VISIBLE);

                try {


                    Login();


                } catch (JSONException e) {
                    System.out.println("LoginError" + e.getMessage());
                }


            }
        });



        //Keep user login
        if (sharedInfo.getUser().length() > 0) {


            if (sharedInfo.getUserType().equals("manager")) {

                Intent intent = new Intent(LoginMain.this, Doctor_Main.class);
                startActivity(intent);
            } else {

                Intent intent = new Intent(LoginMain.this, patientMainActivity.class);
                startActivity(intent);
            }


        }

        //Closing Keyobard
        relative_login_main = (RelativeLayout) findViewById(R.id.relative_login_main);
        relative_login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                closeKeyboard();

            }
        });

    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);


        }

    }

    @Override
    public void onBackPressed() {

    }

    public void Login() throws JSONException {


        final String url = getResources().getString(R.string.api)+"login.php?username=" + username.getText().toString() + "&password=" + password.getText().toString();
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    vf.setVisibility(View.GONE);
                    loginBtn.setText(getResources().getString(R.string.LoginBtn));
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response " + response);


                    String error = jsonObject.getString("error");
                    String type = jsonObject.getString("type");


                    if (error.equals("true")) {

                        View parentLayout = findViewById(android.R.id.content);
                        Snackbar snackbar = Snackbar.make(parentLayout, getResources().getString(R.string.Error_login), Snackbar.LENGTH_LONG)
                                .setAction("try again", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        username.setText("");
                                        password.setText("");

                                    }
                                })
                                .setActionTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        View sbView = snackbar.getView();
                        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.error_red));
                        snackbar.show();

                    } else {




                        if (type.equals("manager")) {
                            sharedInfo.setUser(username.getText().toString());
                            sharedInfo.setUserType("manager");
                            Intent intent = new Intent(LoginMain.this, Doctor_Main.class);
                            startActivity(intent);

                        } else {
                            sharedInfo.setUser(username.getText().toString());
                            sharedInfo.setUserType("patient");
                            Intent intent = new Intent(LoginMain.this, patientMainActivity.class);
                            startActivity(intent);
                        }


                    }


                } catch (JSONException e) {
                    System.out.println("JSONException " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                vf.setVisibility(View.GONE);
               loginBtn.setText(R.string.LoginBtn);

                Toast.makeText(LoginMain.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        });


        queue.add(stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));

    }


}
