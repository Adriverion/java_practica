import java.util.ArrayList;
import java.util.Scanner;

import com.Item.Device;
import com.Item.Teacher;
import com.Operations.Parser;
import com.Operations.Updater;
import com.Interface.GraphicMenu;


public class Main {

    public static void printForTeacher(ArrayList<Device> devices, ArrayList<Teacher> users) {
        for(Teacher user : users) {
            System.out.println("Teacher: " + user.getId());
            for (int idDevice : user.subtracted) {
                System.out.println("    Descripción: "+ devices.get(idDevice).getDescription());
                System.out.println("    Cantidad: " + devices.get(idDevice).getAmount());
                System.out.println("    Precio: " + devices.get(idDevice).getPrice());
                System.out.println("    Factura: " + devices.get(idDevice).getInvoice());
                System.out.println("    Fecha: " + devices.get(idDevice).getDate() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        GraphicMenu experimentalFrame = new GraphicMenu();
        ArrayList<Device> listItem = new ArrayList<>();
        ArrayList<Teacher> teacher_users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Parser inspector = new Parser();
        boolean exit = false;
        String path = "C:/Users/adrian/Documents/Practicas Programacion/JAVA/EjercicioB/src/resources/inventario.txt";

        Updater.chargeFile(listItem, teacher_users, path);

        do {
            System.out.println("1. Agregar Nuevo Dispositivo");
            System.out.println("2. Listar Dispositivos\n");
            System.out.println("3. Iniciar ventana Gráfica (Experimental)");
            System.out.println("0. Salir del Programa");
            int select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 0:
                    System.out.println("Programa terminado");
                    exit = true;
                    break;
                case 1:
                    System.out.println("Ingrese los el nuevo dispositivo:");
                    System.out.println("desc#ct#mu#\"dd\"/\"mm\"/\"aaaa\"#nf#ci");
                    String input = sc.nextLine();
                    if (!inspector.valPattern(input, "^(\\w||\\s)+#\\d+#\\d+.\\d+#\\d+\\/\\d+\\/\\d+#\\w+#\\w+$")) {
                        System.out.println("Error el patron no coincide con el requerido");
                        break;
                    }

                    Updater.addDevice(listItem, inspector.splitString(input));
                    Updater.UpdateFile(listItem, path);
                    break;
                case 2:
                    if (listItem.isEmpty()) {
                        System.out.println("La lista está vacía");
                    } else {
                        System.out.println("LISTA DE EQUIPOS\n");
                        printForTeacher(listItem, teacher_users);
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Advertencia, la opción es experimental, no se encuentra en funcionamiento");
                    experimentalFrame.init();
                    break;
                default:
                    System.out.println("Opción Inválida, los valores no son los correctos");
                    break;
            }
        } while(!exit);

        sc.close();
    }    
}