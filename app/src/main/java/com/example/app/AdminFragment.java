package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class AdminFragment extends Fragment {

    private ImageButton imageButton;
    private Bitmap selectedImage;
    private EditText nomInput, prenomInput, numerotelInput, mInput, mcInput, addInput, emailInput, autreInput;
    private Spinner specialiteSpinner;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin, container, false);

        // Recherche des vues par leur ID
        imageButton = rootView.findViewById(R.id.image_btn);
        nomInput = rootView.findViewById(R.id.nom_input);
        prenomInput = rootView.findViewById(R.id.prenom_input);
        numerotelInput = rootView.findViewById(R.id.numerotel_input);
        mInput = rootView.findViewById(R.id.m_input);
        mcInput = rootView.findViewById(R.id.mc_input);
        addInput = rootView.findViewById(R.id.add_input);
        emailInput = rootView.findViewById(R.id.email_input);
        autreInput = rootView.findViewById(R.id.autre_input);
        specialiteSpinner = rootView.findViewById(R.id.s_spinner);

        // Ajout d'un écouteur d'événements à l'ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sélectionner une image
                selectImage();
            }
        });

        // Ajout d'un écouteur d'événements au bouton d'ajout d'utilisateur
        Button addUserBtn = rootView.findViewById(R.id.login_btn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ajouter l'utilisateur après vérification de l'image
                addUser();
            }
        });

        return rootView;
    }

    private void selectImage() {
        // Ouvrir la galerie pour sélectionner une image
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1 && data != null) {
            // Image sélectionnée depuis la galerie
            Uri imageUri = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                setImage(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImage(Bitmap bitmap) {
        if (bitmap != null) {
            // Resize the bitmap to match the desired dimensions (136dp x 136dp)
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,
                    (int) getResources().getDimension(R.dimen.image_width),
                    (int) getResources().getDimension(R.dimen.image_height),
                    false);
            imageButton.setImageBitmap(resizedBitmap);
        }
    }

    private void addUser() {
        // Vérifier si une image a été sélectionnée
        if (selectedImage == null) {
            // Afficher un message d'erreur
            Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            return; // Sortir de la méthode addUser car l'image est manquante
        }

        // Vérifier si les autres champs sont vides
        if (nomInput.getText().toString().isEmpty() ||
                prenomInput.getText().toString().isEmpty() ||
                numerotelInput.getText().toString().isEmpty() ||
                mInput.getText().toString().isEmpty() ||
                mcInput.getText().toString().isEmpty() ||
                addInput.getText().toString().isEmpty() ||
                emailInput.getText().toString().isEmpty() ||
                autreInput.getText().toString().isEmpty()){
            // Afficher un message d'erreur si un champ est vide
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return; // Sortir de la méthode addUser car un champ est vide
        }
        if (specialiteSpinner.getSelectedItem().toString().equals("Select a Blood type:") || specialiteSpinner.getSelectedItemPosition() == 0) {
            // Afficher un message d'erreur si aucune spécialité n'est sélectionnée
            Toast.makeText(getContext(), "Please select a valid Blood type", Toast.LENGTH_SHORT).show();
            return; // Sortir de la méthode addUser car la spécialité est manquante
        }


        // Ajouter le reste de votre logique pour ajouter l'utilisateur ici
        // Par exemple, vous pouvez récupérer les valeurs des champs avec getText().toString()
        // et les utiliser pour créer un nouvel utilisateur dans votre base de données ou ailleurs
    }
}
