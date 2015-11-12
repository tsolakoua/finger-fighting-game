package game.game1;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener,SensorEventListener{
	 public CardView circle,cross,horizontal,vertical,square;
	 Button button,start,back;
	 TextView text,text2;
	 int ht ,wt,random;
	 long time;
	 int score=0;
	 myTimer timer;
	 LinearLayout mLinearLayout;
	 CardView cards[]=new CardView[5];
	Chronometer chronometer;
	SensorManager sensorManager;
	private float mAccel; // acceleration apart from gravity
	  private float mAccelCurrent; // current acceleration including gravity
	  private float mAccelLast; // last acceleration including gravity
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         DisplayMetrics displaymetrics = new DisplayMetrics();
         getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
          ht = displaymetrics.heightPixels-100;
          wt = displaymetrics.widthPixels;
         vertical=new CardView(this,R.drawable.card_verticallines,ht,wt,this);
         circle=new CardView(this,R.drawable.card_circle,ht,wt,this);
         cross=new CardView(this,R.drawable.card_cross,ht,wt,this);
         square=new CardView(this,R.drawable.card_square,ht,wt,this);
         horizontal=new CardView(this,R.drawable.card_horizontallines,ht,wt,this);
         cards[0]=circle;
         cards[1]=vertical;
         cards[2]=horizontal;
         cards[3]=square;
         cards[4]=cross;
        text=(TextView) findViewById(R.id.text);
        text2=(TextView) findViewById(R.id.textView1);
     //   text2.setText("Height: "+ht+" , Width: "+wt);
        text2.setText("Score: "+score);
         timer=new myTimer(30000,1000,this);
         mLinearLayout=(LinearLayout) findViewById(R.id.mLinearLayout);
         button=(Button)findViewById(R.id.button1);
         button.setOnClickListener(this);
         back=(Button)findViewById(R.id.back);
         back.setOnClickListener(this);
         start=(Button)findViewById(R.id.start);
         start.setOnClickListener(this);
         
         sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
         sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sensorManager.SENSOR_DELAY_NORMAL);
         mAccel = 0.00f;
         mAccelCurrent = SensorManager.GRAVITY_EARTH;
         mAccelLast = SensorManager.GRAVITY_EARTH;
         
        setContentView(mLinearLayout);
    }
    public void nextCard()
    {
    	mLinearLayout.removeView(cards[random]);
		Random rand=new Random();
		int r=rand.nextInt(5);
        while(r==random)
        {
        	r=rand.nextInt(5);
        }
        random=r;
	 mLinearLayout.addView(cards[random]);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if( button.getId() == ((Button)arg0).getId() ){
			 nextCard();
			 
	}
		else if( start.getId() == ((Button)arg0).getId() ){
			Random rand=new Random();
	         random = rand.nextInt(5);
	        // Add the ImageView to the layout and set the layout as the content view
	        mLinearLayout.addView(cards[random]);
			timer.start();
		}
		else if( back.getId() == ((Button)arg0).getId() ){
			turnBack();
	        
		}
		
	}
	public void turnBack()
	{
		CardView c;
		 if(mLinearLayout.getChildAt(mLinearLayout.getChildCount()-1).getClass()==CardView.class)
		 {
			 c=(CardView)mLinearLayout.getChildAt(mLinearLayout.getChildCount()-1);
			 if(!((c.turned)))
		{
			time=timer.pause();
			mLinearLayout.removeView(c);
			mLinearLayout.addView(c.getBack());
			timer.cancel();
		}
		 }
		else
		{
			CardBack b=(CardBack)mLinearLayout.getChildAt(mLinearLayout.getChildCount()-1);
			mLinearLayout.removeView(b);
			mLinearLayout.addView(b.c);
		}
	
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

			float x = event.values[0];
		      float y = event.values[1];
		      float z = event.values[2];
		      mAccelLast = mAccelCurrent;
		      mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
		      float delta = mAccelCurrent - mAccelLast;
		      mAccel = mAccel * 0.9f + delta; // perform low-cut filter
		      if(mAccel>2) turnBack();
		}
			
	}
	
	
}
