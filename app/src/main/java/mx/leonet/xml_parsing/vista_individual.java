package mx.leonet.xml_parsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class vista_individual extends Activity {

    static final String KEY_ID = "id_usuario";
    static final String KEY_USUARIO = "usuario";
    static final String KEY_PASSWORD = "password";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDOP = "apellidop";
    static final String KEY_APELLIDOM = "apellidom";
    static final String KEY_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent
        String id = in.getStringExtra(KEY_ID);
        String usuario = in.getStringExtra(KEY_USUARIO);

        // Displaying all values on the screen
        TextView lblid = (TextView) findViewById(R.id.txt_idusuario);
        TextView lblusuario = (TextView) findViewById(R.id.txt_usuario);

        lblid.setText(id);
        lblusuario.setText(usuario);

    }
}
