package com.example.luiscaguana.apppruebasqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luiscaguana.apppruebasqlite.moden.Contacto;

import java.util.ArrayList;

public class ContactosPersistencia {

  private Context context;
  private ContactosSQLiteHelper csh;


    public ContactosPersistencia(Context context) {
        this.context = context;
        csh = new ContactosSQLiteHelper(context);

    }
    public SQLiteDatabase openReadable() {
        return csh.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return csh.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }


    public long insertarContactos(Contacto contacto){

        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();


        //volcar_todo la informacion  que queremos insertar
        ContentValues contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                contacto.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                contacto.getEmail());



        long id= database.insert(ContactoContract.ContactoEntry.TABLE_NAME,
                null, contactoValues);
        if(id !=-1){
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        //estod debemos tener para cualquier transancion
        return  id;
    }
    public void actualizar(Contacto contacto){

        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();

        //todo: Gestionar el update
        ContentValues contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                contacto.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                contacto.getEmail());

        database.update(ContactoContract.ContactoEntry.TABLE_NAME,
                contactoValues,String.format("%s=%d",
                        ContactoContract.ContactoEntry.COLUMN_ID
                        ,contacto.getId()),
                null);



        String [] whereAr = {String.valueOf(contacto.getId())};

        database.update(ContactoContract.ContactoEntry.TABLE_NAME,
                contactoValues,
                String.format(ContactoContract.ContactoEntry.COLUMN_ID + " = ?", contacto.getId()),
                whereAr);


        //estod debemos tener para cualquier transancion
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public void BorrarContacto(long idContacto){
        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();

        //TODO: ELIMINAR CONTACTO

        //creamos un arry
        String [] whereArgs = {String.valueOf(idContacto)};
        database.delete(ContactoContract.ContactoEntry.TABLE_NAME,
                ContactoContract.ContactoEntry.COLUMN_ID+" = ?",
                whereArgs);



        //estod debemos tener para cualquier transancion
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public Contacto leercontacot(long idContacto){
        Contacto contacto = null;
        SQLiteDatabase database = openReadable();
        String query = "SELECT "
                + ContactoContract.ContactoEntry.COLUMN_ID
                + ", " + ContactoContract.ContactoEntry.COLUMN_NAME
                + ", " + ContactoContract.ContactoEntry.COLUMN_MAIL
                + " FROM " + ContactoContract.ContactoEntry.TABLE_NAME
                + " WHERE "
                + ContactoContract.ContactoEntry.COLUMN_ID + " =  ?";

        String [] whereArgs = {String.valueOf(idContacto)};

        Cursor cursor= database.rawQuery(query, whereArgs);

        long id;
        String nombre;
        String email;
        if(cursor.moveToFirst()){
          id= cursor.getLong(cursor.getColumnIndex(
                  ContactoContract.ContactoEntry.COLUMN_ID));
          nombre= cursor.getString(cursor.getColumnIndex(
                  ContactoContract.ContactoEntry.COLUMN_NAME));
          email= cursor.getString(cursor.getColumnIndex(
                  ContactoContract.ContactoEntry.COLUMN_MAIL));

          contacto = new Contacto(nombre, email);
          contacto.setId(id);


        }

        cursor.close();
        close(database);
        return contacto;
    }
    public ArrayList<Contacto>leerContactos(){
        ArrayList<Contacto>listaContactos = new ArrayList<Contacto>();


        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openReadable();

        String query = "SELECT "+
                ContactoContract.ContactoEntry.COLUMN_ID+
                ", " + ContactoContract.ContactoEntry.COLUMN_NAME+
                ", " + ContactoContract.ContactoEntry.COLUMN_MAIL+
                " FROM " + ContactoContract.ContactoEntry.TABLE_NAME;
        Cursor cursor= database.rawQuery(query, null);
        Contacto contacto = null;
        long id;
        String nombre;
        String email;
        if(cursor.moveToFirst()){
            do{
                id= cursor.getLong(cursor.getColumnIndex(ContactoContract.ContactoEntry.COLUMN_ID));
                nombre= cursor.getString(cursor.getColumnIndex(ContactoContract.ContactoEntry.TABLE_NAME));
                email= cursor.getString(cursor.getColumnIndex(ContactoContract.ContactoEntry.COLUMN_MAIL));

                contacto = new Contacto(nombre, email);
                contacto.setId(id);

                listaContactos.add(contacto);



            }while (cursor.moveToFirst());{

            }
        }

        close(database);
        return listaContactos;
    }

}
