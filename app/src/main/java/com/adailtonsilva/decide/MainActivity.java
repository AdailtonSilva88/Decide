package com.adailtonsilva.decide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String preco;
    Switch preciso,quero, posso;
    public EditText txtPreco;
    private Button btnDecide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Carrega os dados
        inicializar();
        //Metodo Click do botão
        btnDecide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos(view);
            }
        });
    }

    public void inicializar(){
        //Carregando os dados da tela para variaveis
        btnDecide = findViewById(R.id.btnDecide);
        txtPreco = findViewById(R.id.txtPreco);
        preciso = findViewById(R.id.switchPreciso);
        quero = findViewById(R.id.switchQuero);
        posso = findViewById(R.id.switchPosso);
    }

    public void validarCampos(View view){
        preco = txtPreco.getText().toString(); //converte pra String

        if(!preco.isEmpty()){ //Verifica se vazio
            //conversão de String para Double
            Double valor = Double.parseDouble(preco);// converte para double
            decide(valor);//chama o metodo decide e passa o valor não vazio
        }else{
            //Aviso para preencher o campo
            Toast.makeText(this, "Preencha o Valor", Toast.LENGTH_LONG).show();
        }
    }

    public void decide(Double valor){
        Double pagar = (double) 0;
        Double percent = (double) 0;
        String resultado = "";

        //Verifica os Switchs marcados e adiciona uma fração para cada seleção
        if(preciso.isChecked()){
            percent = percent + 0.5;
        }else{}
        if(quero.isChecked()){
            percent = percent + 0.15;
        }else{}
        if(posso.isChecked()){
            percent = percent + 0.35;
        }else{}

        //executa o calculo de acordo com o percent carregado dos Switchs
        pagar = pagar + (valor * percent);

        //converte o pagar para String e troca o (.) por (,) em seguida carrega no resultado a String
        resultado = pagar.toString().replace(".",",");

        //Chama um AlertDialog altera titulo e Mensagem e botão OK
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Voce Decide");
        alerta.setMessage("Você deveria pagar R$ "+resultado);
        alerta.setPositiveButton("OK",null);
        alerta.create().show();
    }
}