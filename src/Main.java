import com.Model.FileReport;
import com.View.GraphicMenu;


public class Main {

    public static void main(String[] args) {
        FileReport report = new FileReport("C:/Users/adrian/Documents/Practicas Programacion/JAVA/EjercicioB/src/resources/inventario.txt");
        GraphicMenu principalMenu = new GraphicMenu(report);

        report.chargeFile();
        principalMenu.init(report);
    }
}