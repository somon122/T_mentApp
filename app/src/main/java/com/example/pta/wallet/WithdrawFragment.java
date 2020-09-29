package com.example.pta.wallet;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pta.R;
import com.example.pta.SaveUserInfo;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WithdrawFragment extends Fragment {

    TextInputLayout number,amount;
    Button submitBtn;
    RadioGroup radioGroup;
    View root;
    RadioButton method;
    String value;
    SaveUserInfo saveUserInfo;
    UserWalletInfo userWalletInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         root =  inflater.inflate(R.layout.fragment_withdraw, container, false);

        number = root.findViewById(R.id.withdrawSubmitNumber);
        amount = root.findViewById(R.id.withdrawSubmitAmount);
        submitBtn = root.findViewById(R.id.withdrawSubmitBtn);
         radioGroup = root.findViewById(R.id.selectRadioGroup);

         saveUserInfo= new SaveUserInfo(getContext());
         userWalletInfo= new UserWalletInfo(getContext());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                method = root.findViewById(checkedId);
                value =method.getText().toString();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (value.equals("")){
                    Toast.makeText(getContext(), "Please Select any one from Bkash, Rocket, or Nagad", Toast.LENGTH_SHORT).show();
                }else {
                    submitInfo();
                }
            }
        });

        return root;
    }

    private void submitInfo() {

        String num = number.getEditText().getText().toString().trim();
        String tk = amount.getEditText().getText().toString().trim();

        if (num.isEmpty()){

            number.getEditText().setError("Please enter Number");

        }else if (tk.isEmpty()){
            amount.getEditText().setError("Please enter Amount");

        }else {

            int mainBalance = Integer.parseInt(userWalletInfo.getBalance());
            int userAmount = Integer.parseInt(tk);
            int lastBalance = mainBalance-userAmount;

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss");
            String currentDateAndTime = sdf.format(new Date());

            if (mainBalance >= 100){

                if (userAmount >= 100){

                    insertWithdraw(saveUserInfo.getId(),saveUserInfo.getUserName(),tk,num, String.valueOf(lastBalance),value,"Pending", currentDateAndTime);

                }else {

                    Toast.makeText(getContext(), "minimum withdraw 100TK", Toast.LENGTH_SHORT).show();

                }

            }else {
                Toast.makeText(getContext(), "You have not enough balance", Toast.LENGTH_SHORT).show();
            }


        }


    }

    private void insertWithdraw(final String id, final String userName,final String amount,final String number,final String lastWinAmount,
                                final String method, final String pending , final String date_time) {


        String url = getString(R.string.BASS_URL) + "insertWithdraw";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Field", Toast.LENGTH_SHORT).show();
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
                Params.put("userId", id);
                Params.put("userName", userName);
                Params.put("number", number);
                Params.put("lastWinAmount", lastWinAmount);
                Params.put("amount", amount);
                Params.put("method", method);
                Params.put("status", pending);
                Params.put("date_time", date_time);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }




}