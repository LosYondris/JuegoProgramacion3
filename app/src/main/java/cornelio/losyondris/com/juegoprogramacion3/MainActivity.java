package cornelio.losyondris.com.juegoprogramacion3;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Mtod mtd = new Mtod();
    Dialog mydialog;
   // private long mTiempo = 6000;
    //boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydialog = new Dialog(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// Pantalla completa
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); /////modo horizontar ( - )
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); /////modo verticar ( | )


        ImageButton aniameles = (ImageButton) findViewById(R.id.id_anaimales);
        aniameles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent td = new Intent(MainActivity.this,Inicio.class);
                int d = 1;
                td.putExtra("pregunta",d);
                startActivity(td);
                finish();

            }
        });
        ImageButton deporte = (ImageButton) findViewById(R.id.id_deportes);
        deporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent td = new Intent(MainActivity.this,Inicio.class);
                int d = 2;
                td.putExtra("pregunta",d);
                startActivity(td);
                finish();
            }
        });

        ImageButton presidente = (ImageButton) findViewById(R.id.id_presidente);
        presidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent td = new Intent(MainActivity.this,Inicio.class);
                int d = 3;
                td.putExtra("pregunta",d);
                startActivity(td);
                finish();
            }
        });

        TextView Salirr = (TextView) findViewById(R.id.Salirr);
        Salirr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtd.musica(MainActivity.this);
                AlertaSalir();
            }
        });

        ///metodo para controlar la flecha de atra del telefono




    }

    /// AlertaSalir
    public  void AlertaSalir(){
        mydialog.setContentView(R.layout.salir);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false);
        mydialog.show();

        TextView salir = (TextView) mydialog.findViewById(R.id.id_dialogo_salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
                int p = android.os.Process.myPid();
                android.os.Process.killProcess(p);
                finish();
            }
        });

        TextView nosalir = (TextView) mydialog.findViewById(R.id.id_dialogo_nosalir);
        nosalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog.dismiss();
            }
        });


    }


    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            // Toast.makeText(getApplicationContext(),"tb",Toast.LENGTH_LONG).show();
            int p = android.os.Process.myPid();
            android.os.Process.killProcess(p);

        }
        return false;
    }
}
