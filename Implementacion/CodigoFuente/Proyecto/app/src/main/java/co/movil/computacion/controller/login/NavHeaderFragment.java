package co.movil.computacion.controller.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.movil.computacion.R;
import co.movil.computacion.controller.Map;

public class NavHeaderFragment extends Fragment {

    private final String SHARED_PREFS = "sharedPreferences";

    ImageView ivProfileMenu;
    TextView tvUsernameMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_bar, container, false);
        SetControls(view);
        return view;
    }

    private void SetControls(View view){
        ivProfileMenu = view.findViewById(R.id.ivProfileMenu);
        tvUsernameMenu = view.findViewById(R.id.tvUsernameMenu);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE );
        tvUsernameMenu.setText( sharedPreferences.getString( "username", "undefined" ) );
        Log.i("username: ", tvUsernameMenu.getText().toString());
    }
}
