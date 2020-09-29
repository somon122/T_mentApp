package com.example.pta.match;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pta.R;
import com.example.pta.SaveUserInfo;
import com.example.pta.result.ResultAdapter;
import com.example.pta.result.ResultDetailsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private Context context;
    private List<MatchClass>matchClassList;
    private MatchClass matchClass;
    private int showCount;
    private SaveUserInfo saveUserInfo;

    int left;

    public MatchAdapter(Context context, List<MatchClass> matchClassList) {
        this.context = context;
        this.matchClassList = matchClassList;
    }
    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_view_model,parent,false);
        saveUserInfo = new SaveUserInfo(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MatchAdapter.ViewHolder holder, final int position) {


        matchClass = matchClassList.get(position);

        holder.name.setText(matchClass.getName());
        holder.date.setText(matchClass.getDate_time());
        holder.totalPrize.setText(matchClass.getTotalPrize());
        holder.perKill.setText(matchClass.getPerKill());
        holder.entryFee.setText(matchClass.getEntryFee());
        holder.type.setText(matchClass.getType());
        holder.version.setText(matchClass.getVersion());
        holder.map.setText(matchClass.getMap());
        holder.winnerPrize.setText("winner -"+matchClass.getWinnerPrice()+" Taka");
        holder.runnerUp1.setText("RunnerUpPrize1-"+matchClass.getRunnerUpPrize1()+" Taka");
        holder.runnerUp2.setText("RunnerUpPrize2-"+matchClass.getRunnerUpPrize2()+" Taka");
        holder.showDataChart.setVisibility(View.GONE);
        checkJointOrNot(matchClass.getId(),saveUserInfo.getId(),holder);
        getTotalJoint(matchClass.getId(), holder, matchClass.getCandidateNo());


        holder.chartShower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showCount>0){
                    holder.showDataChart.setVisibility(View.GONE);
                    showCount=0;
                }else {
                    holder.showDataChart.setVisibility(View.VISIBLE);
                    showCount= showCount+1;
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                matchClass = matchClassList.get(position);

                Intent intent = new Intent(context, MatchDetailsActivity.class);
                intent.putExtra("name",matchClass.getName());
                intent.putExtra("jointStatus",holder.jointOrNot.getText().toString());
                intent.putExtra("matchId",matchClass.getId());
                intent.putExtra("category",matchClass.getCategory());
                intent.putExtra("date_time",matchClass.getDate_time());
                intent.putExtra("totalPrize",matchClass.getTotalPrize());
                intent.putExtra("perKill",matchClass.getPerKill());
                intent.putExtra("entryFee",matchClass.getEntryFee());
                intent.putExtra("type",matchClass.getType());
                intent.putExtra("version",matchClass.getVersion());
                intent.putExtra("map",matchClass.getMap());
                intent.putExtra("winnerPrice",matchClass.getWinnerPrice());
                intent.putExtra("runnerUpPrize1",matchClass.getRunnerUpPrize1());
                intent.putExtra("runnerUpPrize2",matchClass.getRunnerUpPrize2());
                context.startActivity(intent);



            }
        });

        holder.jointOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.jointOrNot.getText().toString().equals("JOIN NOW")){

                    matchClass = matchClassList.get(position);
                    Intent intent = new Intent(context, JointInMatchActivity.class);
                    intent.putExtra("matchId",matchClass.getId());
                    intent.putExtra("matchName",matchClass.getName());
                    intent.putExtra("type",matchClass.getType());
                    intent.putExtra("spotsLeft",left);
                    intent.putExtra("entryFee",matchClass.getEntryFee());
                    intent.putExtra("category",matchClass.getCategory());
                    intent.putExtra("date_time",matchClass.getDate_time());
                    context.startActivity(intent);

                }else {
                    Toast.makeText(context, "You are already joined", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void checkJointOrNot(final String matchId, final String userId, final MatchAdapter.ViewHolder holder) {

        String url = context.getString(R.string.BASS_URL) + "checkAlreadyJoint";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        holder.jointOrNot.setText("JOINED");

                    } else {
                        holder.jointOrNot.setText("JOIN NOW");

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
                Params.put("userId", userId);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


    public void getTotalJoint(final String matchId, final MatchAdapter.ViewHolder holder,final String cNo) {

        String url = context.getString(R.string.BASS_URL) + "getUserTotalJoint";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        int num = jsonArray.length();

                        holder.progressBar.setMax(Integer.parseInt(cNo));
                        holder.progressBar.setProgress(num);
                        holder.joinUserCounter.setText(num+"/"+cNo);
                        int tUser= Integer.parseInt(cNo);
                        left = tUser-num;
                        holder.jointUserLeft.setText("only "+left+ "spots left");

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
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return matchClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView name,date,totalPrize,perKill, entryFee,type
                ,version,map,winnerPrize, runnerUp1,runnerUp2;

        TextView jointUserLeft, joinUserCounter;
        LinearLayout showDataChart, chartShower;
        Button jointOrNot;
        ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.resultMatchName);
            date= itemView.findViewById(R.id.resultMatchTime);
            totalPrize= itemView.findViewById(R.id.resultTotalPrize);
            perKill= itemView.findViewById(R.id.resultPerKill);
            entryFee= itemView.findViewById(R.id.resultEntryFee);
            type= itemView.findViewById(R.id.resultType);
            version= itemView.findViewById(R.id.resultVersion);
            map= itemView.findViewById(R.id.resultMap);
            winnerPrize= itemView.findViewById(R.id.resultWinnerPrize);
            runnerUp1= itemView.findViewById(R.id.resultRunnerUp1Prize);
            runnerUp2= itemView.findViewById(R.id.resultRunnerUp2Prize);

            joinUserCounter= itemView.findViewById(R.id.matchModelJoinUserCounter);
            jointUserLeft= itemView.findViewById(R.id.matchModelUserLeft);

            showDataChart= itemView.findViewById(R.id.resultPrizeChart);
            chartShower= itemView.findViewById(R.id.resultPriceDetailsShow);
            progressBar= itemView.findViewById(R.id.matchModelProgressBar);
            jointOrNot= itemView.findViewById(R.id.resultModelUserJointOrNot);


        }

    }
}
