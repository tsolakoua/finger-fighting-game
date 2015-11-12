package game.game1;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

public class myTimer extends CountDownTimer {

	long startTime,interval;
	TextView text;
	GameActivity game;
	long millisUntilFinished;
	public myTimer(long startTime, long interval,GameActivity game) {
		super( startTime,  interval);
		this.startTime=startTime;
		this.interval=interval;
		this.game=game;
		this.text=game.text;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		text.setText("Time's up!");
		 Toast toast = Toast.makeText(game.getBaseContext(), "Game is Over,Score: "+game.score, 5000);
		  toast.show();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		this.millisUntilFinished=millisUntilFinished;
		 text.setText("Time remain:" + millisUntilFinished/1000);
	}
	
	public long pause()
	{
		return this.millisUntilFinished;
	}
	public void resume()
	{
		
	}
	

}
