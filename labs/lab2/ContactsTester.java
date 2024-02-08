package lab2;

import java.security.cert.CertPath;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

class Faculty{
    String name;
    Student [] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }
    public int countStudentsFromCity(String cityName){
        return (int) Arrays.stream(students).filter(i->i.city.equals(cityName)).count();
    }
    public Student getStudent(long index){
        for(Student s : students){
            if(s.getIndex()==index)
                return s;
        }
        return null;
    }
    public double getAverageNumberOfContacts(){
        int sum=0;
        for(Student s : students){
            sum+=s.getCurrent();
        }
        return (double) sum/students.length;
    }
    public Student getStudentWithMostContacts(){
        Student maxContacts=students[0];
        for(int i=1;i<students.length;i++){
            if(maxContacts.getCurrent()<students[i].getCurrent())
                maxContacts=students[i];
            else if (maxContacts.getCurrent()==students[i].getCurrent() && maxContacts.getIndex()<students[i].getIndex())
                maxContacts=students[i];
        }
        return maxContacts;
    }

    @Override
    public String toString() {
        return "{\"fakultet\":\"" +  name +  "\""+
                ", \"studenti\":" + Arrays.toString(students) +
                '}';
    }
}
class Student {
    private Contact[] contacts;
    int maxContacts=10;
    int numOfEmailContacts=0;
    int numOfPhoneContacts=0;
    int current=0;
    String firstName;
    String lastName;
    String city;
    int age;
    long index;

    public int getCurrent() {
        return current;
    }

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.contacts = new Contact[10];
    }

    public void addEmailContact(String date, String email) {
        numOfEmailContacts++;
        increaseContactSize();
        contacts[current++]=new EmailContact(date,email);
    }

    public void addPhoneContact(String date, String phone) {
        numOfPhoneContacts++;
        increaseContactSize();
        contacts[current++]=new PhoneContact(date, phone);
    }
    private void increaseContactSize(){
        if(current==maxContacts){
            Contact[] temp=new Contact[maxContacts*2];
            for(int i=0;i<maxContacts;i++){
                temp[i]=contacts[i];
            }
            contacts=temp;
            maxContacts*=2;
        }
    }

    public Contact[] getEmailContacts() {
        Contact[] emailContacts=new Contact[numOfEmailContacts];
        for(int i=0,k=0;i<current;i++){
            if(contacts[i].getType().equals("Email")){
                emailContacts[k++]=contacts[i];
            }
        }
        return emailContacts;
    }

    public Contact[] getPhoneContacts() {
        Contact[] phoneContacts=new Contact[numOfPhoneContacts];
        for(int i=0,k=0;i<current;i++){
            if(contacts[i].getType().equals("Phone")){
                phoneContacts[k++]=contacts[i];
            }
        }
        return phoneContacts;
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return firstName + " " + lastName;


    }
    public long getIndex() {
        return index;
    }
    public Contact getLatestContact(){
        Contact latest=contacts[0];
        for(int i=0;i<current;i++){
            if(!(latest.isNewerThan(contacts[i])))
                latest=contacts[i];
        }
        return latest;
    }
    //TODO

    @Override
    public String toString() {
        return "{\"ime\":\"" + firstName + "\", " +
                "\"prezime\":\"" + lastName  + "\", " +
                "\"vozrast\":" + age + ", " +
                "\"grad\":\"" + city + "\", " +
                "\"indeks\":" + index + ", " +
                "\"telefonskiKontakti\":" + Arrays.toString(getPhoneContacts()) + ", " +
                "\"emailKontakti\":" + Arrays.toString(getEmailContacts()) + "}";
    }
}
abstract class Contact{

    int day;
    int month;
    int year;

    public Contact(String date) {
        //YYYY-MM-DD
        String[] s=date.split("-");
        this.day= Integer.parseInt(s[2]);
        this.month=Integer.parseInt(s[1]);
        this.year=Integer.parseInt(s[0]);

    }
    public boolean isNewerThan(Contact c){
        if(c==null)
            return false;
        if(year>c.year)
            return true;
        else if (year==c.year && month>c.month)
            return true;
        else if (year==c.year && month==c.month && day==c.day)
            return true;
        else
            return false;
    }
    public abstract String getType();
}
class EmailContact extends Contact{
    String email;

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String getType() {
        return "Email";
    }

    @Override
    public String toString() {
        return "\""+email + "\"";
    }
}
class PhoneContact extends Contact{
    String phone;
    Operator operator;

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public Operator getOperator() {
        int index=Integer.parseInt(String.valueOf(phone.charAt(2)));
        if(index>=0 && index<=2)
            return Operator.TMOBILE;
        if(index>=5 && index<=6)
            return Operator.ONE;
        if(index>=7 && index<=8)
            return Operator.VIP;

        return null;
    }

    @Override
    public String getType() {
        return "Phone";
    }
    @Override
    public String toString() {
        return "\""+phone + "\"";
    }
}
enum Operator{ VIP,ONE,TMOBILE}

public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}

