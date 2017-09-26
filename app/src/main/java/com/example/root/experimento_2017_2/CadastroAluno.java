package com.example.root.experimento_2017_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.experimento_2017_2.dao.AlunoDAO;
import com.example.root.experimento_2017_2.model.Aluno;

public class CadastroAluno extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        etNome = (EditText) findViewById(R.id.et_nome);
        etEmail = (EditText) findViewById(R.id.et_email);
        etTelefone = (EditText) findViewById(R.id.et_telefone);
        Button button = (Button) findViewById(R.id.bt_salvar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno();
                aluno.setNome(etNome.getText().toString());
                aluno.setEmail(etEmail.getText().toString());
                aluno.setTelefone(etTelefone.getText().toString());
                Intent intent = getIntent().putExtra("aluno",aluno);
                AlunoDAO dao = new AlunoDAO(CadastroAluno.this);
                dao.salvar(aluno);
                //setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}
