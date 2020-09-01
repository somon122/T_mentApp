package com.example.pta.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pta.ProfileSetUpActivity;
import com.example.pta.R;
import com.example.pta.SignInActivity;
import com.example.pta.wallet.WalletActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class ProfileFragment extends Fragment {

    CardView logout,profileWallet,updateProfile, share,howToPlay,aboutUs;
    TextView userName, userEmail;
    ImageView userImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_profile, container, false);

        logout = root.findViewById(R.id.logout_id);
        profileWallet = root.findViewById(R.id.profileWallet);

        userName = root.findViewById(R.id.profileUserName);
        userEmail = root.findViewById(R.id.profileEmail);
        userImage = root.findViewById(R.id.profileUserImage);

        updateProfile = root.findViewById(R.id.profileUpdate);
        share = root.findViewById(R.id.profileShare);
        howToPlay = root.findViewById(R.id.profileHowToPlay);
        aboutUs = root.findViewById(R.id.profileAboutUs);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAlert();
            }
        });

        profileWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(getContext(), WalletActivity.class));
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileSetUpActivity.class));

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();

            }
        });
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();

            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    private void logoutAlert() {


        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getContext(), "Logout Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), SignInActivity.class));

                    }
                });




    }

}