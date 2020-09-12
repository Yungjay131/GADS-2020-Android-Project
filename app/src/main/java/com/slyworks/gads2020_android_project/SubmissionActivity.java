package com.slyworks.gads2020_android_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.slyworks.gads2020_android_project.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Joshua Sylvanus, 7:49AM, 09/09/2020.
 */
public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    //region Vars
    private static final String TAG = SubmissionActivity.class.getSimpleName();
     private EditText etFirstName, etLastName, etEmail, etGithubLink;
     private Button btnSubmit, btnConfirm;
    private ProgressBar progressBar;
    private ImageView ivBack, ivClose;
    //    dialog
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
     //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);


        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etGithubLink = findViewById(R.id.etGitHubProjectLink);
        btnSubmit = findViewById(R.id.btnSubmit);

        progressBar = findViewById(R.id.progressBar);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
    }

    private void submit() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String gitHubLink = etGithubLink.getText().toString();
        //TODO:could add Regex here to make sure its a valid github link
        //TODO:or do an actual query of the address(github project link)

        boolean firstNameBool = firstName.isEmpty();
        boolean lastNameBool = lastName.isEmpty();
        boolean emailBool = email.isEmpty();
        boolean gitHubLinkBool = gitHubLink.isEmpty();

        if(firstNameBool || lastNameBool || emailBool || gitHubLinkBool){
           Snackbar.make(findViewById(R.id.submissionActivity_layout),
                   "Please complete all fields!!", Snackbar.LENGTH_LONG)
                   .show();
           return;
        }

    //TODO:perform the actual submission
        Call<Void> call = ApiClient.submitProjectApi().submitProject(firstName, lastName, email, gitHubLink);

        progressBar.setVisibility(View.VISIBLE);

        Log.e(TAG, "onResponse: Input: \nFirst name: " + firstName + "\nLast name: " +
                           lastName + "\nEmail: " + email + "\nProject link: " + gitHubLink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                progressBar.setVisibility(View.GONE);

                Log.e(TAG, "onResponse: Code " + response.code());

                int code = response.code();

                if (!response.isSuccessful()) {
                    Log.e(TAG, "onFailure: Launching failure dialog");
                    Log.e(TAG, "onResponse: error: " + response.code());

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(SubmissionActivity.this, "An error occurred: "
                                                                    + response.code(), Toast.LENGTH_SHORT).show();

                    displayFailureDialog();
                    return;
                }

                Log.e(TAG, "onResponse: launching success dialog");
                Log.e(TAG, "onResponse: successful: " + response.code());
                Log.e(TAG, "onResponse: Input: \nFirst name: " + firstName + "\nLast name: " +
                                   lastName + "\nEmail: " + email + "\nProject link: " + gitHubLink);

                Toast.makeText(SubmissionActivity.this, "Success: "
                                                                + response.code(), Toast.LENGTH_SHORT).show();

                displaySuccessDialog();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d(TAG, "onFailure: Launching failure dialog");
                Log.d(TAG, "onFailure: An error occurred: " + throwable.getMessage());

                progressBar.setVisibility(View.GONE);

                Snackbar.make(findViewById(R.id.submissionActivity_layout), "", Snackbar.LENGTH_LONG).show();

                Toast.makeText(SubmissionActivity.this, "An error occurred: "
                                                                + throwable.getMessage(), Toast.LENGTH_SHORT).show();

                displayFailureDialog();
            }
        });
    }

    public void displayConfirmationDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.confirmation_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(view);

        ButtonListener buttonListener = new ButtonListener();
        btnConfirm = view.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(buttonListener);

        ivClose = view.findViewById(R.id.close);
        ivClose.setOnClickListener(buttonListener);

        alertDialog.show();
    }

    public void displaySuccessDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.success_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
    }

    public void displayFailureDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.failure_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                startActivity(new Intent(SubmissionActivity.this, MainActivity.class));
//            finish();
                //super.onBackPressed();
                break;

            case R.id.btnSubmit:
                Log.e(TAG, "onClick: Launching confirmation dialog");
                displayConfirmationDialog();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SubmissionActivity.this, MainActivity.class));
    }

    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.btn_confirm:
                    submit();
                    alertDialog.dismiss();
                    break;

                case R.id.close:
                    alertDialog.dismiss();
                    break;
            }
        }
    }



}
