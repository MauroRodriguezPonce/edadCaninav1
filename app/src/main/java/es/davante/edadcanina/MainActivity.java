package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad

    private EditText ageEdit;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ageEdit = findViewById(R.id.age_input);
        resultText = findViewById(R.id.result_text);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Declaramos variables
        EditText ageEdit = findViewById(R.id.age_input);
        TextView resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);


        // Implementar el boton (setOnClickListener)
        button1.setOnClickListener(view -> calcularEdad()); {

            String ageString = ageEdit.getText().toString();
            int age = Integer.parseInt(ageString);
            int dogAge = age * FACTOR_CANINO;
            resultText.setText(getString(R.string.tu_edad_format, dogAge));
        };


    }

    private void calcularEdad(){

        // 1. Leer el texto quitando espacios
        String texto = ageEdit.getText().toString().trim();

        // 2. ¿Está vacío?
        if (texto.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        // 3. Convertir a int controlando el error
        int age;
        try {
            age = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        // 4. ¿Está en rango?
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        // 5. Todo correcto: quitar el error y mostrar el resultado
        ageEdit.setError(null);
        resultText.setText(getString(R.string.tu_edad_format, age * FACTOR_CANINO));
    }

    private void mostrarError(String mensaje) {
        ageEdit.setError(mensaje);   // 1. Muestra el mensaje en el EditText
        ageEdit.requestFocus();      // 2. Pone el foco en el EditText
        resultText.setText("");      // 3. Borra el resultado anterior
    }
}