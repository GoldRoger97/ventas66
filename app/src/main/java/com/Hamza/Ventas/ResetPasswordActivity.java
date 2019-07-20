package com.Hamza.Ventas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText enteremail;
    private Button sendEmail ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        enteremail=findViewById(R.id.Validemail);
        sendEmail=findViewById(R.id.resetbtn);
        mAuth=FirebaseAuth.getInstance();

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = enteremail.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(ResetPasswordActivity.this,"please enter your email ",Toast.LENGTH_SHORT).show();

                }

                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ResetPasswordActivity.this,"please check your email account",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,message,Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
}
