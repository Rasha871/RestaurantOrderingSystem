/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantorderingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RestaurantOrderingSystem extends JFrame {

    String[] foodNames = {
        "Burger",
        "Pizza",
        "Pasta",
        "Sandwich",
        "Fried Rice"
    };

    double[] foodPrices = {
        500,
        1200,
        800,
        400,
        900
    };

    JComboBox<String> foodBox;
    JTextField quantityField, searchField;
    JTable table;
    DefaultTableModel model;
    JLabel totalLabel;

    double totalBill = 0;


    public RestaurantOrderingSystem() {

        setTitle("Restaurant Ordering System");
        setSize(700,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);


        JLabel title = new JLabel("RESTAURANT ORDERING SYSTEM");
        title.setBounds(200,20,300,30);
        title.setFont(new Font("Arial",Font.BOLD,14));
        panel.add(title);



        JLabel foodLabel = new JLabel("Food:");
        foodLabel.setBounds(50,80,100,25);
        panel.add(foodLabel);


        foodBox = new JComboBox<>(foodNames);
        foodBox.setBounds(150,80,150,25);
        panel.add(foodBox);



        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(50,120,100,25);
        panel.add(qtyLabel);


        quantityField = new JTextField();
        quantityField.setBounds(150,120,150,25);
        panel.add(quantityField);



        JButton addButton = new JButton("Add Order");
        addButton.setBounds(330,80,120,30);
        panel.add(addButton);



        JButton billButton = new JButton("Calculate Bill");
        billButton.setBounds(330,120,120,30);
        panel.add(billButton);



        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50,170,100,25);
        panel.add(searchLabel);



        searchField = new JTextField();
        searchField.setBounds(150,170,150,25);
        panel.add(searchField);



        JButton searchButton = new JButton("Search");
        searchButton.setBounds(330,170,120,30);
        panel.add(searchButton);



        JButton sortButton = new JButton("Sort Price");
        sortButton.setBounds(470,170,120,30);
        panel.add(sortButton);



        model = new DefaultTableModel();

        model.addColumn("Food");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");


        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50,230,550,200);
        panel.add(scroll);



        totalLabel = new JLabel("Total Bill: Rs. 0");
        totalLabel.setBounds(50,470,200,30);
        panel.add(totalLabel);



        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(450,470,100,30);
        panel.add(clearButton);



        add(panel);



        // ADD ORDER BUTTON
        addButton.addActionListener(e -> {

            int index = foodBox.getSelectedIndex();

            int qty = Integer.parseInt(quantityField.getText());

            double price = foodPrices[index];

            double total = price * qty;

            model.addRow(new Object[]{
                foodNames[index],
                qty,
                price,
                total
            });

            totalBill += total;

        });



        // CALCULATE BILL

        billButton.addActionListener(e -> {

            totalLabel.setText(
                "Total Bill: Rs. " + totalBill
            );

        });



        // SEARCH BUTTON

        searchButton.addActionListener(e -> {

            String search =
            searchField.getText();


            boolean found=false;


            for(int i=0;i<foodNames.length;i++){

                if(foodNames[i]
                .equalsIgnoreCase(search)){


                    JOptionPane.showMessageDialog(
                    null,
                    "Food Found: "
                    +foodNames[i]
                    +" Price Rs."
                    +foodPrices[i]
                    );


                    found=true;
                    break;
                }
            }


            if(!found){

                JOptionPane.showMessageDialog(
                null,
                "Food Not Found"
                );

            }

        });



        // SORT BUTTON

        sortButton.addActionListener(e -> {


            for(int i=0;i<foodPrices.length-1;i++){

                for(int j=0;j<foodPrices.length-i-1;j++){


                    if(foodPrices[j]>foodPrices[j+1]){


                        double temp=foodPrices[j];
                        foodPrices[j]=foodPrices[j+1];
                        foodPrices[j+1]=temp;


                        String tempName=foodNames[j];
                        foodNames[j]=foodNames[j+1];
                        foodNames[j+1]=tempName;

                    }

                }
            }


            foodBox.removeAllItems();

            for(String food:foodNames){

                foodBox.addItem(food);

            }


            JOptionPane.showMessageDialog(
            null,
            "Food Sorted Successfully");

        });



        // CLEAR BUTTON

        clearButton.addActionListener(e -> {

            model.setRowCount(0);
            totalBill=0;
            totalLabel.setText(
            "Total Bill: Rs. 0");

        });


    }



    public static void main(String args[]) {

        new RestaurantOrderingSystem()
        .setVisible(true);

    }

}