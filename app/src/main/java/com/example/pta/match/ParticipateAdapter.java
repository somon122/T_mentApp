package com.example.pta.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pta.R;


import java.util.List;

public class ParticipateAdapter extends RecyclerView.Adapter<ParticipateAdapter.ViewHolder> {

    private Context context;
    private List<ParticipateClass>participateClassList;
    ParticipateClass participateClass;

    public ParticipateAdapter(Context context, List<ParticipateClass> participateClassList) {
        this.context = context;
        this.participateClassList = participateClassList;
    }

    @NonNull
    @Override
    public ParticipateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.participated_view_model,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipateAdapter.ViewHolder holder, int position) {

        participateClass = participateClassList.get(position);
        holder.serialNo.setText(""+participateClass.getSerialNo());
        holder.pName.setText(participateClass.getpName());

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
        return participateClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView serialNo, pName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serialNo = itemView.findViewById(R.id.participatedModelSerialNo);
            pName = itemView.findViewById(R.id.participatedModelPlayerName);

        }
    }
}
