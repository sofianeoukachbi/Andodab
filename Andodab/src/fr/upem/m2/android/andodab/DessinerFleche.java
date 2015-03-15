package fr.upem.m2.android.andodab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DessinerFleche extends View {
	private int x;
	private int y;
	private int margeFleche;
	private int tailleParent;

	public DessinerFleche(Context context,int x,int y, int margeFleche, int tailleParent) {
		super(context);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y= y;
		this.margeFleche = margeFleche;
		this.tailleParent = tailleParent;
		setBackgroundColor(Color.TRANSPARENT);
		 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint p = new Paint();		
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(2);
		p.setColor(Color.RED);	
		p.setAntiAlias(true);
		canvas.drawLine(x+70, y, 235, 30*this.tailleParent+57, p);			
		
	}

}
