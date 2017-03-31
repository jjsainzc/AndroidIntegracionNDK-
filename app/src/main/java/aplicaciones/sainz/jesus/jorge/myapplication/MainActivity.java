/*
 * Copyright (c) 2017 .Jorge Jesus Sainz Casalla
 * Ultima modificacion: 3/28/17 2:01 PM
 */

package aplicaciones.sainz.jesus.jorge.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;

import aplicaciones.sainz.jesus.jorge.myapplication.jnilibs.JNIFunciones;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AQuery aQuery;

    @BindView(R.id.pedido)
    EditText pedido;

    @BindView(R.id.resultado)
    EditText resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        aQuery = new AQuery(this);

        aQuery.id(R.id.etq_pedido).text(Html.fromHtml("<b>" + getResources().getString(R.string.lbl_pedido) + "</b>"));
        aQuery.id(R.id.sample_text).text(JNIFunciones.consulta());
        aQuery.id(R.id.pedir).text(R.string.lbl_pedir).clicked(this, "onPedirClick");
        aQuery.id(R.id.limpiar).text(R.string.lbl_limpiar).clicked(this, "onLimpiarClick");

        resultado.setKeyListener(null);

    }

    public void onPedirClick(View v) {
        try {
            resultado.setText(JNIFunciones.despacha(Float.parseFloat(pedido.getText().toString())));
        } catch (NumberFormatException e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void onLimpiarClick(View v) {
        pedido.setText("");
        resultado.setText("");
    }

}
