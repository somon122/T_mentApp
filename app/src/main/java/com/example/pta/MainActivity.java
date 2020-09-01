package com.example.pta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pta.fragments.ProfileFragment;
import com.example.pta.fragments.HomeFragment;
import com.example.pta.wallet.WalletActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HomeFragment homeFragment = new HomeFragment();
        fragmentSet(homeFragment);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.bottomHome_id);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.bottomHome_id:
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentSet(homeFragment);
                        break;
                    case R.id.bottomProfile_id:
                        ProfileFragment ProfileFragment = new ProfileFragment();
                        fragmentSet(ProfileFragment);
                        break;
                     case R.id.bottomResult_id:
                         Toast.makeText(MainActivity.this, "bottomResult_id", Toast.LENGTH_SHORT).show();
                         break;
                    case R.id.bottom_balance_wallet_id:
                          startActivity(new Intent(MainActivity.this, WalletActivity.class));
                        break;
                    case R.id.bottom_TopPlayer_id:
                        Toast.makeText(MainActivity.this, "bottom_TopPlayer_id", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }
            }
        });



    }

    private void fragmentSet(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.hostFragment,fragment)
                .commit();

    }
}