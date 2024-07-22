package com.Interface;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Item.*;
import com.Operations.*;

public class GraphicMenu extends JFrame {
    private JPanel panelFormInfo;
    private JPanel panelFormTextFields;
    private JPanel panelButtons;
    
    private JLabel formInfoLabel;
    private JLabel descriptionLabel;
    private JLabel amountLabel;
    private JLabel priceLabel;
    private JLabel dateLabel;
    private JLabel dateFormatLabel;
    private JLabel invoiceLabel;
    private JLabel teacherIdLabel;

    private JTextField amountTextField;
    private JTextField dateTextField;
    private JTextField descriptionTextField;
    private JTextField invoiceTextField;
    private JTextField priceTextField;
    private JTextField teacherIdTextField;
    
    private JButton addDeviceButton;
    private JButton reportButton;
    private JButton exitButton;

    public GraphicMenu(FileReport file) {
        initComponents();
        buildMenu();
        listeners(file);
    }
                     
    private void initComponents() {
        panelFormInfo = new JPanel();
        panelFormTextFields = new JPanel();
        panelButtons = new JPanel();

        formInfoLabel = new JLabel("Ingrese data del equipo:");
        descriptionLabel = new JLabel("Descripción:");
        amountLabel = new JLabel("Cantidad:");
        priceLabel = new JLabel("Costo Unitario (Bs):");
        dateLabel = new JLabel("Fecha de adqusición:");
        dateFormatLabel = new JLabel("dd/mm/aaaa");
        invoiceLabel = new JLabel("Nro de Factura:");
        teacherIdLabel = new JLabel("C.I. del Responsable del equipo:");

        descriptionTextField = new JTextField();
        amountTextField = new JTextField();
        priceTextField = new JTextField();
        dateTextField = new JTextField();
        invoiceTextField = new JTextField();
        teacherIdTextField = new JTextField();

        addDeviceButton = new JButton("Registrar data");
        reportButton = new JButton("Generar Reporte");
        exitButton = new JButton("Salir");

    } 
    
    private void buildMenu() {
        setTitle("Registro y Control de Equipos en Centro de Investigación");
        setPreferredSize(new Dimension(500, 275));
        setResizable(false);

        GroupLayout panelFormInfoLayout = new GroupLayout(panelFormInfo);

        panelFormInfo.setLayout(panelFormInfoLayout);
        
        panelFormInfoLayout.setHorizontalGroup(
            panelFormInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formInfoLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFormInfoLayout.setVerticalGroup(
            panelFormInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formInfoLabel)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        GroupLayout panelFormTextFieldsLayout = new GroupLayout(panelFormTextFields);

        panelFormTextFields.setLayout(panelFormTextFieldsLayout);

        panelFormTextFieldsLayout.setHorizontalGroup(
            panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(amountLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionTextField)
                    .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                        .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(invoiceLabel)
                            .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                                .addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(priceLabel))
                            .addComponent(teacherIdLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(priceTextField)
                            .addComponent(invoiceTextField)
                            .addComponent(teacherIdTextField, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))))
            .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel)
                    .addComponent(dateFormatLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelFormTextFieldsLayout.setVerticalGroup(
            panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel)
                    .addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                        .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoiceLabel)
                            .addComponent(invoiceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormTextFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(teacherIdLabel)
                            .addComponent(teacherIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFormTextFieldsLayout.createSequentialGroup()
                        .addComponent(dateLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFormatLabel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout panelButtonsLayout = new GroupLayout(panelButtons);

        panelButtons.setLayout(panelButtonsLayout);

        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addComponent(addDeviceButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );

        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(reportButton)
                .addComponent(addDeviceButton)
                .addComponent(exitButton))
        );

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelFormInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelFormTextFields, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtons, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormTextFields, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pack();
    }

    private void listeners(FileReport file) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addDeviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addNewDevice(evt, file);
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                printForTeacher(file);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
    }

    private void clearTextFields() {
        descriptionTextField.setText("");
        amountTextField.setText("");
        priceTextField.setText("");
        dateTextField.setText("");
        invoiceTextField.setText("");
        teacherIdTextField.setText("");
    }

    private void addNewDevice(ActionEvent event, FileReport file) {
        String[] textFields = new String[] {
            descriptionTextField.getText(),
            amountTextField.getText(),
            priceTextField.getText(),
            dateTextField.getText(),
            invoiceTextField.getText(),
            teacherIdTextField.getText()
        };

        Validator.addDevice(file, textFields);
        file.UpdateFile();
        clearTextFields();
    }

    private void printForTeacher(FileReport file) {
        for(Teacher user : file.userList) {
            System.out.println("Teacher: " + user.getId());
            for (int idDevice : user.getListDevice()) {
                System.out.println("    Descripción: "+ file.deviceList.get(idDevice).getDescription());
                System.out.println("    Cantidad: " + file.deviceList.get(idDevice).getAmount());
                System.out.println("    Precio: " + file.deviceList.get(idDevice).getPrice());
                System.out.println("    Factura: " + file.deviceList.get(idDevice).getInvoice());
                System.out.println("    Fecha: " + file.deviceList.get(idDevice).getDate() + "\n");
            }
        }
    }

    public void init(FileReport file) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicMenu(file).setVisible(true);
            }
        });
    }                
}