package com.Item;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.Operations.*;

public class FileReport {
    public ArrayList<Device> deviceList;
    public ArrayList<Teacher> userList;
    private String path;

    public FileReport(String url) {
        deviceList = new ArrayList<>();
        userList = new ArrayList<>();
        path = url;
    }

    public void chargeFile() {
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
                String input = null;

                while ((input = buffer.readLine()) != null) {
                    Validator.addDevice(this, Parser.splitString(input));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    public void UpdateFile() {
        try (FileWriter file = new FileWriter(path)) {
            for (int i = 0; i < deviceList.size(); ++i) {
                file.write(deviceList.get(i).exportDevice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
