package com.Operations;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.Item.Device;
import com.Item.Teacher;

public class Updater {
    public static void addDevice(ArrayList<Device> list, String[] input) {
        Parser inspector = new Parser();
        Device item = new Device();

        if (input[0] != null) item.setDescription(input[0]);
        else {
            System.out.println("Error en la descripción");
            return;
        }
        
        try {
            item.setAmount(Integer.parseInt(input[1]));
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir la cantidad");
            return;
        }

        try {
            item.setPrice(Float.parseFloat(input[2]));
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el precio");
            return;
        }

        int[] date = inspector.parseDate(input[3]);
        if (date == null) {
            System.out.println("Error al convertir la fecha");
            return;
        }
        item.setDate(date);

        if (input[4] != null) item.setInvoice(input[4]);
        else {
            System.out.println("Error en la factura");
            return;
        }
        
        if (input[5] != null) item.setIdTeacher(input[5]);
        else {
            System.out.println("Error en la identidad el maestro");
            return;
        }

        list.add(item);
        return;
    }

    private static int findTeacher(ArrayList<Teacher> list, String id) {
        int position = 0;
        for (Teacher user : list) {
            if (id.equals(user.getId())) 
                return position;
            else ++position;
        }
        return -1;
    }

    private static void organizeUsers(ArrayList<Device> item_list, ArrayList<Teacher> user_list) {
        String idTeacher = null;
        Teacher newTeacher = null;
        int positionTeacher = 0;
        for (int i = 0; i < item_list.size(); ++i) {
            idTeacher = item_list.get(i).getIdTeacher();
            positionTeacher = findTeacher(user_list, idTeacher);

            if (positionTeacher == -1) {
                newTeacher = new Teacher(idTeacher);
                newTeacher.subtracted.add(i);
                user_list.add(newTeacher);
            } else {
                user_list.get(positionTeacher).subtracted.add(i);
            }
        }
    }

    public static void chargeFile(ArrayList<Device> devices, ArrayList<Teacher> users, String path) {
        File file = new File(path);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("El archivo '" + path + "' no existe. Creándolo...");
            try {
                // Crear el archivo
                if (file.createNewFile()) {
                    System.out.println("Archivo '" + path + "' creado correctamente.");
                } else {
                    System.out.println("Error al crear el archivo '" + path + "'.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // Leer el contenido del archivo (si existe)
            try (FileReader flie = new FileReader(path); BufferedReader buffer = new BufferedReader(flie)) {
                Parser inspector = new Parser();
                String input = null;

                while ((input = buffer.readLine()) != null) {
                    addDevice(devices, inspector.splitString(input));
                }

                organizeUsers(devices, users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    public static void UpdateFile(ArrayList<Device> list, String path) {
        try (FileWriter file = new FileWriter(path)) {
            for (int i = 0; i < list.size(); ++i) {
                file.write(list.get(i).exportDevice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
