package cornelio.losyondris.com.juegoprogramacion3;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


///public class Inicio extends AppCompatActivity  implements SensorEventListener
public class Inicio extends AppCompatActivity {

    private static final String TAG = "Aimales";
    private static final long STAR_MTiempo = 6000;
    boolean running = false;
    Sensor acceleracion;
    SensorEventListener sensorEventListener;
    Dialog mydialog;
    TextView pregunta, tiempoAot, chefla, adjuste, core, A, B, C, pBueno, pMalo;
    ImageButton btnFondo;
    ConstraintLayout fondoOriginal;
    Bundle varible;
    Mtod mtd = new Mtod();
    int cont = 0;
    int Mpuntos = 0;
    int Bpuntos = 0;
    private long mtiempo = STAR_MTiempo;
    CountDownTimer mcountDownTimer;
    /// conometro del Juego

    private SensorManager sensorManager;


//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        float z = sensorEvent.values[2];
//        float a = z;
//        TextView pregunta = (TextView) findViewById(R.id.id_pregunta);
//        String prebuena =  pregunta.getText().toString().toUpperCase().trim();
//
//        if (a<-9){
//            SharedPreferences prefBuena = getSharedPreferences("PreBuena",MODE_PRIVATE);
//            SharedPreferences.Editor editor = prefBuena.edit();
//            editor.putString(prebuena,prebuena);
//            // editor.commit();
//
//               // Reset();
//               // preguntas();
//
//
//
//        }else  if (a<2){
//            Log.i("TAG","Valor atra de : "+a);
//            // getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
//            // TiempoAot();
//
//        }else  if (a>9){
//            // Log.i("TAG","Valor nose de : "+a);
//            // getWindow().getDecorView().setBackgroundColor(Color.RED);
//            SharedPreferences prefMala = getSharedPreferences("PreBuena",MODE_PRIVATE);
//            SharedPreferences.Editor editor = prefMala.edit();
//            editor.putString(prebuena,prebuena);
//            // editor.commit();
//            //preguntas();
//            //Reset();
//
//        }
//
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {}
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mydialog = new Dialog(this);
        pregunta = (TextView) findViewById(R.id.id_pregunta);
        tiempoAot = (TextView) findViewById(R.id.id_tiempo_aut);
        pBueno = (TextView) findViewById(R.id.id_pbueno);
        pMalo = (TextView) findViewById(R.id.id_pmalo);
        varible = this.getIntent().getExtras();


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// Pantalla completa
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  /////modo horizontar ( - )

        Play();
        Moviemto();

//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        acceleracion = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorManager.registerListener(Inicio.this,acceleracion,SensorManager.SENSOR_DELAY_NORMAL);


