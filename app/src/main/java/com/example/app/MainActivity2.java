package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_PDF = 1;
    private Uri pdfUri;
    private TextView fromDateInput;
    private TextView toDateInput;
    private TextView emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fromDateInput = findViewById(R.id.from_date_input);
        toDateInput = findViewById(R.id.to_date_input);
        emailInput = findViewById(R.id.email_input);

        // Set the from date input to today's date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        fromDateInput.setText(currentDate);

        // Set the to date input to today's date plus two months
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        Date toDate = calendar.getTime();
        String toDateText = sdf.format(toDate);
        toDateInput.setText(toDateText);

        Button pdfInput = findViewById(R.id.pdf);
        pdfInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, REQUEST_PDF);
            }
        });

        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri != null && validateDates() && validateEmail()) {
                    adjustDatesAndSendEmail();
                } else {
                    Toast.makeText(MainActivity2.this, "Veuillez sélectionner un fichier PDF, vérifier les dates et saisir une adresse e-mail valide.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PDF && resultCode == RESULT_OK) {
            if (data != null) {
                pdfUri = data.getData();

                if (pdfUri != null) {
                    Toast.makeText(MainActivity2.this, "PDF sélectionné.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Sélection de PDF annulée.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean validateDates() {
        String fromDate = fromDateInput.getText().toString();
        String toDate = toDateInput.getText().toString();
        return fromDate.compareTo(toDate) < 0;
    }

    private boolean validateEmail() {
        String email = emailInput.getText().toString().trim();
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void adjustDatesAndSendEmail() {
        String fromDateText = fromDateInput.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fromDate;
        try {
            fromDate = sdf.parse(fromDateText);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity2.this, "Erreur lors de la conversion de la date.", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.MONTH, 2);
        Date toDate = calendar.getTime();

        String toDateText = sdf.format(toDate);
        toDateInput.setText(toDateText);

        sendEmail();
    }

    private void sendEmail() {
        String email = "ghouatmounib@gmail.com"; // Nouvelle adresse e-mail destinataire
        String subject = "Envoi de fichier PDF";

        String fromDate = fromDateInput.getText().toString();
        String toDate = toDateInput.getText().toString();

        String message = "Bonjour,\nVeuillez trouver ci-joint le fichier PDF ainsi que les dates from et to.\n\nFrom Date: " + fromDate + "\nTo Date: " + toDate;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);

        startActivity(Intent.createChooser(emailIntent, "Envoyer l'e-mail avec :"));

        Toast.makeText(MainActivity2.this, "Email envoyé.", Toast.LENGTH_SHORT).show();
    }
}
