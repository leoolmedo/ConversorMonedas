
package conversor;

//importo la clase de la interfaz gráfica
import conversor.gui.ConversorGUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;
import javax.swing.JButton;

/**
 *
 * @author leonardo
 */
public class Conversor {
    
    static String tipoConversion="moneda";
    
    public static void main(String[] args) {
        
        
        
        /* en el array monedas, se colocan los nombres de las monedas
        y en el array monedas van las cotizaciones de las monedas del array anterior con respecto al dolar
        es decir si con 100 pesos se compra 1 dolar, se debe colocar 100 */
        String monedas[]= {"USD - US Dolar","AR$ - Peso argentino", "Euro", "Libra Exterlina", "CH$ - Peso chileno", "CO$ - peso colombiano", "MX$ - peso mexicano", "UY$ - Peso Uruguayo", "Real Brasil", "JPY - Yen japones","KWR - Won Coreano" };
        double cambios[]= {1, 205.8, 0.931099, 0.8224, 801.98, 4774.866, 18.8749, 39.2752, 5.2448, 0.0075, 0.00077};
        
        //se aplica el mismo concepto para las longitudes
        String longitudes[] = {"kilometro","metro", "decímetro", "centímetro", "milímetro","pulgada", "Pie", "yarda", "milla", "milla náutica"};
        double equivalencia[]= {.001, 1, 10, 100, 1000, 39.3701, 3.28084, 1.094, 0.000621504, 1852};
        
        String temperaturas[] = {"Centígrados", "Kelvin", "Fahrenheit"};
        
        
        //creo el objeto para manejar la interfax grafica
        ConversorGUI GUI = new ConversorGUI();
        GUI.setLocationRelativeTo(null);
        GUI.setTitle("Conversor de magnitudes");
        GUI.setVisible(true);
        
        //cargo los valores para los combobox (moneda predeterminado)
        GUI.resetGUI("Conversor de monedas", "Moneda: ", monedas, "", "1");
        
        //genero las acciones de los botones
        ActionListener accionMoneda = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI.resetGUI("Conversor de monedas", "Moneda: ", monedas, "", "1");
                tipoConversion= "moneda";
            }
        };
        
                
        ActionListener accionLongitud = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI.resetGUI("Conversor de longitudes", "Unidad:", longitudes, "", "1");
                tipoConversion= "longitud";
            }
        };
        
        ActionListener accionTemperatura = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI.resetGUI("Conversor de unidades de temperatura", "Unidad:", temperaturas, "","1");
                tipoConversion= "temperatura";
            }
        };
        
        ActionListener accionConvertir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                double monto=0,cambio=0;
                GUI.alerta("");
                try {
                    //convierto el monto a double
                    monto = Double.parseDouble(GUI.getTxtMonto());
                }
                catch (Exception e){
                    
                    GUI.alerta("El valor debe ser numérico.");
                    GUI.setTxtMonto("1");
                    System.out.println(e.getMessage());
                }
                int indiceOrigen = GUI.getTxtUnidadOrigen();
                int indiceDestino = GUI.getTxtUnidadDestino();
                switch (tipoConversion){
                    case "moneda":
                        cambio = monto/cambios[indiceOrigen]*cambios[indiceDestino];
                    break;
                    
                    case "longitud":
                        cambio = monto/equivalencia[indiceOrigen]*equivalencia[indiceDestino];
                    break;
                    
                    case "temperatura":
                        System.out.println("Temperatura");
                        switch (indiceOrigen){
                            case 0:                         //centígrados
                               cambio = monto;              //ya está en centígrados no hago nada 
                            break;
                            case 1:                         //kelvin
                                cambio = monto - 273.15;    //lo convierto a centígrados
                            break;
                            case 2:         //fahrenheit
                                cambio = ((monto-32)*5)/9;
                            break;
                        }
                        
                        switch (indiceDestino){
                            case 0:                         //centígrados
                                                            //ya está en centígrados no hago nada 
                            break;
                            case 1:                         //kelvin
                                cambio += 273.15;           //lo convierto a centígrados
                            break;
                            case 2:                         //fahrenheit
                                cambio = ((cambio/5)*9)+32;
                            break;
                        }
                    break;
                }
                GUI.setTxtCambio(String.format("%.3f",cambio));
            }
        };
        
        // Asigno las acciones a los botones correspondientes
        GUI.setAccionMoneda(accionMoneda);
        GUI.setAccionLongitud(accionLongitud);
        GUI.setAccionTemperatura(accionTemperatura);
        GUI.setAccionConvertir(accionConvertir);
        
        

    }
    
}
