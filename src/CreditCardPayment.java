import Exceptions.EmptyAccountHolder;
import Exceptions.IncorrectCVVException;
import Exceptions.IncorretCardNumberException;

import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
public class CreditCardPayment implements Payment {
    private String cardnumber;
    private String accountholder;
    private String cvv;
    private String expiry_date;

    /*public CreditCardPayment(String cardnumber, String accountholder, String cvv, String expiry_date) {
        this.cardnumber = cardnumber;
        this.accountholder = accountholder;
        this.cvv = cvv;
        this.expiry_date = expiry_date;
    }*/

    public void setCardnumber(String cardnumber) throws IncorretCardNumberException {
        if (cardnumber.length() == 16) {
            this.cardnumber = cardnumber;
        } else{
                throw new IncorretCardNumberException("The card number you inserted doesnt have 16 digits!");
            }
        }



    public String getCardnumber() {
        return cardnumber;
    }


    public void setAccountholder(String accountholder) throws EmptyAccountHolder {
        if (!accountholder.isEmpty()) {
            this.accountholder = accountholder;
        } else {
            throw new EmptyAccountHolder("the account holder is empty!");
        }
    }


    public String getAccountholder() {
        return accountholder;
    }

    public void setCvv(String cvv) {
        if (cvv.length() == 3) {
            this.cvv = cvv;
        } else {
            throw new IncorrectCVVException("the cvv you inserted is incorrect");
        }
    }

    public String getCvv() {
        return cvv;
    }

    public void setExpiry_date(String expiry_date) {
        if (expiry_date.contains("/")) {
            this.expiry_date = expiry_date;
        }
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    @Override
    public void pay(double amount) {
        try {

            System.out.println("checking the card number... **** **** ****" + getCardnumber().substring(cardnumber.length() - 4));//to get the last 4digits of the card
            Thread.sleep(3000);
            System.out.println("checking the account holder..." + getAccountholder().substring(0).toUpperCase());
            Thread.sleep(3000);
            System.out.println("checking the cvv..." + getCvv());
            Thread.sleep(3000);
            System.out.println("checking the expiry date..." + getExpiry_date());
            Thread.sleep(3000);

        } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        try {
            setCardnumber(getCardnumber());
            setAccountholder(getAccountholder());
            setCvv(getCvv());
            setExpiry_date(getExpiry_date());
        } catch (IncorretCardNumberException e) {
            System.out.println(e.getMessage());
        } catch (EmptyAccountHolder e) {
            System.out.println(e.getMessage());
        } catch (IncorrectCVVException e) {
            System.out.println(e.getMessage());
        }
    }


    //after running the above code, the program should hold for 5seconds and then print out these messages;
    public void finalizing_payment(){
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                System.out.println("payment made with success!");

            }
        };
        timer.schedule(timerTask, 5000);


    }
}

