package co.movil.computacion.controller.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.controller.Home;
import co.movil.computacion.controller.NewUser;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private final String SHARED_PREFS = "sharedPreferences";
    EditText usernameEditText;
    EditText passwordEditText;

    ViewComponent vc = new ViewComponent(this,"LOGIN",null);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        usernameEditText.setText("mguzman");
        passwordEditText.setText("123456");
        final TextView register = findViewById(R.id.registrar);
        final Button loginButton = findViewById(R.id.login);
        vc.progressBarProcess(R.id.loading,true);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });
        vc.progressBarProcess(R.id.loading,false);
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {

                RequestAuthentication authentication = new RequestAuthentication();
                authentication.setUsername(usernameEditText.getText().toString());
                authentication.setPassword(passwordEditText.getText().toString());

                IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
                Call<UserTokenViewModel> call = service.login("application/json",authentication);

                call.enqueue(new Callback<UserTokenViewModel>() {

                    @Override
                    public void onResponse(Call<UserTokenViewModel> call, Response<UserTokenViewModel> response) {
                        if(response.errorBody()!= null){
                            Toast.makeText(getApplicationContext(), "Usuario/clave Incorrectos...", Toast.LENGTH_LONG).show();
                        }else{
                            UserTokenViewModel result =  response.body();
                            final Bundle bundle = new Bundle();
                            bundle.putSerializable("USER",result);
                            bundle.putString("userName",usernameEditText.getText().toString());
                            bundle.putString("password",passwordEditText.getText().toString());

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    LoginActivity.this.startActivity(new Intent(LoginActivity.this,Home.class).putExtras(bundle));
                                    vc.progressBarProcess(R.id.loading,false);
                                    LoginActivity.this.finish();
                                }
                            },1000);
                        }
                        vc.progressBarProcess(R.id.loading,false);
                    }

                    @Override
                    public void onFailure(Call<UserTokenViewModel> call, Throwable t) {
                        Log.w("error proyecto AAAAAAA:" , t.getMessage().toString());
                        Toast.makeText(getApplicationContext(), "Usuario/clave Incorrectos", Toast.LENGTH_LONG).show();
                        vc.progressBarProcess(R.id.loading,false);
                    }
                });
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Log.i("username: ", usernameEditText.getText().toString() );
                vc.progressBarProcess(R.id.loading,true);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newUserIntent = new Intent( v.getContext(), NewUser.class);
                startActivity( newUserIntent );
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void saveData()
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString( "username", usernameEditText.getText().toString() );
        editor.commit();
    }
}
