package fr.upem.m2.android.andodab.DAO;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.beans.Attribut_bean;
import fr.upem.m2.android.andodab.beans.Bdd_bean;
import fr.upem.m2.android.andodab.beans.Final_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;
import fr.upem.m2.android.andodab.beans.Primitif_bean;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Valeur;
import fr.upem.m2.android.andodab.providerAndo.TutosAndroidProvider;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Attribut;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Bdd;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Final;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Objet;
import fr.upem.m2.android.andodab.providerAndo.SharedInformation.Primitif;
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
	
	//creation d'une base de donnee
	public void CreateBdd(String name){
		
		ContentValues bdd = new ContentValues();
		bdd.clear();
		bdd.put(Bdd.BDD_NAME, name);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_BDD,bdd);
	}
	
	//creation d'un objet
	public Integer createObjet(Objet_bean objet){
		ContentValues bdd = new ContentValues();
		bdd.clear();
		bdd.put(Objet.OBJET_NAME, objet.getObjet_nom());
		bdd.put(Objet.OBJET_SEALED, objet.getObjet_sealed());
		bdd.put(Objet.OBJET_BDD_ID, objet.getObjet_bdd_id());
		bdd.put(Objet.OBJET_ID_OBJET, objet.getObjet_objet_id());
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_OBJET,bdd);
		
		Integer max=null;
		String columnsTest[] = new String[] {"id_max"};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_MAXOBJET;
        Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
		if (curTest.moveToFirst()) {
            
			do {				

				max=curTest.getInt(curTest.getColumnIndex("id_max"));
				
				
			} while (curTest.moveToNext());
		
		}
			return max;
	}
	
	//ajouter un attribut comme objet pour un objet
	public void addObjetToObjet(Integer objet_id, String attributName, Objet_bean objet  ){
		///creation de l'attribut
		ContentValues attribut=new ContentValues();
		attribut.clear();
		attribut.put(Attribut.ATTRIBUT_NAME, attributName);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_ATTRIBUT,attribut);
		
		///recuperer l id de l'attribut
		String columnsTest[] = new String[] {"id_max"};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_MAXATTRIBUT;
        Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
        Integer max=null;
        
		if (curTest.moveToFirst()) {
            
			do {				

				max=curTest.getInt(curTest.getColumnIndex("id_max"));
				
				
			} while (curTest.moveToNext());
		
		}
		
		
		ContentValues valeur=new ContentValues();
		valeur.clear();
		valeur.put(Valeur.OBJET_TYPE_ID, objet.getObjet_id());
		valeur.put(Valeur.OBJET_ID, objet_id);
		valeur.put(Valeur.ATTRIBUT_ID, max);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_VALEUR,attribut);
		
	}
	
	//ajouter un attribut comme attribut à un objet
	public void addPrimitifToObjet(Integer objet_id, String attributName, Primitif_bean primitif  ){
		///creation de l'attribut
		ContentValues attribut=new ContentValues();
		attribut.clear();
		attribut.put(Attribut.ATTRIBUT_NAME, attributName);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_ATTRIBUT,attribut);
		
		///recuperer l id de l'attribut
		String columnsTest[] = new String[] {"id_max"};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_MAXATTRIBUT;
        Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
        Integer max=null;
        
		if (curTest.moveToFirst()) {
            
			do {				

				max=curTest.getInt(curTest.getColumnIndex("id_max"));
				
				
			} while (curTest.moveToNext());
		
		}
		
		
		ContentValues valeur=new ContentValues();
		valeur.clear();
		valeur.put(Valeur.PRIMITIF_ID, primitif.getPrimitif_id());
		valeur.put(Valeur.OBJET_ID, objet_id);
		valeur.put(Valeur.ATTRIBUT_ID, max);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_VALEUR,attribut);
		
	}
	
	
	public void addFinalToObjet(Integer objet_id, String attributName, Final_bean final_b  ){
		///creation de l'attribut
		ContentValues attribut=new ContentValues();
		attribut.clear();
		attribut.put(Attribut.ATTRIBUT_NAME, attributName);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_ATTRIBUT,attribut);
		
		///recuperer l id de l'attribut
		String columnsTest[] = new String[] {"id_max"};
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_MAXATTRIBUT;
        Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, null, null);
		
        Integer max=null;
        
		if (curTest.moveToFirst()) {
            
			do {				

				max=curTest.getInt(curTest.getColumnIndex("id_max"));
				
				
			} while (curTest.moveToNext());
		
		}
		//creation de la valeur final
		ContentValues finalval=new ContentValues();
		finalval.clear();
		finalval.put(Final.FINAL_VAL, final_b.getFinal_val());
		finalval.put(Final.FINAL_PRIMITIF_ID, final_b.getFinal_primitif_id());
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_FINAL,finalval);
		
		///recuperer l id de l'attribut final inserer
		String columnsTestf[] = new String[] {"id_max"};
		Uri mContactsTestf = TutosAndroidProvider.CONTENT_URI_MAXFINAL;
		Cursor curTestf = activite.managedQuery(mContactsTestf, columnsTestf, null, null, null);
				
		Integer maxf=null;
		        
	    if (curTest.moveToFirst()) {
		            
		         do {				

						max=curTest.getInt(curTest.getColumnIndex("id_max"));
						
						
					} while (curTest.moveToNext());
				
				}
		
		ContentValues valeur=new ContentValues();
		valeur.clear();
		
		valeur.put(Valeur.FINAL_ID, maxf);
		valeur.put(Valeur.ATTRIBUT_ID, max);
		valeur.put(Valeur.OBJET_ID, objet_id);
		activite.getContentResolver().insert(TutosAndroidProvider.CONTENT_URI_FINAL,attribut);
	
	}
	
	//recuperer la liste des a bases de donnees 
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
	
	//recuperer la liste des files d'un objet avec son identifiant
	public List<Objet_bean> getChildObjet(Integer idParent){
		List<Objet_bean> liste = new ArrayList<Objet_bean>();
		Objet_bean bean;
		String columnsTest[] = new String[] { Objet.OBJET_ID,Objet.OBJET_NAME,Objet.OBJET_ID_OBJET,Objet.OBJET_SEALED,Objet.OBJET_BDD_ID };
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_CHILD_OBJET;
		String contraites[] =new String[] {idParent.toString()};
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, contraites, null);
		
		if (curTest.moveToFirst()) {
			do {	
			
				bean = new Objet_bean();
				bean.setObjet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID)));
				bean.setObjet_nom(curTest.getString(curTest.getColumnIndex(Objet.OBJET_NAME)));
				bean.setObjet_objet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID_OBJET)));
				bean.setObjet_sealed(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_SEALED)));
				bean.setObjet_bdd_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_BDD_ID)));
			    liste.add(bean);
		   } while (curTest.moveToNext());
	}
	return liste;
	}
	
	//recuper les liste des premitifs
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
	
	//supprimer un objet
	public void deleteObjet(Integer id){
		//t.delete(TutosAndroidProvider.CONTENT_URI_OBJET, selection, selectionArgs);	
	}
	// recuperer la liste des objet racine d'une base de donnee comme identifiant bdd_id
	public List<Objet_bean> getListRacine(Integer bdd_id){
		List<Objet_bean> liste = new ArrayList<Objet_bean>();
		Objet_bean bean;
		String[] bddC=new String[]{bdd_id.toString()};
		String columnsTest[] = new String[] { Objet.OBJET_ID,Objet.OBJET_NAME,Objet.OBJET_ID_OBJET,Objet.OBJET_SEALED,Objet.OBJET_BDD_ID };
		Uri mContactsTest = TutosAndroidProvider.CONTENT_URI_OBJETRACINE;
		Cursor curTest = activite.managedQuery(mContactsTest, columnsTest, null, bddC, null);
		
		if (curTest.moveToFirst()) {
			do {	
				bean=new Objet_bean();
				
				bean.setObjet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID)));
				bean.setObjet_nom(curTest.getString(curTest.getColumnIndex(Objet.OBJET_NAME)));
				bean.setObjet_objet_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_ID_OBJET)));
				bean.setObjet_sealed(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_SEALED)));
				bean.setObjet_bdd_id(curTest.getInt(curTest.getColumnIndex(Objet.OBJET_BDD_ID)));			
				
					
				liste.add(bean);
				
			} while (curTest.moveToNext());
		}
		
		return liste;		
	}
	
	//recuperer la liste des objets d'une base de donnee comme identifiant bdd_id
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
	//recuper la liste des attributs d'un objets
	public List<Attribut_bean> getListAttributObjet(Integer objet_id){
		List<Attribut_bean> liste = new ArrayList<Attribut_bean>();
		
		
		
		return liste;
	}
	
	
	
	
}
