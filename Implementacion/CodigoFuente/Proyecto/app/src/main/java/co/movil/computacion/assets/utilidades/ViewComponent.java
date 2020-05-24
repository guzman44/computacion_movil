package co.movil.computacion.assets.utilidades;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;
import co.movil.computacion.model.UserTokenViewModel;

public class ViewComponent extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Activity activity;
    public String activityString = "";
    public UserTokenViewModel userToken;
    public Bundle userBuble;

    public Bundle getUserBuble() {
        return userBuble;
    }

    public UserTokenViewModel getUserToken() {
        return userToken;
    }

    public void setUserToken(UserTokenViewModel userToken) {
        this.userToken = userToken;
    }


    public ViewComponent(Activity _activity){
        this.activity = _activity;
    }

    public ViewComponent(Activity _activity, String _activityString, UserTokenViewModel _userToken) {
        this.activity = _activity;
        this.activityString = _activityString;
    }

    public void progressBarProcess(int id, boolean loading) {
        ProgressBar bar = this.activity.findViewById(id);
        if(bar!= null){
            if (loading) {
                bar.setVisibility(View.VISIBLE);
            } else {
                bar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void setDatosLogin(){
        Bundle requestUser = activity.getIntent().getExtras();
        if(requestUser != null){
            userToken = (UserTokenViewModel) requestUser.getSerializable("USER");
                userBuble = new Bundle();
            userBuble.putSerializable("USER",userToken);
        }
    }
}
