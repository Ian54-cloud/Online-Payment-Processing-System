import Exceptions.UnmatchedBlikCodeException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;

public class BlikPayment implements Payment {

    private String blik_code;
    private String phone_number;
    private String recipient_name;
    private double amount;
    private String description;






    public void setBlik_code(String blik_code) {
        if (blik_code.length() == 6) {
            this.blik_code = blik_code;
        }
    }

    public String getBlik_code() {
        return blik_code;
    }

    public void setPhone_number(String phone_number) {
        if (phone_number.length() == 9) {
            this.phone_number = phone_number;
        }
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setRecipient_name(String recipient_name) {
        if (!recipient_name.isEmpty()) {
            this.recipient_name = recipient_name;
        }
    }

    public String getSender_name() {
        return recipient_name;
    }

    public void setAmount(double amount) {
        if (amount >= 1) {
            this.amount = amount;
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }



    public static final String GenerateBlik() {
        Random rnd = new Random();
        int blik_code_converted = rnd.nextInt(900000) + 100000;
        return String.valueOf(blik_code_converted);
    }

    //Generates a Blik code, writes it to file, and starts a 60s countdown
    public final void GenerateBlikCodeNumber() throws UnmatchedBlikCodeException {

        String blik_code = GenerateBlik(); // generate first code
        this.blik_code = blik_code;

        // Write code to file
        try (FileWriter writer = new FileWriter("blik.txt")) {
            writer.write(blik_code);
            System.out.println(" Your Blik code has been sent to blik.txt");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


            try {
                Scanner sc = new Scanner(System.in);
                int attempts = 5;
                while (attempts > 0) {
                    System.out.print("Enter your 6-digit Blik code which is on the file: ");
                    String blik_code_writtenbyuser = sc.nextLine();

                    if (blik_code_writtenbyuser.equals(blik_code)) {
                        System.out.println("Blik code accepted!");

                    } else {
                        attempts--;
                        throw new UnmatchedBlikCodeException("the blik code which you inserted isn't the same one as on the file");

                    }
                }
                System.out.println("Transaction cancelled!");
            } catch (UnmatchedBlikCodeException ub) {
                System.out.println(ub.getMessage());
            };




        // Countdown timer (runs every second)
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int count = 60;

            @Override
            public void run() {
                String newCode = GenerateBlik();
               String blik_code = newCode;
                try (FileWriter writer = new FileWriter("blik.txt")) {
                    writer.write(newCode);
                    System.out.println("Blik code expired! New code generated in blik.txt");
                } catch (IOException e) {
                    System.out.println("Failed to regenerate BLIK code!");
                }
                        timer.cancel(); // stop timer
                    }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        Scanner sc = new Scanner(System.in);
        int attempts = 5;

        while (attempts > 0) {
            System.out.print("Enter your 6-digit Blik code: ");
            String input = sc.nextLine();

            if (input.equals(blik_code)) {
                System.out.println("Blik code accepted!");
                timer.cancel();
                return;
            } else {
                attempts--;
                System.out.println("Incorrect code. Attempts left: " + attempts);
            }
        }
    }

    @Override
    public void pay(double amount) {
        try {

            System.out.println("checking the Sender's name..." + getSender_name());
            Thread.sleep(3000);
            System.out.println("checking the Sender's name..." + getSender_name());
            Thread.sleep(3000);
            System.out.println("checking the inserted amount..."+getAmount());
            Thread.sleep(3000);
            System.out.println("checking the phone number inserted..."+getPhone_number());
            Thread.sleep(3000);
            System.out.println("checkin the description..."+getDescription());
            Thread.sleep(5000);
            System.out.println("approved payment!");
            Thread.sleep(2000);
            System.out.println("Please check your blik receipt file");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}







