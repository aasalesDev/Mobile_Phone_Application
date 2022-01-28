package aasales_oop;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MobilePhone mobilePhone = new MobilePhone("00 55 12 1234 56789");

    public static void main(String[] args) {

        boolean exit = false;
        int action;
        startPhone();

        while (!exit) {
            printActions();
            System.out.println("\nType your option number: ");
            try {
                action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 0:
                        System.out.println("\nShutting the phone down...");
                        System.out.println("Bye!");
                        exit = true;
                        break;
                    case 1:
                        mobilePhone.printContacts();
                        break;
                    case 2:
                        addNewContact();
                        break;
                    case 3:
                        updateContact();
                        break;
                    case 4:
                        removeContact();
                        break;
                    case 5:
                        queryContact();
                        break;
                    case 6:
                        checkMyNumber();
                        break;
                    case 7:
                        queryContactNumber();
                        break;
                    case 8:
                        printActions();
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                }
            } catch (Exception exception) {
                System.out.println("Please enter a number from 0 to 8.");
                scanner.next();
            }
        }
    }

    private static void checkMyNumber() {
        System.out.println("This mobile phone's number is " + mobilePhone.getMyNumber());
    }

    private static void addNewContact() {
        System.out.println("Enter a new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter its phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println("A new contact has been added=> Name: " + name + ". Phone Number: " + phone);
        } else {
            System.out.println("==================================");
        }
    }

    private static void updateContact() {
        System.out.println("Enter the contact name you want to update: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContactName(name);
        if (existingContactRecord == null) {
            System.out.println(name + " was not found on file.");
            return;
        }
        System.out.print("Enter the new contact name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter the new contact phone number: ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("The record was updated successfully.");
        } else {
            System.out.println("Update was unsuccessful.");
        }
    }

    private static void removeContact() {
        System.out.println("Enter an existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContactName(name);
        if (existingContactRecord == null) {
            System.out.println(name + " was not found on file.");
            return;
        }
        if (mobilePhone.removeContact(existingContactRecord)) {
            System.out.println(name + " has been successfully deleted.");
        } else {
            System.out.println("It was not possible to delete " + name + ". Please try again.");
        }
    }

    private static void queryContact() {
        System.out.println("Enter the contact name you are looking for: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContactName(name);
        if (existingContactRecord == null) {
            System.out.println(name + " could not be found. Please check your entry.");
            return;
        }
        System.out.println(existingContactRecord.getName() + "'s phone number is " + existingContactRecord.getPhoneNumber()+".");
    }

    private static void queryContactNumber(){
        System.out.println("Whose number are you looking for?");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContactName(name);
        if (existingContactRecord == null) {
            System.out.println(name + " could not be found. Please check your entry.");
        } else{
            System.out.println(name + "'s phone number is " + mobilePhone.queryContactNumber(mobilePhone.queryContactName(name)));
        }
    }

    private static void startPhone() {
        System.out.println("Powering Phone on...");
        System.out.println("Welcome!");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions:\nPlease press");
        System.out.println("0  - to shutdown\n" +
                "1  - to print contacts\n" +
                "2  - to add a new contact\n" +
                "3  - to update an existing contact\n" +
                "4  - to remove an existing contact\n" +
                "5  - to verify whether a contact exists\n" +
                "6  - to check this mobile phone number\n" +
                "7  - to check a contact's phone number\n" +
                "8  - to print the list of available actions.");
        System.out.println("Choose your action: ");
    }
}