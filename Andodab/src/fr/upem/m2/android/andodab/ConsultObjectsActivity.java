package fr.upem.m2.android.andodab;

import java.util.ArrayList;

import fr.upem.m2.android.andodab.DessinObjet.IFilsCallback;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class ConsultObjectsActivity extends Activity implements IFilsCallback {
	
	private ArrayList<DessinObjet> lista;
	private int x, y;
	private RelativeLayout mainLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consult_objects);	
				
		lista = new ArrayList<DessinObjet>();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consult_objects);

		/**
		 * Retrouner de la base de donn�es la liste des objets racine
		 */

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

		// noyade.getFils().add(new Rectangle(this, 200, 300, new
		// ArrayList<Rectangle>()));

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
		y += 300;		
		
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
	 * r�cuperer l'objet selection�, l'envoyer � la base pour retourner ses fils
	 */

	@Override
	public void getFilsFromDb(String objectName, ArrayList<String> listAttributs) {
		
		mainLayout.removeAllViews();
		x = 0;
		int margeFleche = 15;
		/**
		 * Dessiner le p�re (L'objet selectionn�)
		 */

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

		ArrayList<String> filsList = new ArrayList<String>();
		filsList.add("fils1");
		filsList.add("fils2");
		filsList.add("fils3");

		ArrayList<ArrayList<String>> attributFils = new ArrayList<ArrayList<String>>();

		for (String fils : filsList) {
			ArrayList<String> attribs = new ArrayList<String>();
			attribs.add("Attrib 1 " + fils);
			attribs.add("Attrib 2 " + fils);
			attribs.add("Attrib 3 " + fils);

			attributFils.add(attribs);

		}
		/**
		 * Supposant qu'on a les fils retourn� par la base
		 */

		/**
		 * Parcourir les fils pour les afficher
		 */

		for (int i = 0; i < filsList.size(); i++) {
			DessinObjet noyade = new DessinObjet(this, filsList.get(i),
					attributFils.get(i), 50 + x, 270, true);

			DessinerFleche fleche = new DessinerFleche(this, 50 + x, 270,margeFleche,listAttributs.size());
			mainLayout.addView(fleche);

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30 + x - 20, 250);
			params.leftMargin = 50 + x;
			params.topMargin = 270;
			x += 150;
			mainLayout.addView(noyade, params);
			margeFleche+=15;
		}
		
	}
}