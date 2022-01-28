package aasales_oop;

import java.util.ArrayList;

public class MobilePhone {
    private final String myNumber;
    private final ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContactName(contact.getName()) >= 0 && findContactNumber(contact.getPhoneNumber()) >= 0) {
            System.out.println("Contact is already on file.");
            return false;
        } else if (findContactNumber(contact.getPhoneNumber()) >= 0){
            System.out.println("This number is already registered for another contact.");
            return false;
        } else{
            myContacts.add(contact);
            return true;
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);
        if (foundPosition < 0) {
            System.out.println(oldContact.getName() + " was not found.");
            return false;
        } else if (findContactName(newContact.getName()) >= 0 && findContactNumber(newContact.getPhoneNumber()) >= 0){
            System.out.println("This contact is already on your list.");
            return false;
        } else if (findContactNumber(newContact.getPhoneNumber()) >= 0){
            System.out.println("This number is already registered for another contact.");
            return false;
        } else{
            this.myContacts.set(foundPosition, newContact);
            System.out.println(oldContact.getName() + " was replaced with " + newContact.getName()+".");
            return true;
        }
    }

    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition < 0) {
            System.out.println(contact.getName() + " was not found.");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contact.getName() + " was deleted.");
        return true;
    }

    private int findContact(Contact contact) {
        return this.myContacts.indexOf(contact);
    }

    private int findContactName(String contactName) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    private int findContactNumber(String phoneNumber) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    public String queryContactNumber(Contact contact) {
        if (findContactNumber(contact.getPhoneNumber()) >= 0) {
            return contact.getPhoneNumber();
        }
        return null;
    }

    public Contact queryContactName(String name) {
        int position = findContactName(name);
        if (position >= 0) {
            return this.myContacts.get(position);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List");
        if(this.myContacts.size()>0){
            for (int i = 0; i < this.myContacts.size(); i++) {
                System.out.println((i + 1) + "." +
                        this.myContacts.get(i).getName() + " -> " +
                        this.myContacts.get(i).getPhoneNumber());
            }
        } else{
            System.out.println("You have not saved any contacts just yet.");
        }
    }

    public String getMyNumber() {
        return myNumber;
    }
}