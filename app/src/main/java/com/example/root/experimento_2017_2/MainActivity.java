package com.example.root.experimento_2017_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, CadastroAluno.class);
                it.putExtra("aluno", adapter.getItem(position));
                startActivity(it);
            }
        });

        registerForContextMenu(lvAlunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_aluno, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = adapter.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.mi_nome:
                Toast.makeText(this, "Nome: " + aluno, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mi_ligar:
                /*String uri = "tel:" + aluno.getTelefone().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);*/
                return true;
            default:
                return super.onContextItemSelected(item);
        /*AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.mi_nome:
                Toast.makeText(this, "Nome: "+aluno, Toast.LENGTH_SHORT).show(); */

        }
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
