package co.movil.computacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuUpFragment extends Fragment {

    ImageView ivProfile;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_header, container, false);

        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);

        ivProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Profile.class );
                //intent.putExtra("event", "Home");
                startActivity(intent);
            }
        });



        return view;
    }

}
