package fr.upem.m2.android.andodab;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.AttributeObjet;
import fr.upem.m2.android.andodab.beans.Bdd_bean;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditValueObjectActivity extends Activity {
	private LinearLayout linearLayout ;
	private LinearLayout simplelinear;
	Button btOk;
	Button btno;
	int idB;
	
	private BddOperations bddo;
	List<String> list = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   idB = getIntent().getIntExtra("db_id", 0);
		   
		   bddo = new BddOperations(this);
		   List<AttributeObjet> lis = bddo.getListAttributObjet(idB);
//		   Log.i("li", lis.size()+"");
		   Toast.makeText(EditValueObjectActivity.this,lis.size()+"", Toast.LENGTH_SHORT).show();
		   
	       /**
	        * creation du linearLayout principale
	        */
	        LinearLayout linLayout = new LinearLayout(this);
	        /**
	         * specifying vertical orientation
	         */
	 
	        linLayout.setOrientation(LinearLayout.VERTICAL);
	        /**
	         * creating LayoutParams
	         */
	          
	        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); 
	        
	        /**
	         *  set LinearLayout as a root element of the screen 
	         */
	        setContentView(linLayout, linLayoutParam);
	        list.add("1");
	        list.add("2");
	        LayoutParams firstParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
	        LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        lpView.width=120;
	        lpView.height=80;
	        lpView.topMargin=50;
 	         /**
 	          * afficher les attributs
 	          */
	        for(String l: list)
	        {
	        	if(l.equals("1"))
	        	{
	        	 LinearLayout firstLayout = new LinearLayout(this);
	 	        // specifying vertical orientation
	 	        firstLayout.setOrientation(LinearLayout.HORIZONTAL);
	 	       firstLayout.setLayoutParams(firstParam); 
	 	        
	 	        	        
	 	        TextView tv = new TextView(this);
	 	        tv.setText("nom de l'attribut");
	 	        tv.setLayoutParams(lpView);
	 	        firstLayout.addView(tv,lpView);	        
	 	        EditText et = new EditText(this);
	 	        et.setText("sf");
	 	        et.setLayoutParams(lpView);
	 	        firstLayout.addView(et, lpView);
	 	       linLayout.addView(firstLayout);
	        	}else{
	        		
	        	    LinearLayout secondLayout = new LinearLayout(this);
	    	        secondLayout.setOrientation(LinearLayout.HORIZONTAL);	       
	    	        secondLayout.setLayoutParams(firstParam);  
	    	        TextView tv1 = new TextView(this);
	    	        tv1.setText("nom de l'attribut");
	    	        tv1.setLayoutParams(lpView);
	    	        secondLayout.addView(tv1,lpView);	        
	    	        EditText et1 = new EditText(this);
	    	        et1.setText("te");
	    	        secondLayout.addView(et1, lpView);
	    	        EditText et2 = new EditText(this);
	    	        et2.setText("te2");
	    	        secondLayout.addView(et2, lpView);
	    	        linLayout.addView(secondLayout);
	        		
	        	}
	        }
	        
	        /**
	         * ajout du linearlayout des boutons
	         */

	        LinearLayout buttonLayout = new LinearLayout(this);
	        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);	       
	        buttonLayout.setLayoutParams(firstParam);
	        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        rightGravityParams.leftMargin=50;

	        rightGravityParams.width=190;
	        rightGravityParams.height=80;
	        rightGravityParams.gravity = Gravity.END;
	        LinearLayout.LayoutParams leftGravityParams = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	        leftGravityParams.leftMargin=20;
	        leftGravityParams.width=190;
	        leftGravityParams.height=80;
	        
	         btOk = new Button(this);
	        btOk.setText("Ok");
	        btOk.setLayoutParams(leftGravityParams);
	        
	        btOk.setBackgroundColor(getResources().getColor(R.color.success));
	        Drawable imgn = getBaseContext().getResources().getDrawable( R.drawable.ok );
	        imgn.setBounds( 0, 0, 60, 60 );
	        btOk.setCompoundDrawables( imgn, null, null, null );

	        buttonLayout.addView(btOk,leftGravityParams);	 
	        
	         btno = new Button(this);
	        btno.setText("Annuler");
	        btno.setWidth(RESULT_OK);
	        btno.setHeight(RESULT_OK);
	        btno.setBackgroundColor(getResources().getColor(R.color.no));
	        Drawable img = getBaseContext().getResources().getDrawable( R.drawable.annuler );
	        img.setBounds( 0, 0, 60, 60 );
	        btno.setCompoundDrawables( img, null, null, null );

	        buttonLayout.addView(btno,rightGravityParams);	
	       
	      
	       /**
	        * ajout du layout des bouton dans le layout principale
	        */
	   
	      
	        linLayout.addView(buttonLayout);
	        
	        
	        initEventView() ;

	}
	
	
	/**
	 * initier les evenements
	 */
	public void initEventView() {
		btOk.setOnClickListener(btOk_click);
		btno.setOnClickListener(btno_click);

		
	}
	

	/**
	 * lorsqu'on clique sur ok 
	 */
	private android.view.View.OnClickListener btOk_click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(),
					EditObjectActivity.class);
			startActivity(intent);
			
		}
		
	};
	
	/**
	 * lorsuqu'on clique sur annuler
	 */
	private android.view.View.OnClickListener btno_click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(),
					EditObjectActivity.class);
			startActivity(intent);
			
		}
		
	};

}
