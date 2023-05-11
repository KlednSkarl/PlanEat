package com.example.ui_presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Home extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            CardView cardView = rootView.findViewById(R.id.profile);
            CardView cardView1 = rootView.findViewById(R.id.meals);
            CardView cardView2 = rootView.findViewById(R.id.goals);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Intent i = new Intent(getActivity(), profile_settings.class);
                  startActivity(i);

                }
            });

            cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(),Planning_phase.class));
                }
            });

            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(),goals.class));
                }
            });

//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return rootView;

    }


    public void Meals(View view) {


    }
}