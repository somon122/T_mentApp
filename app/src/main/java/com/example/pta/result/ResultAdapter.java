package com.example.pta.result;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;
    private List<ResultClass>resultClassList;
    private ResultClass resultClass;
    SaveUserInfo saveUserInfo;

    private int showCount;

    public ResultAdapter(Context context, List<ResultClass> resultClassList) {
        this.context = context;
        this.resultClassList = resultClassList;
    }

    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.result_view_model,parent,false);

        saveUserInfo = new SaveUserInfo(context);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ResultAdapter.ViewHolder holder, final int position) {

        resultClass = resultClassList.get(position);

        holder.name.setText(resultClass.getName());
        holder.date.setText(resultClass.getDate_time());
        holder.totalPrize.setText(resultClass.getTotalPrize());
        holder.perKill.setText(resultClass.getPerKill());
        holder.entryFee.setText(resultClass.getEntryFee());
        holder.type.setText(resultClass.getType());
        holder.version.setText(resultClass.getVersion());
        holder.map.setText(resultClass.getMap());
        holder.winnerPrize.setText("winner -"+resultClass.getWinnerPrice()+" Taka");
        holder.runnerUp1.setText("RunnerUpPrize1-"+resultClass.getRunnerUpPrize1()+" Taka");
        holder.runnerUp2.setText("RunnerUpPrize2-"+resultClass.getRunnerUpPrize2()+" Taka");
        holder.showDataChart.setVisibility(View.GONE);

        getTotalJoint(resultClass.getMatchId(),saveUserInfo.getId(),holder);

        holder.watchMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Wait for show", Toast.LENGTH_SHORT).show();

            }
        });

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

                resultClass = resultClassList.get(position);

                Intent intent = new Intent(context,ResultDetailsActivity.class);
                intent.putExtra("name",resultClass.getName());
                intent.putExtra("matchId",resultClass.getMatchId());
                intent.putExtra("category",resultClass.getCategory());
                intent.putExtra("date_time",resultClass.getDate_time());
                intent.putExtra("totalPrize",resultClass.getTotalPrize());
                intent.putExtra("perKill",resultClass.getPerKill());
                intent.putExtra("entryFee",resultClass.getEntryFee());
                intent.putExtra("type",resultClass.getType());
                intent.putExtra("version",resultClass.getVersion());
                intent.putExtra("map",resultClass.getMap());
                intent.putExtra("winnerPrice",resultClass.getWinnerPrice());
                intent.putExtra("runnerUpPrize1",resultClass.getRunnerUpPrize1());
                intent.putExtra("runnerUpPrize2",resultClass.getRunnerUpPrize2());
                context.startActivity(intent);


            }
        });

    }


    public void getTotalJoint(final String matchId, final String userId, final ResultAdapter.ViewHolder holder) {

        String url = context.getString(R.string.BASS_URL) + "checkAlreadyJoint";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        holder.jointOrNot.setText("JOINTED");

                    } else {
                        holder.jointOrNot.setText("NOT JOIN");

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
        return resultClassList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,date,totalPrize,perKill, entryFee,type
                ,version,map,winnerPrize, runnerUp1,runnerUp2;
        LinearLayout showDataChart, chartShower;
        Button watchMatch, jointOrNot;


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

            showDataChart= itemView.findViewById(R.id.resultPrizeChart);
            chartShower= itemView.findViewById(R.id.resultPriceDetailsShow);
            watchMatch= itemView.findViewById(R.id.resultModelWatchMatch);
            jointOrNot= itemView.findViewById(R.id.resultModelUserJointOrNot);



        }
    }
}
