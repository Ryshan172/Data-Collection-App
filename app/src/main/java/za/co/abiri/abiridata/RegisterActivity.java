package za.co.abiri.abiridata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    //declarations
    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImageUri;

    //Private
    private EditText userEmail,userPassword,userPassword2,userName;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private Button returnLoginBtn;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //finding items from xml file
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        userName = findViewById(R.id.regName);
        loadingProgress = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.regBtn);
        //progressbar visibility
        loadingProgress.setVisibility(View.INVISIBLE);

        //Firebase Auth Code
        mAuth = FirebaseAuth.getInstance();

        //Registration Button code
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                //Entered values converted to string
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                //value conditions
                if(email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)) {

                    //Display error message
                    showMessage("Please complete all fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);

                }
                else
                {
                    //Case where all fields are completed and there are no issues
                    //Create User Account method only works if email is valid
                    CreateUserAccount(email,name,password);

                }

            }
        });

        //Return to login page
        returnLoginBtn = findViewById(R.id.returnloginBtn);
        returnLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });


        //User Profile Photo code
        ImgUserPhoto = findViewById(R.id.regUserPhoto);


    }

    //CREATED METHODS
    private void CreateUserAccount(String email, String name, String password) {
        //Create user account for email and password
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //User account successfully created
                            showMessage("Registration complete");

                            //Updated profile picture and name after creating account
                            //Created Method at bottom of code
                            //updateUserInfo( name ,pickedImageUri,mAuth.getCurrentUser());

                            //updateUserInfo( name ,pickedImageUri,mAuth.getCurrentUser());

                            updateUserInfo( name , mAuth.getCurrentUser());

                            //New change
                            //updateUI();


                        }
                        else
                        {
                            showMessage("Account creation failed" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);

                        }
                    }
                });

    }

    //Update User Info method - update Name and Photo
    private void updateUserInfo(String name, FirebaseUser currentUser) {

        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        //Updating currentUser profile
        currentUser.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            //Successfully updated user info
                            //showMessage("Registration complete");
                            //Method
                            updateUI();
                        }
                    }
                });




    }

    //updateUI created Method
    private void updateUI() {
        //Moves to HomeActivity
        //Toast
        Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeActivity);
        finish();

    }

    //Simple Error Toast Message Method
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }


    //Override method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            //Image from gallery successfully picked by user
            //Reference needs to be saved to a Uri Variable
            pickedImageUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImageUri);
        }
    }





}

