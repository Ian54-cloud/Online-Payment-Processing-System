import Exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Payment payment;
        System.out.println("Please, select the payment method. Select 1 by credit card or 2 by blik");
        int option = sc.nextInt();
        sc.nextLine();
            switch (option) {
                case 1:
                    payment = new CreditCardPayment();
                    if (payment instanceof CreditCardPayment) {

                        CreditCardPayment cd = (CreditCardPayment) payment;
                        try {
                            System.out.println("write your credit card number:");
                            String user_credit_card_number = sc.nextLine();

                            cd.setCardnumber(user_credit_card_number);

                            StringBuilder formatted = new StringBuilder();
                            for (int i = 0; i < user_credit_card_number.length(); i++) {
                                formatted.append(user_credit_card_number.charAt(i));

                                // Add a space after every 4th digit, but not after the last group of 4

                                if ((i + 1) % 4 == 0 && i != user_credit_card_number.length() - 1) {
                                    formatted.append(" ");

                                }

                            }
                            System.out.println("card number:" + formatted);
                        }catch (IncorretCardNumberException e){
                            System.out.println(e.getMessage());
                        }
                        try {
                            System.out.println("Who is the account holder?");
                            String account_holder = sc.nextLine();
                            cd.setAccountholder(account_holder);
                        } catch (EmptyAccountHolder e) {
                            System.out.println(e.getMessage());
                        }
                        try {
                            System.out.println("write the cvv of your card");
                            String cvv = sc.nextLine();
                            cd.setCvv(cvv);
                        } catch (IncorrectCVVException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("write the expiry date of your card");
                        String expiry_date = sc.nextLine();
                        cd.setExpiry_date(expiry_date);

                        System.out.println("how much do you want to pay?");
                        double amount = sc.nextDouble();
                        cd.pay(amount);
                        cd.finalizing_payment();
                        try (FileWriter fw = new FileWriter("Credit_Card.txt", true)) {
                            LocalDateTime dt = LocalDateTime.now();
                            fw.write("Card Number:"+cd.getCardnumber() + " " + System.lineSeparator());
                            fw.write("Account Holder:"+cd.getAccountholder() + " " + System.lineSeparator());

                            fw.write("cvv:"+cd.getCvv() + " " + System.lineSeparator());

                            fw.write("Expiry date:"+cd.getExpiry_date() + "" + System.lineSeparator());
                            fw.write("-------");
fw.write("Payment made by credit card!");
                            fw.write("Payment made on:" + dt);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    break;
                case 2:

                        payment = new BlikPayment();
                        if (payment instanceof BlikPayment) {
                            BlikPayment blikPayment = (BlikPayment) payment;
                            System.out.println("Hello, insert the name of recipient:");
                            String recipient_name = sc.nextLine();
                            blikPayment.setRecipient_name(recipient_name);
                            System.out.println("write the phone number of the recipient:");
                            String recipient_number = sc.nextLine();
                            blikPayment.setPhone_number( recipient_number);
                            System.out.println("amount:");
                            double amount = sc.nextDouble();
                            sc.nextLine();
                            blikPayment.setAmount(amount);
                            System.out.println("description:");
                            String description = sc.nextLine();
                            blikPayment.setDescription(description);
                            try {
                                blikPayment.GenerateBlikCodeNumber();
                            } catch (UnmatchedBlikCodeException e) {
                                throw new RuntimeException(e);
                            }
                            try(FileWriter fr=new FileWriter("blik_receipt.txt", true)) {

                                fr.write("Name of the receiver:"+blikPayment.getSender_name()+System.lineSeparator());
                                String phone=String.valueOf(blikPayment.getPhone_number()+System.lineSeparator());
                                fr.write("Phone number of the receiver"+ blikPayment.getPhone_number()+System.lineSeparator());
                                //amount = String.valueOf(blikPayment.getAmount());
                                fr.write("Amount transferred:"+blikPayment.getAmount()+System.lineSeparator());
                                fr.write("Description:"+blikPayment.getDescription()+System.lineSeparator());
                                fr.write("Blik code:"+blikPayment.getBlik_code()+System.lineSeparator());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            blikPayment.pay(amount);

                        }
                        break;
                default:
                    System.out.println("incorrect payment method");

                    }

        }

            }





