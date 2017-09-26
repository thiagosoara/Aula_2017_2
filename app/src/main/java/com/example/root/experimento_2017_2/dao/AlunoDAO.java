package com.example.root.experimento_2017_2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.root.experimento_2017_2.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/09/17.
 */

public class AlunoDAO extends SQLiteOpenHelper{
    public AlunoDAO(Context context) {
        super(context, "amadeus", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table aluno(id integer primary key, nome TEXT, email TEXT, telefone TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table aluno;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void salvar(Aluno aluno){
        //Montar o Dicionário de Dados = ContentValues
        ContentValues cv = getContentValues(aluno);
        //pegar uma instância de SQLiteDatabe
        SQLiteDatabase db = getWritableDatabase();
        //insert
        if (aluno.getId() == null){
            db.insert("aluno",null,cv);
        }else{
            atualizar(aluno);
        }

        /*String sql = "insert into aluno (nome,email,telefone) values (\""+aluno.getNome()+
                "\", \""+aluno.getEmail()+"\",\""+aluno.getTelefone()+"\");";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);*/
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues cv = new ContentValues();
        cv.put("nome",aluno.getNome());
        cv.put("email", aluno.getEmail());
        cv.put("telefone", aluno.getTelefone());
        return cv;
    }

    public Aluno atualizar(Aluno aluno){
        //Montar CV
        ContentValues cv = getContentValues(aluno);
        //SQLiteDatabase
        SQLiteDatabase db = getWritableDatabase();
        //update
        String[] params = {aluno.getId().toString()};
        int retorno = db.update("aluno",cv,"id = ?",params);
        if (retorno > 0){
            return aluno;
        }
        return null;
    }

    public List<Aluno> buscarAlunos(){
        String sql = "select * from aluno;";
        List<Aluno> alunos = new ArrayList<Aluno>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            alunos.add(aluno);
        }
        return alunos;
    }
}
