package it.aorlando.cliccamangia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import it.aorlando.cliccamangia.Connection.Internet;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread wait = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);

                    Internet.IsInternetAvailable aTask = new Internet.IsInternetAvailable();
                    aTask.setListener(new Internet.IsInternetAvailable.IsInternetAvailableListener() {
                        @Override
                        public void onPreExecuteConcluded() {

                        }

                        @Override
                        public void onPostExecuteConcluded(boolean result) {
                            if (result) {
                                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(home);
                                finish();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SplashScreen.this);
                                builder1.setTitle(R.string.warning);
                                builder1.setMessage(R.string.internetIsRequired);
                                builder1.setCancelable(true);
                                builder1.setNeutralButton(android.R.string.ok,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(home);
                                                finish();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        }
                    });
                    aTask.execute();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        wait.start();
    }
}
