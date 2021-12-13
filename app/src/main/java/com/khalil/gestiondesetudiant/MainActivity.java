package com.khalil.gestiondesetudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName,lastName,aff;
    RadioButton ti,dsi,rsi;
    EtudiantDAO DAO;
    RadioGroup rd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         DAO = new EtudiantDAO(getApplicationContext());

         rd = findViewById(R.id.radioGroup);
         firstName = findViewById(R.id.prenom);
         lastName = findViewById(R.id.nom);
         aff = findViewById(R.id.edit);
         ti  = findViewById(R.id.radio1);
         dsi = findViewById(R.id.radio2);
         rsi = findViewById(R.id.radio3);
    }


    public void add_student(View view) {
        String par ="";
        if(ti.isChecked()){
            par="Ti";
        }else if (dsi.isChecked()){
            par="DSI";
        }else if (rsi.isChecked()){
            par="RSI";
        }
        Etudiant e = new Etudiant(firstName.getText().toString(), lastName.getText().toString(),par);
        DAO.insertdata(e);
        Toast.makeText(this, "Etudiant ajouter avec success !!", Toast.LENGTH_SHORT).show();

    }

    public void show_all(View view) {
        Cursor c=DAO.showdata();

        if( c.getCount()==0 ){
            Toast.makeText(getApplicationContext(), "List vide ", Toast.LENGTH_SHORT).show();

        }else {
            String chaine = "";
            while (c.moveToNext()) {
                chaine=chaine+ "Etudiant : "+c.getInt(0)+" de nom : "+c.getString(1)+
                        " "+c.getString(2)+" suivant le parcour :"+c.getString(3)+ '\n';
            }
            aff.setText(chaine  );
        }
    }

    public void delete_student(View view) {
        int id =Integer.parseInt(aff.getText().toString());
        DAO.deleData(id);
        Toast.makeText(getApplicationContext(), "Etudaint de ID : "+id+" a été supprimer !!", Toast.LENGTH_SHORT).show();
        Log.i(String.valueOf(getApplicationContext()), "delete_student: ");
    }

    public void search_student(View view) {
        int id =Integer.parseInt(aff.getText().toString());

        Cursor c= DAO.showEtudiant(id);
        if( c.getCount()==0 ){
            Toast.makeText(getApplicationContext(), "Etudiant introuvable fausse ID !!", Toast.LENGTH_SHORT).show();

        }else{
            while (c.moveToNext()) {
                String ch = "";
                firstName.setText(c.getString(1));
                lastName.setText(c.getString(2));
                ch = c.getString(3);
                if(ch.equalsIgnoreCase("Ti")){
                    rd.check(R.id.radio1);
                }else if(ch.equalsIgnoreCase("DSI")){
                    rd.check(R.id.radio2);
                }else {
                    rd.check(R.id.radio3);
                }
                /*aff.setText("Etudiant : "+c.getInt(0)+" de nom : "+c.getString(1)+
                        " "+c.getString(2)+" suivant le parcour :"+c.getString(3));*/

            }
        }
    }

    public void modify_student(View view) {

        int id =Integer.parseInt(aff.getText().toString());


        String par ="";
        if(ti.isChecked()){
            par="Ti";
        }else if (dsi.isChecked()){
            par="DSI";
        }else if (rsi.isChecked()){
            par="RSI";
        }


        Etudiant e = new Etudiant(firstName.getText().toString(), lastName.getText().toString(),par);
        DAO.updateData(e,id);
        Toast.makeText(getApplicationContext(), "Etudiant modifiée avec success !!", Toast.LENGTH_SHORT).show();
        Log.i(String.valueOf(this), "modify_student: ");
    }
}