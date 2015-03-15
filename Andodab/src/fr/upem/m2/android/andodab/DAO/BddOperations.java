package fr.upem.m2.android.andodab.DAO;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.ContentProviderExempleActivity;
import fr.upem.m2.android.andodab.beans.Attribut_bean;
import fr.upem.m2.android.andodab.beans.Bdd_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;
import fr.upem.m2.android.andodab.beans.Primitif_bean;
import fr.upem.m2.android.andodab.provider.SharedInformation.Attribut;
import fr.upem.m2.android.andodab.provider.SharedInformation.Objet;
import fr.upem.m2.android.andodab.provider.SharedInformation.Primitif;
import fr.upem.m2.android.andodab.provider.TutosAndroidProvider;
import fr.upem.m2.android.andodab.provider.SharedInformation.Bdd;
import fr.upem.m2.android.andodab.provider.SharedInformation.Final;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;



public class BddOperations {

	TutosAndroidProvider t;
	Activity activite;
	
	
	
	public BddOperations(Activity activite) {
		super();
		this.activite = activite;
	}


	public BddOperations() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void CreateBdd(String name){
		
		ContentValues bdd = new ContentValues();
		bdd.clear();
		bdd.put(Bdd.BDD_NAME, name);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_BDD,bdd);
	}
	
	public List<Bdd_bean> getListBdd(){
		Bdd_bean bean;
		List<Bdd_bean> liste=new ArrayList<Bdd_bean>();
		String columnsTest[] = new String[] {Bdd.BDD_ID,Bdd.BDD_NAME};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_BDD;
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
		if (curTest.moveToFirst()) {
			do {	
				bean=new Bdd_bean();		
				bean.setBdd_id(curTest.getInt(curTest.getColumnIndex(Bdd.BDD_ID)));
				bean.setBdd_name(curTest.getString(curTest.getColumnIndex(Bdd.BDD_NAME)));
				liste.add(bean);
				
			} while (curTest.moveToNext());
		}

		return liste;
		
	}
	
	public List<Primitif_bean> getListPrimitif(){
		
		Primitif_bean bean = new Primitif_bean();
		List<Primitif_bean> liste= new ArrayList<Primitif_bean>();
		
		String columnsTest[] = new String[] { Primitif.PRIMITIF_ID,Primitif.PRIMITIF_NAME };
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_PRIMITIF;
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
		if (curTest.moveToFirst()) {
			do {			
				bean = new Primitif_bean();
				bean.setPrimitif_id(curTest.getInt(curTest.getColumnIndex(Primitif.PRIMITIF_ID)));
				bean.setPrimitif_name(curTest.getString(curTest.getColumnIndex(Primitif.PRIMITIF_NAME)));
				liste.add(bean);
				
			} while (curTest.moveToNext());
		}

		return liste;
	}
	
	public void deleteObjet(Integer id){
		//t.delete(TutosAndroidProvider.CONTENT_URI_OBJET, selection, selectionArgs);	
	}
	
	public List<Objet_bean> getListRacine(Integer bdd_id){
		List<Objet_bean> liste = new ArrayList<Objet_bean>();
		Objet_bean bean;
		String columnsTest[] = new String[] { Objet.OBJET_ID,Objet.OBJET_NAME,Objet.OBJET_ID_OBJET,Objet.OBJET_SEALED,Objet.OBJET_BDD_ID };
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_OBJET;
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
		if (curTest.moveToFirst()) {
			do {	
				bean=new Objet_bean();
				bean = new Objet_bean();
				bean.setObjet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID)));
				bean.setObjet_nom(curTest.getString(curTest.getColumnIndex(Objet.OBJET_NAME)));
				bean.setObjet_objet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID_OBJET)));
				bean.setObjet_sealed(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_SEALED)));
				bean.setObjet_bdd_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_BDD_ID)));			
				
				if((bean.getObjet_bdd_id().equals(null))&&(bean.getObjet_bdd_id()!=bdd_id)){	
				liste.add(bean);}
				
			} while (curTest.moveToNext());
		}
		
		return liste;		
	}
	
	public List<Objet_bean> getListeObjetBDD(Integer bdd_id){
		List<Objet_bean> liste = new ArrayList<Objet_bean>();
		Objet_bean bean;
		String columnsTest[] = new String[] { Objet.OBJET_ID,Objet.OBJET_NAME,Objet.OBJET_ID_OBJET,Objet.OBJET_SEALED,Objet.OBJET_BDD_ID };
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_OBJET;
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
		if (curTest.moveToFirst()) {
			do {
				bean = new Objet_bean();
				bean.setObjet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID)));
				bean.setObjet_nom(curTest.getString(curTest.getColumnIndex(Objet.OBJET_NAME)));
				bean.setObjet_objet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID_OBJET)));
				bean.setObjet_sealed(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_SEALED)));
				bean.setObjet_bdd_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_BDD_ID)));
				
				if(bean.getObjet_bdd_id()==bdd_id){
				liste.add(bean);}
				
			} while (curTest.moveToNext());
		}
		return liste;
		
	}
	
	public List<Attribut_bean> getListAttributObjet(Integer objet_id){
		List<Attribut_bean> liste = new ArrayList<Attribut_bean>();
		
		
		
		return liste;
	}
	public void setAttributToObjet(Integer objet_id){
		
	}
	
	
}