        chefla = (TextView) findViewById(R.id.id_salir);
        chefla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this, MainActivity.class));
                finish();
                mtd.musica(view.getContext());
                mydialog.dismiss();
                countDownTimer.cancel();
                running = false;
            }
        });

        core = (TextView) findViewById(R.id.btn_core);
        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Core();
                countDownTimer.cancel();
                running = false;
                // musica();
            }
        });

        adjuste = (TextView) findViewById(R.id.id_adjutes);
        adjuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adjuste();
                mtd.musica(view.getContext());
                countDownTimer.cancel();
                running = false;
            }
        });
    }

    /// play
    public void Play() {
        mydialog.setContentView(R.layout.conometro);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();
        //preguntas();


       mcountDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                mtiempo = l;
                // Update();
                int min = (int) (mtiempo / 1000) / 60;
                int seg = (int) (mtiempo / 1000) % 60;
                String cronometro = String.format(Locale.getDefault(), "%02d:%02d", min, seg);
                TextView conometro = (TextView) mydialog.findViewById(R.id.id_relo);
                conometro.setText(cronometro);
            }

            @Override
            public void onFinish() {
                Reset();
                preguntas();
                mydialog.dismiss();
                running = false;
            }
        }.start();
        running = true;



        mtd.musica(this);


    }

    public void TiempCorazon() {
        A = (TextView) findViewById(R.id.id_corazon_a);
        B = (TextView) findViewById(R.id.id_corazon_b);
        C = (TextView) findViewById(R.id.id_corazon_c);

        cont++;
        if (cont == 1) {
            // A.setBackground(ContextCompat.getDrawable(Inicio.this, R.drawable.icono_corazone_rojo));
            A.setVisibility(View.INVISIBLE);
            running = false;

        } else if (cont == 2) {
            B.setVisibility(View.INVISIBLE);
            running = false;
        }


    }

    /// tiempo agotado /// que guarde en el preferen
    public void TiempoAgotado() {
        mydialog.setContentView(R.layout.tiempo_agotado);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();


        RelativeLayout tiempoagotado = (RelativeLayout) mydialog.findViewById(R.id.tiempoagotado);
        tiempoagotado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
                mtd.musica(view.getContext());


                if (running = true) {
                    Reset();
                    preguntas();
                    TiempCorazon();
                    running = false;
                    Mpuntos++;


                }
                pMalo.setText(String.valueOf(Mpuntos));

            }
        });

        //  mtd.musica(this);


    }

    public void preguntas() {
       // mcountDownTimer.cancel();
        int pr = varible.getInt("pregunta");
        Log.i("TAG", "zxc :" + varible);
        switch (pr) {
            case 1:
                String[] Animarles = {"Perro", "Caballo", "Gallina", "Araña", "Cerdo", "Cangrejo", "Oso", "Serpiente", "Toro", "Gato"};
                mtd.preg(pregunta, Animarles);
               // Log.i("TAG", "zxc :" + 1);
                break;
            case 2:
                String[] Deporte = {"Baloncesto", "Boxeo", "Fútbol", "Gimnasia", "natación", "Beisbol", "Arqueria", "Atletismo", "Billar", "Tenis"};
                mtd.preg(pregunta, Deporte);
               // Log.i("TAG", "zxc :" + 2);

                break;
            case 3:
                String[] Presidente = {"Leonel", "Hipolito", "Danilo", "Balaguer", "Obama", "Clinton", "Jugo Chave", "FIdel Castro", "Mandeli", "Sadan Usen"};
                mtd.preg(pregunta, Presidente);
               /// Log.i("TAG", "zxc :" + 3);
                break;
        }

    }

    ///GameOver
    public void GameOver() {
        mydialog.setContentView(R.layout.game_over);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();
        Mpuntos++;
        pMalo.setText(String.valueOf(Mpuntos));

        TextView exit = (TextView) mydialog.findViewById(R.id.id_gameOver);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
                mtd.musica(view.getContext());
                startActivity(new Intent(Inicio.this, MainActivity.class));
                running = false;


            }
        });

        // mtd.musica(this);
        running = false;


    }

    /// Ajuste
    public void Adjuste() {
        mydialog.setContentView(R.layout.adjuste);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();

        fondoOriginal = (ConstraintLayout) mydialog.findViewById(R.id.id_fondoOriginar);
        btnFondo = (ImageButton) mydialog.findViewById(R.id.id_fondo_verde);
        btnFondo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                btnFondo.setBackgroundColor(ContextCompat.getColor(Inicio.this, R.id.id_fondo_verde));
            }
        });

        TextView ExitAdjuste = (TextView) mydialog.findViewById(R.id.id_exit_adjuste);
        ExitAdjuste.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
                countDownTimer.start();
            }
        });


    }

    /// Core
    public void Core() {
        mydialog.setContentView(R.layout.core);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();

        TextView exit = (TextView) mydialog.findViewById(R.id.id_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
                mtd.musica(view.getContext());
                countDownTimer.start();
            }
        });


    }

    ///tiempo
    public void Update() {
        int min = (int) (mtiempo / 1000) / 60;
        int seg = (int) (mtiempo / 1000) % 60;
        String tiempo = String.format(Locale.getDefault(), "%02d:%02d", min, seg);
        tiempoAot.setText(tiempo);
    }

    public void Reset() {
        mtiempo = STAR_MTiempo;
        Update();
        // StartTp();
        countDownTimer.start();

    }

    public void Moviemto() {

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceleracion = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // sensorManager.registerListener(Inicio.this,acceleracion,SensorManager.SENSOR_DELAY_NORMAL);
        if (acceleracion == null) {
            finish();
        } else {

        }

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                boolean move = false;
                float z = sensorEvent.values[2];
                if (z > 8) {
                    if (!move) {
                        preguntas();
                        move = true;
                        Bpuntos++;
                        pBueno.setText(String.valueOf(Bpuntos));
                    }
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        running = false;
                    }
                } else if (z <= 7 && z >= -7) {
                    if (!move) {
                        move = false;
                        // Toast.makeText(Inicio.this,"El medio",Toast.LENGTH_LONG).show();

                    }




                } else if (z < -8) {
                    if (!move) {
                        preguntas();
                        move = true;
                        Mpuntos++;
                        pMalo.setText(String.valueOf(Mpuntos));

                    }
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        running = false;
                    }
                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        StartSensor();

    }

    public void StartSensor() {
        if (sensorManager != null && acceleracion != null) {
            sensorManager.registerListener(sensorEventListener, acceleracion, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    public void StopSensor() {
        if (sensorManager != null && acceleracion != null) {
            sensorManager.unregisterListener(sensorEventListener);

        }
    }

    CountDownTimer countDownTimer = new CountDownTimer(mtiempo, 1000) {
        @Override
        public void onTick(long l) {
            mtiempo = l;
            Update();
        }

        @Override
        public void onFinish() {
            running = false;
            if (cont < 2) {
                TiempoAgotado();
                running = true;
            } else {
                C.setVisibility(View.INVISIBLE);
                running = true;
                GameOver();

            }


        }

    };

}

