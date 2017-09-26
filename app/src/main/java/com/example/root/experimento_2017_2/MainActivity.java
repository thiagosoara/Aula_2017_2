package com.example.root.experimento_2017_2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.root.experimento_2017_2.dao.AlunoDAO;
import com.example.root.experimento_2017_2.model.Aluno;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private static final int ADICIONAR_ALUNO_CODE = 1987;
    private Aluno aluno;
    private ListView lvAlunos;
    private ArrayList<Aluno> alunos;
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregaLista();
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroAluno.class);
                startActivity(intent);
            }
        });
    }

    private void carregaLista() {
        lvAlunos = (ListView) findViewById(R.id.lv_alunos);
        AlunoDAO dao = new AlunoDAO(this);
        alunos = (ArrayList<Aluno>) dao.buscarAlunos();
        adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lvAlunos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == ADICIONAR_ALUNO_CODE){
                aluno = (Aluno) data.getSerializableExtra("aluno");
                alunos.add(aluno);
                adapter.notifyDataSetChanged();
            }
        }
    }*/
}
