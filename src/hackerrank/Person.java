package hackerrank;

import java.util.Scanner;

public class Person {

    private int age;

    Person(final int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age is not valid, setting age to 0.");
            this.age = 0;
        }
    }

    public int getAge() {
        return age;
    }

    public void yearPasses() {
        age++;
    }

    public void amIOld() {
        if (age < 13) {
            System.out.println("You are young.");

        } else if (age >= 13 && age < 18) {
            System.out.println("You are a teenager.");

        } else {
            System.out.println("You are old.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int age = sc.nextInt();
            Person p = new Person(age);
            p.amIOld();
            for (int j = 0; j < 3; j++) {
                p.yearPasses();
            }
            p.amIOld();
            System.out.println();
        }
        sc.close();
    }
}
