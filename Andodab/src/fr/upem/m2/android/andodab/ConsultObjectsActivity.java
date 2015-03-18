package fr.upem.m2.android.andodab;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.DessinObjet.IFilsCallback;
import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.Attribut_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class ConsultObjectsActivity extends Activity implements IFilsCallback {
	
	 
	private int x, y;
	private RelativeLayout mainLayout;
	private BddOperations db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consult_objects);	
	
		db = new BddOperations(this);
		int dbId = getIntent().getIntExtra("id", 0);
		Log.v("idB", ""+dbId);
		
//		Log.v("id", ""+dbId);
//		db.createObjet(new Objet_bean("obj1", null,null,dbId)); 
//		db.createObjet(new Objet_bean("obj2", null,null,dbId)); 
//		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consult_objects);

		/**
		 * Retrouner de la base de données la liste des objets racine
		 */
		//
		
		List<Objet_bean> listRacine = db.getListRacine(dbId);
		Log.v("taille", ""+listRacine.size());
		x=0;
		y=0;
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		
		
		
		for (Objet_bean racine : listRacine) {
			
			int objectId = racine.getObjet_id();
			
			ArrayList<Attribut_bean> attributs = new ArrayList<Attribut_bean>();
			attributs.add(new Attribut_bean("attrib1"));
			
			DessinObjet racineView = new DessinObjet(this, racine.getObjet_nom(), attributs, 80+x,60+y, false);
			
			 
  				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50+x, 40+y);
 					params.leftMargin = 80+x;
 					params.topMargin = 60+y;
 					
 					mainLayout.addView(racineView, params);
			
 					x+=160;
			
			
		}
		
		
	/*	 
		String objet1 = "objet 1";
		ArrayList<String> listAttributs1 = new ArrayList<String>();
		listAttributs1.add("Attribut 1");
		listAttributs1.add("Attribut 2");
		listAttributs1.add("Attribut 3");
		listAttributs1.add("Attribut 3");
		listAttributs1.add("Attribut 3");

		String objet2 = "objet 2";
		ArrayList<String> listAttributs2 = new ArrayList<String>();
	listAttributs2.add("Attribut 1");
		listAttributs2.add("Attribut 2");
		listAttributs2.add("Attribut 3");
		listAttributs2.add("Attribut 3");
		listAttributs2.add("Attribut 3");

		DessinObjet noyade = new DessinObjet(this, objet1, listAttributs1, 80,
				60, false);
		DessinObjet noyade2 = new DessinObjet(this, objet2, listAttributs2,
				240, 60, false);
  
		// setContentView(noyade);
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

		x = 0;
				y = 0;

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				50, 40);
		params.leftMargin = 80;
		params.topMargin = 60;

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				200, 40);
		params2.leftMargin = 240;
		params2.topMargin = 60;
		mainLayout.addView(noyade, params);
		mainLayout.addView(noyade2, params2);
		x += 300;
	y += 300;	*/	
		

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consult_objects, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	/**
	 * récuperer l'objet selectioné, l'envoyer à la base pour retourner ses fils
	 */

	@Override
	public void getFilsFromDb(String objectName, ArrayList<Attribut_bean> listAttributs) {
//	public void getFilsFromDb(String objectName, ArrayList<String> listAttributs) {
		
		mainLayout.removeAllViews();
		x = 0;
		int margeFleche = 15;
		
		

//		DessinObjet pere = new DessinObjet(this, objectName, listAttributs,
//				170, 60, false);
//		RelativeLayout.LayoutParams paramPere = new RelativeLayout.LayoutParams(
//				150, 40);
//		paramPere.leftMargin = 170;
//		paramPere.topMargin = 60;
//		mainLayout.addView(pere, paramPere);
		
		DessinObjet pere = new DessinObjet(this, objectName, listAttributs,
 				170, 60, false);
 		RelativeLayout.LayoutParams paramPere = new RelativeLayout.LayoutParams(
 				150, 40);
 		paramPere.leftMargin = 170;
 		paramPere.topMargin = 60;
 		mainLayout.addView(pere, paramPere);

		/**
		 * Appel a la base ... fils retourner par la base ...
		 */

//		ArrayList<String> filsList = new ArrayList<String>();
//		filsList.add("fils1");
//		filsList.add("fils2");
//		filsList.add("fils3");
//
//		ArrayList<ArrayList<String>> attributFils = new ArrayList<ArrayList<String>>();
//
//		for (String fils : filsList) {
//			ArrayList<String> attribs = new ArrayList<String>();
//			attribs.add("Attrib 1 " + fils);
//			attribs.add("Attrib 2 " + fils);
//			attribs.add("Attrib 3 " + fils);
//
//			attributFils.add(attribs);
//
//		}
//		/**
//		 * Supposant qu'on a les fils retourné par la base
//		 */
//
//		/**
//		 * Parcourir les fils pour les afficher
//		 */
//
//		for (int i = 0; i < filsList.size(); i++) {
////			DessinObjet noyade = new DessinObjet(this, filsList.get(i),
////					attributFils.get(i), 50 + x, 270, true);
//
//			DessinerFleche fleche = new DessinerFleche(this, 50 + x, 270,margeFleche,listAttributs.size());
//			mainLayout.addView(fleche);
//
//			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30 + x - 20, 250);
//			params.leftMargin = 50 + x;
//			params.topMargin = 270;
//			x += 150;
////			mainLayout.addView(noyade, params);
//			margeFleche+=15;
//		}
 		
 		
 		
 		ArrayList<Objet_bean> filsList = new ArrayList<Objet_bean>();
		filsList.add(new Objet_bean("fils1",null,null,0));
		filsList.add(new Objet_bean("fils2",null,null,0));

		ArrayList<ArrayList<Attribut_bean>> attributFils = new ArrayList<ArrayList<Attribut_bean>>();

		for (Objet_bean fils : filsList) {
			ArrayList<Attribut_bean> attribs = new ArrayList<Attribut_bean>();
			attribs.add(new Attribut_bean("atrib 1 "+ fils.getObjet_nom()));
			

			attributFils.add(attribs);

		}
		/**
		 * Supposant qu'on a les fils retourné par la base
		 */

		/**
		 * Parcourir les fils pour les afficher
		 */

		for (int i = 0; i < filsList.size(); i++) {
		DessinObjet filsView = new DessinObjet(this, filsList.get(i).getObjet_nom(),
					attributFils.get(i), 50 + x, 270, true);

			DessinerFleche fleche = new DessinerFleche(this, 50 + x, 270,margeFleche,listAttributs.size());
			mainLayout.addView(fleche);

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30 + x - 20, 250);
			params.leftMargin = 50 + x;
			params.topMargin = 270;
			x += 150;
			mainLayout.addView(filsView, params);
			margeFleche+=15;
		}
 		
		
	}
}
