package game.game1;

import java.util.List;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

public class timer2 extends CountDownTimer {

	long startTime,interval;
	List<Point> points;
	int card,w,h;
	float a;
	CardView v;
	Context context;
	public timer2(long startTime, long interval,CardView v) {
		super( startTime,  interval);
		this.startTime=startTime;
		this.interval=interval;
		this.v=v;
		this.points=v.points;
		this.card=v.card;
		this.w=v.w;
		this.h=v.h;
		this.a=v.a;
		this.context=v.context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		if(checkPoints()) 
		  {
		  Toast toast = Toast.makeText(context, "TRUE", 5000);		
		  toast.show();
		  v.game.score=v.game.score+10;
		  v.game.text2.setText("Score: "+v.game.score);
		  v.game.nextCard();
		  }
	  else
	  {
		  Toast toast = Toast.makeText(context, "FALSE", 5000);
		  toast.show();
	   }
 
		points.clear();
		v.invalidate();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub


	}
	 public boolean checkPoints()
	 {
		 if(card==R.drawable.card_circle)
		 {
			 for(Point p : points)
			 {
				 int distance = (int) Math.sqrt((p.x-w/2)*(p.x-w/2) + (p.y-h/2)*(p.y-h/2));
				 if((distance>=(a-20))&&(distance<=(a+20))) continue;
				 else return false;
			 }
			 return true;
		 }
		 else if(card==R.drawable.card_horizontallines)
		 {
			 for(Point p : points)
			 {
				 int distance1 = (int) Math.abs(p.y-72);
				 int distance2 = (int) Math.abs(p.y-148);
				 int distance3 = (int) Math.abs(p.y-228);
				 if((p.x<=210)&&(p.x>=28))
				 {
				 if ((distance1<=16)||(distance2<=16)||(distance3<=16)) continue;
					else return false;
				 }
				 else return false;
			 }
		 }
		 else if(card==R.drawable.card_verticallines)
		 {
			 for(Point p : points)
			 {
				 int distance1 = (int) Math.abs(p.x-w/4);
				 int distance2 = (int) Math.abs(p.x-3*w/4);
			//	 if((p.y<=235)&&(p.y>=75))
			//	 {
				 if ((distance1<=20)||(distance2<=20)) continue;
					else return false;
			//	 }
			//	 else return false;
			 }
		 }
		 else if (card == R.drawable.card_square) {
				for (Point p : points) {
					int x1 = 86; int x2 = 398; int y1 = 189; int y2 = 514;
					int a1 = 0;	int a2 = 0;
					int b1 = y1; int b2 = y2;
					int left = 0;
					int distance1 = (int) Math.abs(p.y - a1 * p.x - b1); // for line horizontal
					int distance2 = (int) Math.abs(p.y - a2 * p.x - b2); // for line horizontal
					int distance3 = (int) Math.abs(left - x1);    // distance for vertical left line
					int distance4 = (int) Math.abs(left - x2);    // distance for vertical right line
					

					int distance_left = (int) Math.abs(p.x - distance3); // x - c = 0
					int distance_right = (int) Math.abs(p.x - distance4);
					if ((distance_left <=20)||(distance1 <=20)||(distance2 <=20)||(distance_right <=20))
					{
						continue;
					}
					
					else return false;
				}
		 }
		 else if (card==R.drawable.card_cross)
		 {
			 for (Point p: points)
			 {
				
				int x1 = 74; int x2 = 408;int y1 = 184;	int y2 = 519; int x_center = 239; int y_center = 351;
				 
				// line 1, y1 = ax1 + b, y2 = ax2 + b
				int a1 = (y2-y1)/(x2-x1);
				int b1 = (int) (y1 - (a*x1));
				int distance1 = (int) Math.abs(p.y - a1*p.x - b1);
				
				// line 2, y1 = ax2 + b, y2 = ax1 + b
				int a2 = (y2-y1)/(x1-x2);
				int b2 = (y1- a1*x2);
				int distance2 = (int) Math.abs(p.y - a2*p.x - b2);
				if ((distance1<=20)||(distance2<=20)) continue;
				else return false;
	
			 }
		 }
		 return true;
	 }


}
