package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity5 extends AppCompatActivity {
    private EditText usernomForgetEditText;
    private EditText nomForgetEditText;
    private Spinner specialiteSpinner;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        usernomForgetEditText = findViewById(R.id.usernom_forget);
        nomForgetEditText = findViewById(R.id.nom_forget);
        specialiteSpinner = findViewById(R.id.specialite_spinner);
        sendButton = findViewById(R.id.login_btn);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernomForgetEditText.getText().toString();
                String password = nomForgetEditText.getText().toString();
                String specialite = specialiteSpinner.getSelectedItem().toString();

                if (username.isEmpty() || password.isEmpty() || specialite.equals("Select a Spécialité:")) {
                    // Afficher un message d'erreur avec un toast
                    Toast.makeText(MainActivity5.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                        // Effectuer la validation de la connexion si les champs ne sont pas vides
                        if (username.equals("ADMIN") && password.equals("1")) {
                            Toast.makeText(MainActivity5.this, "Successful login. Welcome!", Toast.LENGTH_SHORT).show();

                            // Naviguer vers MainActivity3
                            Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
                            startActivity(intent);
                        } else {
                            // Identifiants invalides, afficher un message d'erreur
                            Toast.makeText(MainActivity5.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });




        Button backButton = findViewById(R.id.back_b);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        }
        }