package com.Controller;

import java.util.ArrayList;

import com.Model.Device;
import com.Model.FileReport;
import com.Model.Teacher;

public class Validator {
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

    private static void organizeUsers(FileReport file) {
        String idTeacher = null;
        Teacher newTeacher = null;
        int positionTeacher = 0;
        for (int i = 0; i < file.deviceList.size(); ++i) {
            idTeacher = file.deviceList.get(i).getIdTeacher();
            positionTeacher = findTeacher(file.userList, idTeacher);

            if (positionTeacher == -1) {
                newTeacher = new Teacher(idTeacher);
                newTeacher.associateDevice(i);;
                file.userList.add(newTeacher);
            } else {
                file.userList.get(positionTeacher).associateDevice(i);;
            }
        }
    }

    public static void addDevice(FileReport file, String[] input) {
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
                                file.deviceList.add(item);
                                organizeUsers(file);
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
}