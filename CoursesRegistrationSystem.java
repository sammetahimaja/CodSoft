import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean addStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        } else {
            return false;
        }
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    @Override
    public String toString() {
        return courseCode + ": " + title + " - " + description + " (Capacity: " + capacity + ", Enrolled: " + enrolled + ", Schedule: " + schedule + ")";
    }
}

class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.addStudent()) {
            registeredCourses.add(course);
            return true;
        } else {
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.removeStudent()) {
            registeredCourses.remove(course);
            return true;
        } else {
            return false;
        }
    }
}

class CourseRegistrationSystem {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}

public class CoursesRegistrationSystem {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some courses
        system.addCourse(new Course("CS101", "Intro to Computer Science", "Basics of computer science", 30, "MWF 9:00-10:00"));
        system.addCourse(new Course("MATH201", "Calculus I", "Introduction to calculus", 25, "TTh 11:00-12:30"));
        system.addCourse(new Course("ENG301", "English Literature", "Study of English literature", 20, "MWF 1:00-2:00"));

        // Add some students
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Display available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = system.findStudentByID(studentID);
                    if (student != null) {
                        System.out.print("Enter course code to register: ");
                        String courseCode = scanner.nextLine();
                        Course course = system.findCourseByCode(courseCode);
                        if (course != null) {
                            if (student.registerCourse(course)) {
                                System.out.println("Registration successful!");
                            } else {
                                System.out.println("Registration failed. Course may be full.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    student = system.findStudentByID(studentID);
                    if (student != null) {
                        System.out.print("Enter course code to drop: ");
                        String courseCode = scanner.nextLine();
                        Course course = system.findCourseByCode(courseCode);
                        if (course != null) {
                            if (student.dropCourse(course)) {
                                System.out.println("Course dropped successfully.");
                            } else {
                                System.out.println("Drop failed. Course not registered.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}