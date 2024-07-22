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
    public static boolean validateDescriptionField(String input) {
        if (input.isBlank()) {
            System.out.println("ERROR::NO PUEDES CARGAR UNA DESCRIPCIÓN VACÍA::ERROR");
            return false;
        }
        return true;
    }

    public static int validateAmountField(String input) {
        if (input.isBlank()) {
            System.out.println("ERROR::NO PUEDES, EL CAMPO DE CANTIDAD VACÍA::ERROR");
            return -1;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("ERROR::EL VALOR INTRODUCIDO EN EL CAMPO NO CANTIDAD NO ES UN NÚMERO::ERROR");
            return -1;
        }
    }

    public static float validatePriceField(String input) {
        if (input.isBlank()) {
            System.out.println("ERROR::NO PUEDES EL CAMPO DE PRECIO ESTA VACÍO::ERROR");
            return -1;
        }

        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            System.out.println("ERROR::EL VALOR INTRODUCIDO EN EL CAMPO NO PRECIO NO ES UN NÚMERO::ERROR");
            return -1;
        }
    }

    public static int[] validateDateField(String input) {
        int[] date = Parser.parseDate(input);
        if (date == null) {
            System.out.println("ERROR::NO SE PUDO CONVERTIR LA FECHA::ERROR");
            return null;
        }
        return date;
    }

    public static boolean validateInvoiceField(String input) {
        if (input.isBlank()) {
            System.out.println("ERROR::NO PUEDES CARGAR UN NUMERO DE FACTURA VACÍA::ERROR");
            return false;
        }
        return true;
    }

    public static boolean validateIdTeacherField(String input) {
        if (input.isBlank()) {
            System.out.println("ERROR::NO PUEDES CARGAR UNA CEDULA VACÍA::ERROR");
            return false;
        }
        return true;
    }

    private static void organizeUsers(ArrayList<Device> deviceList, ArrayList<Teacher> userList) {
        String idTeacher = null;
        Teacher newTeacher = null;
        int positionTeacher = 0;
        for (int i = 0; i < deviceList.size(); ++i) {
            idTeacher = deviceList.get(i).getIdTeacher();
            positionTeacher = findTeacher(userList, idTeacher);

            if (positionTeacher == -1) {
                newTeacher = new Teacher(idTeacher);
                newTeacher.associateDevice(i);;
                userList.add(newTeacher);
            } else {
                userList.get(positionTeacher).associateDevice(i);;
            }
        }
    }

    public static void addDevice(ArrayList<Device> deviceList, ArrayList<Teacher> userList, String[] input) {
        Device item = new Device();

        if (validateDescriptionField(input[0])) {
            item.setDescription(input[0]);

            int amount = validateAmountField(input[1]);

            if (amount != -1) {
                item.setAmount(amount);

                float price = validatePriceField(input[2]);
                
                if (price != -1) {
                    item.setPrice(price);

                    int[] date = validateDateField(input[3]);

                    if (date != null) {
                        item.setDate(date);

                        if (validateInvoiceField(input[4])) {
                            item.setInvoice(input[4]);

                            if (validateIdTeacherField(input[5])) {
                                item.setIdTeacher(input[5]);
                                deviceList.add(item);
                                organizeUsers(deviceList, userList);
                                System.out.println("MESSAGE::Equipo agregado correctamente::MESSAGE");
                            }
                        }
                    }
                }
            }
        }
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

    public static void chargeFile(ArrayList<Device> deviceList, ArrayList<Teacher> userList, String path) {
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
                    addDevice(deviceList, userList, Parser.splitString(input));
                }

                organizeUsers(deviceList, userList);
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