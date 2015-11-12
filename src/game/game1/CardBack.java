package game.game1;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class CardBack extends ImageView {

	int card,h,w;
    float a;
    Context context;
    CardView c;
	public CardBack(Context context,int card,int h,int w,CardView c) {
		super(context);
		// TODO Auto-generated constructor stub
		this.card=card;
		this.h=h;
		this.w=w;
		setImageResource(card);
	    this.setMinimumHeight(h);
	    this.setMinimumWidth(w);
	    setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
	    LayoutParams.WRAP_CONTENT));  
        this.context=context;
        this.c=c;
	}

	public CardBack(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CardBack(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onDraw(Canvas canvas) 
    {  
	 	super.onDraw(canvas);
	 if(card==R.drawable.card_killself)
		 {	c.game.score=c.game.score-10;
	 		c.game.text2.setText("Score: "+c.game.score);
	 		Toast toast = Toast.makeText(context, "You lost 10 points", 5000);		
			  toast.show();
		 }
    }

}
