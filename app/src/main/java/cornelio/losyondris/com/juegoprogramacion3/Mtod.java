package cornelio.losyondris.com.juegoprogramacion3;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
//import android.support.design.snackbar.ContentViewCallback;
import android.widget.TextView;

import java.util.List;
import java.util.Random;



public class Mtod  {
    private static final long MTiempo = 6000;
    long mtiempo = MTiempo;
    boolean running = false;
    Dialog mydialog;

    public void preg(TextView pregunta, String[] texts) {
        Random random = new Random();
        int randomInt = random.nextInt(texts.length);
        String des = texts[randomInt];
        if(!pregunta.getText().toString().equals(des)){
            pregunta.setText(des);
        }else {
            preg( pregunta,texts);
        }

    }

    ///sonido de botones
    public void musica(Context context){
        MediaPlayer sonido = MediaPlayer.create(context,R.raw.sonido_click);
        sonido.start();
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 60 * 60 / 10, 0);
    }


//    public void  time(){
//        Timer timer = new Timer();
//
//
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                ////ajecutar funcion
//
//            }
//        };
//        timer.schedule(timerTask, 0,1000);
//
//    }





}
