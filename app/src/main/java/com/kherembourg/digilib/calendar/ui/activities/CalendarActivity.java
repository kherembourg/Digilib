package com.kherembourg.digilib.calendar.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kherembourg.digilib.R;
import com.kherembourg.digilib.calendar.ui.fragments.CalendarFragment;
import com.kherembourg.digilib.trakt.account.activities.LoginActivity;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationRepository;
import com.kherembourg.digilib.transversal.dagger.DaggerHelper;

import javax.inject.Inject;

public class CalendarActivity extends AppCompatActivity {

    @Inject
    AuthenticationRepository mAuthenticationRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerHelper.component().inject(this);

        if(mAuthenticationRepository.getToken() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, CalendarFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mAuthenticationRepository.removeToken();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
