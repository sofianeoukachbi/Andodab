package fr.upem.m2.android.andodab;





import java.util.List;

import fr.upem.m2.android.andodab.R;
import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.AttributeObjet;
import fr.upem.m2.android.andodab.beans.Final_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Attribut;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Final;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Objet;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Valeur;
import fr.upem.m2.android.andodab.providerAndo.TutosAndroidProvider;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Bdd;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Primitif;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ContentProviderExempleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
		//test();
		//insertRecords();
		displayContentProvider();
		
	}
	
	private void test(){
		BddOperations bdd = new BddOperations(this);
		bdd.CreateBdd("juba");
	}

	private void displayContentProvider() {
		
		BddOperations bddOp= new BddOperations(this);
		Final_bean final_b=new Final_bean(2, "izan", null);
		Objet_bean objet_b=new Objet_bean("animal", null, null,null);
		Integer i=bddOp.createObjet(objet_b);
		bddOp.addFinalToObjet(2, "smith", final_b);
		
		List<AttributeObjet> list=bddOp.getListAttributObjet(2);
		
		Toast.makeText(this, list.size() + " ", Toast.LENGTH_LONG).show();
		
		
		
		//Toast.makeText(this, name + " ", Toast.LENGTH_LONG).show();
		
		
		
		String columnsTest[] = new String[] { Valeur.VALEUR_ID,Valeur.ATTRIBUT_ID,Valeur.FINAL_ID};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_VALEUR;
		//String contraites[] =new String[] {"1"};
		Cursor curTest = managedQuery(mContactsTest, columnsTest, null, null, null);
		Toast.makeText(ContentProviderExempleActivity.this, curTest.getCount() + "",
				Toast.LENGTH_LONG).show();

		if (curTest.moveToFirst()) {
			Integer id = null;
			Integer id_a = null;
			Integer id_f=null;
			do {
				id = curTest.getInt(curTest.getColumnIndex(Valeur.VALEUR_ID));
				id_a = curTest.getInt(curTest.getColumnIndex(Valeur.ATTRIBUT_ID));
				id_f=curTest.getInt(curTest.getColumnIndex(Valeur.FINAL_ID));
				Toast.makeText(this, id+" "+id_a+" "+id_f + " ", Toast.LENGTH_LONG).show();
			} while (curTest.moveToNext());
				
		}
		
		
		
		
		
//		String columns[] = new String[] { Cours.COURS_ID, Cours.COURS_NAME, Cours.COURS_DESC };
//		Uri mContacts = TutosAndroidProvider.CONTENT_URI_COURS;
//		Cursor cur = managedQuery(mContacts, columns, null, null, null);
//		Toast.makeText(ContentProviderExempleActivity.this, cur.getCount() + "",
//				Toast.LENGTH_LONG).show();
//
//		if (cur.moveToFirst()) {
//			String name = null;
//			do {
//				name = cur.getString(cur.getColumnIndex(Cours.COURS_ID)) + " " +
//						cur.getString(cur.getColumnIndex(Cours.COURS_NAME)) + " " + 
//						cur.getString(cur.getColumnIndex(Cours.COURS_DESC));
//				Toast.makeText(this, name + " ", Toast.LENGTH_LONG).show();
//			} while (cur.moveToNext());
//		}
		
		
		

	}

	private void insertRecords() {
		
		ContentValues contact = new ContentValues();


		contact.clear();
		contact.put(Bdd.BDD_NAME, "TEST izan");
		getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_BDD, contact);
		
		contact.clear();
		contact.put(Primitif.PRIMITIF_NAME, "Primitif test");
		getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_PRIMITIF, contact);

		
		
	}
}