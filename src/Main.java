import java.util.ArrayList;
import java.util.Scanner;

import com.Item.Device;
import com.Item.Teacher;
import com.Operations.Parser;
import com.Operations.Updater;


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
        ArrayList<Device> listItem = new ArrayList<>();
        ArrayList<Teacher> teacher_users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Parser inspector = new Parser();
        boolean exit = false;
        String path = "C:/Users/adrian/Documents/Practicas Programacion/JAVA/EjercicioB/src/resources/inventario.txt";

        Updater.chargeFile(listItem, teacher_users, path);
        printForTeacher(listItem, teacher_users);
        do {
            System.out.println("1. Agregar Nuevo Dispositivo");
            System.out.println("2. Listar Dispositivos\n");
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
                        System.out.println("LISTA DE EQUIPOS\n\n");
                        for (int i = 0; i < listItem.size(); ++i) {
                            System.out.println("Profesor: " + listItem.get(i).getIdTeacher());

                            for (int j = 0; j < listItem.size(); ++j) {
                                if (listItem.get(j).getIdTeacher() == listItem.get(i).getIdTeacher()) {
                                    System.out.println("    Descripción: "+ listItem.get(j).getDescription());
                                    System.out.println("    Cantidad: " + listItem.get(j).getAmount());
                                    System.out.println("    Precio: " + listItem.get(j).getPrice());
                                    System.out.println("    Factura: " + listItem.get(j).getInvoice());
                                    System.out.println("    Fecha: " + listItem.get(j).getDate() + "\n");
                                }
                            }
                        }
                        System.out.print("\n\n");
                    }
                    break;
                default:
                    System.out.println("Opción Inválida, los valores no son los correctos");
                    break;
            }
        } while(!exit);

        sc.close();
    }    
}