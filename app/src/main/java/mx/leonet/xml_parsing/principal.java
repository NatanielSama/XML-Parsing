package mx.leonet.xml_parsing;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class principal extends ListActivity {

    // All static variables
    static final String URL = "http://app.italtamirano.edu.mx/bbdd.xml";
    // XML node keys
    static final String KEY_ELEMENTO = "elemento"; // nodo padre
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
        setContentView(R.layout.activity_principal);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_ELEMENTO);

        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, "ID: " + parser.getValue(e, KEY_ID));
            map.put(KEY_USUARIO, "Nombre del usuario: " + parser.getValue(e, KEY_USUARIO));
            map.put(KEY_PASSWORD, "Password: " + parser.getValue(e, KEY_PASSWORD));
            map.put(KEY_NOMBRE, "Nombre: " + parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_APELLIDOP, "Apellido Paterno: " + parser.getValue(e, KEY_APELLIDOP));
            map.put(KEY_APELLIDOM, "Apellido Materno: " + parser.getValue(e, KEY_APELLIDOM));
            map.put(KEY_EMAIL, "Email: " + parser.getValue(e, KEY_EMAIL));

            // adding HashList to ArrayList
            menuItems.add(map);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.lista_usuarios,
                new String[] { KEY_ID, KEY_USUARIO }, new int[] {
                R.id.txt_v_idusuario, R.id.txt_v_usuario });

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String idusuario = ((TextView) view.findViewById(R.id.txt_v_idusuario)).getText().toString();
                String nombre = ((TextView) view.findViewById(R.id.txt_v_usuario)).getText().toString();
                //String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), vista_individual.class);
                in.putExtra(KEY_ID, idusuario);
                in.putExtra(KEY_USUARIO, nombre);
                //in.putExtra(KEY_DESC, description);
                startActivity(in);

            }
        });
    }


}
