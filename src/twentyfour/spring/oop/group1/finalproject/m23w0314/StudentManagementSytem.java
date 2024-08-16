package twentyfour.spring.oop.group1.finalproject.m23w0314;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Base Student Class **/

class Student {
    String name;
    String father_name;
    String email;
    String student_id;
    String student_contact;
    String course;
    String year;

    public void getInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student's name: ");
        name = sc.nextLine();
        System.out.print("Enter Student's Father name: ");
        father_name = sc.nextLine();
        System.out.print("Enter Student's ID: ");
        student_id = sc.nextLine();
        System.out.print("Enter Student's Email ID: ");
        email = sc.nextLine();
        System.out.print("Enter Student's Contact Info: ");
        student_contact = sc.nextLine();
        System.out.print("Enter Student's Course: ");
        course = sc.nextLine();
        System.out.print("Enter Student's Year: ");
        year = sc.nextLine();
    }
}

/*** Inherited Class ***/

class StudentDetail extends Student {
}


/*** Main Menu Class ***/

class MainMenu {
    public void menu() {
        System.out.println("*************************************************");
        System.out.println("               STUDENT MANAGEMENT SYSTEM");
        System.out.println("*************************************************");

        System.out.println("\nChoose one of the following options:");

        System.out.println("\n1. Add Student Details");
        System.out.println("2. View Student Details");
        System.out.println("3. Remove Student");
        System.out.println("4. Update Student Details");
        System.out.println("5. Exit");
    }
}


/*** Add Student Details Class ***/

class Student_Add {
    public void createFile() {
        Scanner sc = new Scanner(System.in);

        StudentDetail stu = new StudentDetail();
        stu.getInfo();
        try {
            File f1 = new File("file" + stu.student_id + ".txt");
            if (f1.createNewFile()) {
                FileWriter myWriter = new FileWriter("file" + stu.student_id + ".txt");
                myWriter.write("Student ID: " + stu.student_id + "\n" + "Student Name: " + stu.name + "\n" +
                        "Father's Name: " + stu.father_name + "\n" + "Student Contact: " + stu.student_contact + "\n" +
                        "Email Information: " + stu.email + "\n" + "Course: " + stu.course + "\n" +
                        "Year: " + stu.year);
                myWriter.close();
                System.out.println("\nStudent has been added.\n");
            } else {
                System.out.println("\nStudent already exists.\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.print("Press Enter to continue...");
        sc.nextLine();
    }
}


/*** Show Student Details Class ***/

class Student_Show {
    public void viewFile(String s) throws Exception {
        File file = new File("file" + s + ".txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}


/*** Remove Student Details Class ***/

class Student_Remove {
    public void removeFile(String ID) {
        File file = new File("file" + ID + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("\nStudent has been removed successfully.");
            }
        } else {
            System.out.println("\nStudent does not exist.");
        }
    }
}


/*** Update Student Details Class ***/


class Student_Update {
    public void updateFile(String studentId, String detailToUpdate, String newDetail) throws IOException {
        // Construct the file path based on studentId
        String filePath = "file" + studentId + ".txt";

        // Read existing content from the file
        StringBuilder fileContent = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading student file: " + e.getMessage());
            throw e;
        }

        // Replace old detail with new detail
        String oldDetailPattern = detailToUpdate + ":\\s*.*";
        String replacement = detailToUpdate + ": " + newDetail;
        String updatedContent = fileContent.toString().replaceAll(oldDetailPattern, replacement);

        // Write updated content back to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(updatedContent);
        } catch (IOException e) {
            System.out.println("Error writing to student file: " + e.getMessage());
            throw e;
        }

        System.out.println("Student details updated successfully.");
    }
}


/*** Exit from SMS Class ***/

class CodeExit {
    public void out() {
        System.out.println("***************************************");
        System.out.println("       Thank You for Using SMS");
        System.out.println("***************************************");
        System.exit(0);
    }
}

/*** Main Class ***/

class StudentManagementSystem {
    public static void main(String[] args) {
        // Clear the console screen
        System.out.print("\033[H\033[2J");

        // Create Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Create an object to show student details
        Student_Show spv = new Student_Show();

        // Create an object for the main menu
        MainMenu obj1 = new MainMenu();

        // Variable to store user choice
        int choice = 0;

        // Display the main menu
        obj1.menu();

        // Loop until the user chooses to exit (choice 5)
        while (choice != 5) {
            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            // Handle user choice
            switch (choice) {
                case 1:
                    // Add student details
                    Student_Add sp = new Student_Add();
                    sp.createFile();
                    break;

                case 2:
                    // View student details
                    System.out.print("Enter Student's ID: ");
                    String idToView = sc.nextLine();
                    try {
                        spv.viewFile(idToView);
                    } catch (Exception e) {
                        System.out.println("\nInvalid Student ID or No student found.");
                    }
                    break;

                case 3:
                    // Remove student details
                    System.out.print("Enter Student's ID: ");
                    String idToRemove = sc.nextLine();
                    Student_Remove spr = new Student_Remove();
                    spr.removeFile(idToRemove);
                    break;

                case 4:
                    // Update student details
                    System.out.print("Enter Student's ID: ");
                    String idToUpdate = sc.nextLine();
                    try {
                        spv.viewFile(idToUpdate);
                    } catch (Exception e) {
                        System.out.println("\nInvalid Student ID or No student found.");
                        break; // Exit case if student ID is invalid
                    }

                    // Display options for what detail to update
                    System.out.println("\nChoose what detail to update:");
                    System.out.println("1. Name");
                    System.out.println("2. Father's Name");
                    System.out.println("3. Email");
                    System.out.println("4. Contact Number");
                    System.out.println("5. Course");
                    System.out.println("6. Year");

                    // Prompt user to enter their choice
                    System.out.print("Enter your choice (1-6): ");
                    int option = Integer.parseInt(sc.nextLine());

                    // Initialize variables for update
                    String detailToUpdate = "";
                    String promptMessage = "Enter the updated ";

                    // Determine which detail to update based on user choice
                    switch (option) {
                        case 1:
                            detailToUpdate = "Student Name";
                            promptMessage += "name: ";
                            break;
                        case 2:
                            detailToUpdate = "Father's Name";
                            promptMessage += "father's name: ";
                            break;
                        case 3:
                            detailToUpdate = "Email Information";
                            promptMessage += "email: ";
                            break;
                        case 4:
                            detailToUpdate = "Student Contact";
                            promptMessage += "contact number: ";
                            break;
                        case 5:
                            detailToUpdate = "Course";
                            promptMessage += "course: ";
                            break;
                        case 6:
                            detailToUpdate = "Year";
                            promptMessage += "year: ";
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    // Prompt user for the updated detail
                    if (!detailToUpdate.isEmpty()) {
                        System.out.print(promptMessage);
                        String newDetail = sc.nextLine();

                        // Update the student file with the new detail
                        Student_Update spu = new Student_Update();
                        try {
                            spu.updateFile(idToUpdate, detailToUpdate, newDetail);
                            System.out.println("Student details updated successfully.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 5:
                    // Exit the program
                    CodeExit obj = new CodeExit();
                    obj.out();
                    break;
                default:
                    // Handle invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // If the user did not choose to exit, clear the screen and display the menu again
            if (choice != 5) {
                System.out.print("\033[H\033[2J");
                obj1.menu();
            }
        }
    }
}