package com.example.pta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1500;


    //varriable
    Animation top,bottom;
    ImageView imageView;
    TextView textView;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        //Hooks
        imageView = findViewById(R.id.image1);
        textView = findViewById(R.id.text1);



        imageView.setAnimation(top);
        textView.setAnimation(bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                   /* String number = user.getPhoneNumber();
                    number = number.replaceAll("[^a-zA-Z0-9]", "");
                    checkUser(number);*/

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
                    intent.putExtra("status","new");
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_SCREEN);

    }
    public void checkUser(final String number) {
        String url = getString(R.string.BASS_URL) + "testUser";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {

                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        AuthUI.getInstance()
                                .signOut(SplashScreenActivity.this)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(SplashScreenActivity.this, "You Register First", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
                                    }
                                });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //netAlert();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //netAlert();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("number", number);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(SplashScreenActivity.this);
        queue.add(stringRequest);
    }

}
