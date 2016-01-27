package com.kherembourg.digilib.trakt.account.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kherembourg.digilib.BuildConfig;
import com.kherembourg.digilib.R;
import com.kherembourg.digilib.calendar.ui.activities.CalendarActivity;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationRepository;
import com.kherembourg.digilib.trakt.model.Token;
import com.kherembourg.digilib.transversal.dagger.DaggerHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @Inject
    AuthenticationRepository authenticationRepository;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DaggerHelper.component().inject(this);
        ButterKnife.bind(this);

        Uri uri = this.getIntent().getData();
        if (uri != null) {
            String code = uri.getQueryParameter("code");
            Timber.d("Code retrieved from Trakt %s", code);
            subscription = authenticationRepository.login(code)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Token>() {
                        @Override
                        public void onCompleted() {
                            Timber.d("Success");
                            startActivity(new Intent(LoginActivity.this, CalendarActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e(e, "Error while trying to log user with trakt code");
                            Toast.makeText(LoginActivity.this, "Unable to log in", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(Token token) {
                            Timber.d("Token ok");
                        }
                    });
        }
    }

    @OnClick(R.id.button_login)
    public void onButtonLoginClicked() {
        StringBuilder url = new StringBuilder(BuildConfig.TRAKT_API_URL);
        url.append("oauth/authorize?");
        url.append("response_type=code");
        url.append("&client_id=");
        url.append(BuildConfig.TRAKT_CLIENT_ID);
        url.append("&redirect_uri=");
        url.append(BuildConfig.TRAKT_REDIRECT_URI);

        startActivity(new Intent("android.intent.action.VIEW",
                Uri.parse(url.toString())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
        super.onDestroy();
    }
}
