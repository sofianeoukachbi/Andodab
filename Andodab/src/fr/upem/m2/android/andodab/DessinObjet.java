package fr.upem.m2.android.andodab;

import java.util.ArrayList;

import fr.upem.m2.android.andodab.beans.Attribut_bean;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

  public class DessinObjet extends View  {
	/**
	 * 
	 * @author Aymen
	 * envoyer l'objet selectionné vers l'activité pour chercher son fils
	 *
	 */
	  public interface IFilsCallback {
	        void getFilsFromDb(String objectName,ArrayList<Attribut_bean> listAttributs);
		 // void getFilsFromDb(String objectName,ArrayList<String> listAttributs);
	    }
	  
	  
	  
	private IFilsCallback interfFils;
	private  String objectName ;
   private ArrayList<Attribut_bean> attributs ; 
//	 private ArrayList<String> attributs ; 
    private int yText;
    private int xObject;
    private int yObject;
    private Boolean dessinerFleche;
      
    
public DessinObjet(Context context,String objectName, ArrayList<Attribut_bean> listAttributs,int x,int y,Boolean dessinerFleche) {
//    public DessinObjet(Context context,String objectName, ArrayList<String> listAttributs,int x,int y,Boolean dessinerFleche) {
		super(context);	
		setBackgroundColor(Color.CYAN);
		this.attributs = new ArrayList<Attribut_bean>(listAttributs);
	//	this.attributs = listAttributs;
//		this.attributs = new ArrayList<String>(listAttributs);
		this.objectName = objectName;		
		this.xObject = x;
		this.yObject = y;
		this.dessinerFleche = dessinerFleche;
		yText=0;
		interfFils = (IFilsCallback)context; 
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.v("click",""+ this.attributs.size());
/**
 * Envoyer à l'activité l'objet selectionner pour interoger la base de données et chercher ses fils
 */
	interfFils.getFilsFromDb(this.objectName, this.attributs);
	
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.RED);
		p.setTextSize(18);
		canvas.drawText(this.objectName, 20, 17, p);
		
		
		/**
		 * Afficher dans le rectangle tous les attributs de l'objet
		 */
		if(attributs.size()>0){
			yText+=20;
			p.setColor(Color.BLACK);
			p.setStyle(Style.STROKE);
			Rect r = new Rect(0, 0, getWidth()-2, getHeight()-2);
			canvas.drawRect(r, p);
			p.setTextSize(14);
			
			for (Attribut_bean attribut : attributs) {
//				for (String attribut : attributs) {
					canvas.drawText(attribut.getAttribut_name(), 10, 17+yText, p);	
//					canvas.drawText(attribut, 10, 17+yText, p);	
					yText+=20;
				}
				
		}
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(125, 45*this.attributs.size());
		
	}

	
}
