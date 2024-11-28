package com.example.practicafactuzone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Encuentra el contenedor principal
        RelativeLayout splashLayout = findViewById(R.id.splashLayout); // Cambiado a RelativeLayout
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashLayout.startAnimation(fadeIn);

        new Handler().postDelayed(() -> {
            // Aplica la animación de salida
            Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
            splashLayout.startAnimation(fadeOut);

            // Cambia de actividad después de la animación de salida
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }, 1000); // Duración de la animación de salida
        }, 2000); // Duración del splash (antes de la animación de salida)
    }
}
