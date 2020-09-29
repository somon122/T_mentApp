package com.example.pta.match;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.example.pta.R;
import com.example.pta.result.ResultDetailsActivity;
import com.example.pta.result.ResultDetailsClass;
import com.example.pta.result.ResultsDetailsAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MatchDetailsActivity extends AppCompatActivity {

    String matchName, matchId,category,date_time,totalPrize,perKill,entryFee,
            type,version,map,winnerPrice, runnerUpPrize1,runnerUpPrize2, jointStatus;

    TextView instruction1,instruction2,instruction3,instruction4,instruction5,instruction6,instruction7,instruction8;

    RecyclerView recyclerView;
    List<ParticipateClass>participatedList;
    Button jointButton, participateShowButton;
    TextView matchNameTV,dateTV,totalPrizeTV,perKillTV,entryFeeTV,  typeTV, versionTV, mapTV;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        instruction3 = findViewById(R.id.instruction3);
        instruction8 = findViewById(R.id.instruction8);

        instruction1 = findViewById(R.id.instruction1);
        instruction2 = findViewById(R.id.instruction2);
        instruction4 = findViewById(R.id.instruction4);
        instruction5 = findViewById(R.id.instruction5);
        instruction6 = findViewById(R.id.instruction6);
        instruction7 = findViewById(R.id.instruction7);

        imageView = findViewById(R.id.matchDetailsImage);
        matchNameTV = findViewById(R.id.matchDetailsMatchName);
        dateTV = findViewById(R.id.matchDetailsDate);
        totalPrizeTV = findViewById(R.id.matchDetailsTotalPrize);
        perKillTV = findViewById(R.id.matchDetailsPerKill);
        entryFeeTV = findViewById(R.id.matchDetailsEntryFee);

        typeTV = findViewById(R.id.matchDetailsType);
        versionTV = findViewById(R.id.matchDetailsVersion);
        mapTV = findViewById(R.id.matchDetailsMap);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            matchName = bundle.getString("name");
            matchId = bundle.getString("matchId");
            jointStatus = bundle.getString("jointStatus");
            category = bundle.getString("category");
            date_time = bundle.getString("date_time");
            totalPrize = bundle.getString("totalPrize");
            perKill = bundle.getString("perKill");
            entryFee = bundle.getString("entryFee");
            type = bundle.getString("type");
            version = bundle.getString("version");
            map = bundle.getString("map");
            winnerPrice = bundle.getString("winnerPrice");
            runnerUpPrize1 = bundle.getString("runnerUpPrize1");
            runnerUpPrize2 = bundle.getString("runnerUpPrize2");

            matchNameTV.setText(matchName);
            dateTV.setText("Match Schedule on "+date_time);
            totalPrizeTV.setText(totalPrize+"TK");
            perKillTV.setText(perKill+"TK");
            entryFeeTV.setText(entryFee+"TK");

            typeTV.setText(type);
            mapTV.setText(map);
            versionTV.setText(version);
            imageShow(category);
           getRoomIdData(matchId);

        }

        if (category.equals("Ludo")){

            instruction1.setText(getString(R.string.ludo_text));
            instruction2.setVisibility(View.GONE);
            instruction6.setVisibility(View.GONE);
            instruction7.setVisibility(View.GONE);


        }else if (category.equals("PUBG Lite")){

            instruction2.setText(getString(R.string.text2_pubg_lite));

        }else if (category.equals("PUBG")){

            instruction2.setText(getString(R.string.text2_pubg));

        }



        participatedList = new ArrayList<>();
        recyclerView = findViewById(R.id.participatedRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();

        recyclerView.setVisibility(View.GONE);

        jointButton = findViewById(R.id.matchDetailsJointNow);
        participateShowButton = findViewById(R.id.participatedListShowBtn);

        jointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (jointStatus.equals("JOINED")){

                    Toast.makeText(MatchDetailsActivity.this, "You are already Joined", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(getApplicationContext(),JointInMatchActivity.class);
                    intent.putExtra("matchId",matchId);
                    intent.putExtra("matchName",matchName);
                    intent.putExtra("type",type);
                    intent.putExtra("spotsLeft","");
                    intent.putExtra("entryFee",entryFee);
                    intent.putExtra("category",category);
                    intent.putExtra("date_time",date_time);
                    startActivity(intent);


                }

            }
        });

        participateShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.VISIBLE);
                getParticipatedData(matchId);

            }
        });


        SpannableString ss2 = new SpannableString(getString(R.string.text3));
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(MatchDetailsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };
        ss2.setSpan(clickableSpan2,143,153, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        instruction3.setText(ss2);
        instruction3.setMovementMethod(LinkMovementMethod.getInstance());

/// 2nd system clickable text=========

        SpannableString ss8 = new SpannableString(getString(R.string.text8));
        ClickableSpan clickableSpan8 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(MatchDetailsActivity.this, "Hello2", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };
        ss8.setSpan(clickableSpan8,77,87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        instruction8.setText(ss8);
        instruction8.setMovementMethod(LinkMovementMethod.getInstance());



    }

    private void imageShow(String category) {

        if (category.equals("FreeFire")){

            Picasso.get().load("https://i.ytimg.com/vi/pKUu6PKNyzk/maxresdefault.jpg").fit().into(imageView);

        }else if (category.equals("Ludo")){
            Picasso.get().load("https://i.ytimg.com/vi/KnLo8mT29ng/maxresdefault.jpg").fit().into(imageView);

        }else if (category.equals("PUBG")){
            Picasso.get().load("https://resize.indiatvnews.com/en/resize/newbucket/715_-/2020/03/pubg-mobile-1-1583916680.jpg").fit().into(imageView);

        }else if (category.equals("PUBG Lite")){
            Picasso.get().load("https://images.firstpost.com/fpimages/1200x800/fixed/jpg/2019/01/PUBG-Lite-copy.jpg").fit().into(imageView);

        }else {


        }


    }

    public void getParticipatedData(final String matchId) {
        String url = getString(R.string.BASS_URL) + "getUserTotalJoint";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataobj = jsonArray.getJSONObject(i);
                            int serialNo = i+1;
                            String name = dataobj.getString("playerName");
                            ParticipateClass participateClass = new ParticipateClass(serialNo,name);
                            participatedList.add(participateClass);
                        }

                        ParticipateAdapter participateAdapter = new ParticipateAdapter(MatchDetailsActivity.this,participatedList);
                        recyclerView.setAdapter(participateAdapter);
                        participateAdapter.notifyDataSetChanged();

                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("matchId", matchId);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(MatchDetailsActivity.this));
        queue.add(stringRequest);
    }


    public void getRoomIdData(final String matchId) {
        String url = getString(R.string.BASS_URL) + "getRoomId";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataobj = jsonArray.getJSONObject(i);

                            String status = dataobj.getString("idStatus");
                            String matchName = dataobj.getString("matchName");
                            String codeNo = dataobj.getString("codeNo");
                            String password = dataobj.getString("password");

                            if (status.equals("Open")){

                               idCopyAlert(matchName,codeNo,password);
                            }
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("matchId", matchId);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(MatchDetailsActivity.this));
        queue.add(stringRequest);
    }


    private void idCopyAlert(final String mName, final String code , final String pass ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MatchDetailsActivity.this);
        View view1 = LayoutInflater.from(MatchDetailsActivity.this).inflate(R.layout.room_id_model,null);

        final TextView matchNameTV = view1.findViewById(R.id.roomIdMatchName);
        final TextView codeIdTV = view1.findViewById(R.id.roomModelCodeId);
        final TextView passwordTV = view1.findViewById(R.id.roomModelPassword);
        final Button codeIdCopyBtn = view1.findViewById(R.id.codeIdCopyBtn);
        final Button passWordCopyBtn = view1.findViewById(R.id.passwordCopyBtn);

        matchNameTV.setText(mName);
        codeIdTV.setText(code);
        passwordTV.setText(pass);

        codeIdCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (code!= null){

                    ClipboardManager clipboard = (ClipboardManager)
                            getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text", code);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MatchDetailsActivity.this, "Copied!", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(MatchDetailsActivity.this, "Text Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

        passWordCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pass!= null){

                    ClipboardManager clipboard = (ClipboardManager)
                            getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text", pass);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MatchDetailsActivity.this, "Copied!", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(MatchDetailsActivity.this, "Text Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

        builder.setView(view1);
        AlertDialog dialog = builder.create();
        dialog.show();


    }


}