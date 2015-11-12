package game.game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class CardView extends ImageView implements OnTouchListener {

	List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    int card,h,w;
    float a;
    Context context;
    CardBack back;
    GameActivity game;
    boolean turned=false;
    
	public CardView(Context context,int card,int h,int w,GameActivity game) {
		super(context);
		// TODO Auto-generated constructor stub
		setImageResource(card);
	    this.setMinimumHeight(h);
	    this.setMinimumWidth(w);
	    setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
	    LayoutParams.WRAP_CONTENT));
	    this.setClickable(true);  
        this.setOnTouchListener(this);        
        this.context=context;
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        this.h=h;
        this.w=w;
        this.card=card;
        this.game=game;
        a=(float) ((w/2)*0.676);
        int cards[]=new int[4];
        cards[0]=R.drawable.card_blank;
        cards[1]=R.drawable.card_killself;
        cards[2]=R.drawable.card_timeextend;
        cards[3]=R.drawable.card_timeshorten;
        Random rand=new Random();
        back=new CardBack(context,cards[rand.nextInt(4)],h,w,this);
        
	}
	public void setCard(int resId)
	{
		this.card=resId;
		this.setImageResource(resId);
	}
	public CardBack getBack()
	{
		this.turned=true;
		return this.back;
	}

 	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getActionMasked();
		Point first=new Point();
	if(event.getPointerCount()==1){
    	Point point = null;
        Log.v("MotionEvent", "Action = " + action+ " View:" + view.toString());
        Log.v("MotionEvent", "X = " + event.getX() + "Y = " + event.getY());
        point = new Point();
        point.x = event.getX();
        point.y = event.getY();
       points.add(point);
        if (action == MotionEvent.ACTION_MOVE) {
            // do something
        	 point = new Point();
            point.x = event.getX();
            point.y = event.getY();
           points.add(point); 	        	
       }
       if (action == MotionEvent.ACTION_UP) {
            // do something
    	   timer2 tim=new timer2(3000,1000,this);
    	   tim.start();
       }
	}
	else if(event.getPointerCount()==2)
	{ 
	Point second=new Point();
		if (action == MotionEvent.ACTION_DOWN) {
			first.x=event.getX(0);
			first.y=event.getY(0);
		}
		if (action == MotionEvent.ACTION_POINTER_DOWN) {
			second.x=event.getX(1);
			second.y=event.getY(1);
			game.text2.setText("MULTITOUCH");
		}
		if (action == MotionEvent.ACTION_POINTER_UP) {
			float x=second.x;
			float x2=event.getX(1);
			if(x2<x) 
			{   game.text2.setText("kinisi aristera");
				game.nextCard();
			}
		}
	}
        invalidate();
        return true;
	}
	
	 @Override
	    public void onDraw(Canvas canvas) 
	    {  
		 	super.onDraw(canvas);
	        for (Point point : points) 
	        {               
	              canvas.drawCircle(point.x, point.y, 5, paint);
	        }
	        Log.v("array size:",points.size()+"");
	        if(card==R.drawable.card_circle)
	        	canvas.drawCircle(w/2,h/2,a, paint );
	        
	        else if(card==R.drawable.card_horizontallines)
	        	{
	        //	canvas.drawLine(37 ,  72 , 202 , 72 , paint);
	        	canvas.drawLine(0 ,  (h+15)/4 , w , (h+15)/4 , paint);
	        //	canvas.drawLine(37 , 148 , 202 , 148 , paint);
	        	canvas.drawLine(0 , h/2 , w , h/2 , paint);
	        //	canvas.drawLine(37 , 228 , 202 , 228 , paint);
	        	canvas.drawLine(0 , 3*(h-15)/4 , w , 3*(h-15)/4 , paint);
	        	}
	        else if(card==R.drawable.card_verticallines)
	        {
	        //	canvas.drawLine(63 , 70 , 63 , 231 ,paint);
	        //	canvas.drawLine(178 , 70 , 178 , 231 ,paint);
	        	canvas.drawLine(w/4 , 0 , w/4 , h ,paint);
	        	canvas.drawLine(3*w/4 , 0 , 3*w/4 , h ,paint);
	        	
	        }
	    }
	 

}
