package com.wlad.minutrade.view;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wlad.minutrade.R;
import com.wlad.minutrade.controller.DataBase;
import com.wlad.minutrade.model.Client;

public class MainActivity extends AppCompatActivity {

    EditText cpf, name, email, address, num1, num2;
    TextView cpfError, nameError, emailError, addError, numError;
    Spinner status;
    Button insert, clear;
    DataBase dataBase;
    boolean canInsert;

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBase = new DataBase(this);
        canInsert = true;

        cpf = (EditText) findViewById(R.id.editText_cpf);
        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        address = (EditText) findViewById(R.id.editText_address);
        num1 = (EditText) findViewById(R.id.editText_num1);
        num2 = (EditText) findViewById(R.id.editText_num2);
        status = (Spinner) findViewById(R.id.spinner_status);
        cpfError = (TextView) findViewById(R.id.textView_errorCPF);
        nameError = (TextView) findViewById(R.id.textView_errorName);
        emailError = (TextView) findViewById(R.id.textView_errorEmail);
        addError = (TextView) findViewById(R.id.textView_errorAdd);
        numError = (TextView) findViewById(R.id.textView_errorNum);
        insert = (Button) findViewById(R.id.button_insert);
        clear = (Button) findViewById(R.id.button_clear);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                canInsert = true;
                cpfError.setVisibility(View.GONE);
                nameError.setVisibility(View.GONE);
                emailError.setVisibility(View.GONE);
                addError.setVisibility(View.GONE);
                numError.setVisibility(View.GONE);

                Client client =  new Client();

                if(validCPF(cpf.getText().toString())){
                    client.setCPF("" + cpf.getText());
                }
                else{
                    cpfError.setVisibility(View.VISIBLE);
                    canInsert = false;
                }

                if(!name.getText().toString().equals("")){
                    client.setName("" + name.getText());
                }
                else{
                    nameError.setVisibility(View.VISIBLE);
                    canInsert = false;
                }

                if(!email.getText().toString().equals("")) {
                    client.setEmail("" + email.getText());
                }
                else{
                    emailError.setVisibility(View.VISIBLE);
                    canInsert = false;
                }

                if(!address.getText().toString().equals("")) {
                    client.setAddress("" + address.getText());
                }
                else{
                    addError.setVisibility(View.VISIBLE);
                    canInsert = false;
                }

                if(!num1.getText().toString().equals("")) {
                    client.setNumber1("" + num1.getText());
                }
                else {
                    numError.setVisibility(View.VISIBLE);
                    canInsert = false;
                }

                client.setNumber2("" + num2.getText());
                client.setMaritalStatus("" + status.getSelectedItem());


                if(canInsert) {

                    if(!dataBase.retrieve(client.getCPF())){

                        try {
                            dataBase.insert(client);

                            cpf.setText("");
                            name.setText("");
                            email.setText("");
                            address.setText("");
                            num1.setText("");
                            num2.setText("");
                            status.setSelection(0);

                            cpfError.setVisibility(View.GONE);
                            nameError.setVisibility(View.GONE);
                            emailError.setVisibility(View.GONE);
                            addError.setVisibility(View.GONE);
                            numError.setVisibility(View.GONE);


                            Toast.makeText(getApplicationContext(), "successful registration", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "failed registration", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "CPF is already registered", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf.setText("");
                name.setText("");
                email.setText("");
                address.setText("");
                num1.setText("");
                num2.setText("");
                status.setSelection(0);

                cpfError.setVisibility(View.GONE);
                nameError.setVisibility(View.GONE);
                emailError.setVisibility(View.GONE);
                addError.setVisibility(View.GONE);
                numError.setVisibility(View.GONE);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validCPF(String cpf){
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
            cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
            cpf.equals("99999999999") || (cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

}
