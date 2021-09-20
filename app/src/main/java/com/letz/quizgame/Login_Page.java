package com.letz.quizgame;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_Page extends AppCompatActivity
{
    EditText email;
    EditText password;
    Button SignIn;
    SignInButton SigninGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBar_Login;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        SignIn = findViewById(R.id.buttonSignin);
        SigninGoogle = findViewById(R.id.buttonGoogleSignin);
        signUp = findViewById(R.id.textViewSignUp);

        progressBar_Login = findViewById(R.id.progressBar_LoginPage);
        forgotPassword = findViewById(R.id.textViewForgot);

        SignIn.setOnClickListener(v -> {
            String userMail = email.getText().toString();
            String userPassword = password.getText().toString();
            signInWithFirebase(userMail, userPassword);
            progressBar_Login.setVisibility(View.INVISIBLE);
        });

        SigninGoogle.setOnClickListener(v -> {
            signInGoogle();
        });
        signUp.setOnClickListener(v -> {
            Intent i = new Intent(Login_Page.this, Signup_Page.class);
            startActivity(i);
        });

        forgotPassword.setOnClickListener(v -> {
            Intent igo_Forgot_Password = new Intent(Login_Page.this, Forgot_Password.class);
            startActivity(igo_Forgot_Password);
        });
    }

    public void signInWithFirebase(String userMail, String userPassword) {
        progressBar_Login.setVisibility(View.VISIBLE);
        SignIn.setClickable(false);
        // create FirebaseAuth on top
        auth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent igoMain = new Intent(Login_Page.this, MainActivity.class);
                            startActivity(igoMain);
                            finish();
                            progressBar_Login.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login_Page.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Page.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent i = new Intent(Login_Page.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        progressBar_Login.setVisibility(View.INVISIBLE);
    }

    public void signInGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        signIn();
    }

    public void signIn() {
        Intent iSignIn = googleSignInClient.getSignInIntent();
        launchSignInGoogle.launch(iSignIn);

    }

    ActivityResultLauncher<Intent> launchSignInGoogle = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task
                                = GoogleSignIn.getSignedInAccountFromIntent(data);
                        firebaseSignInWithGoogle(task);
                    }
                }
            }
    );

    private void firebaseSignInWithGoogle(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(Login_Page.this, "Signed In successfully", Toast.LENGTH_SHORT).show();
            Intent igoMain = new Intent(Login_Page.this, MainActivity.class);
            startActivity(igoMain);
            finish();
            firebaseGoogleAccount(account);

        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(Login_Page.this, "Failed sing in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseGoogleAccount(GoogleSignInAccount account) {
        //login 된 기기 확인 가능
        AuthCredential authCredential
                = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        auth.signInWithCredential(authCredential).addOnCompleteListener(this
                , new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

                        } else {

                        }
                    }
                });
    }
}