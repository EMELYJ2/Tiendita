package com.example.mitiendita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     EditText nombre,direccion,pedido;
     Button pedir;
    String clie, direc,pedii;
    RequestQueue requestQueue;
    String HttpUrl = "https://mitienditaa.000webhostapp.com/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.edtnombre);
        direccion =findViewById(R.id.edtdireccion);
        pedido = findViewById(R.id.edtpedido);
        pedir = findViewById(R.id.btnpedir);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // progressDialog.setMessage("Espera, Se esta insertando los datos en la base de datos");
                //progressDialog.show();
                valores();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // progressDialog.dismiss();
                                //;
                                if (!nombre.getText().toString().isEmpty()&&!direccion.getText().toString().isEmpty()&&!pedido.getText().toString().isEmpty()){
                                    Toast.makeText(MainActivity.this, "no has ingresado datos", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "envio realizado correctamente", Toast.LENGTH_SHORT).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // progressDialog.dismiss();
                                //
                                if (!nombre.getText().toString().isEmpty()&&!direccion.getText().toString().isEmpty()&&!pedido.getText().toString().isEmpty()){
                                    Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                                }
                                else{

                                    Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                                }


                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre",clie);
                        params.put("direccion",direc);
                        params.put("pedido",pedii);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
                nombre.setText("");
                direccion.setText("");
                pedido.setText("");
            }

        });

    }
    public void valores(){
        clie = nombre.getText().toString().trim();
        direc = direccion.getText().toString().trim();
        pedii = pedido.getText().toString().trim();
    }
}