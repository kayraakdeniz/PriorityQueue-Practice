import java.util.*;

public class Main {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}

class Priorities {

    PriorityQueue<Student> queue = new PriorityQueue<>(new Comparator<Student>() {
        //@Override
        public int compare(Student o1, Student o2) {
            if (o1.getCgpa() < o2.getCgpa()) {
                return 1;
            } else if (o1.getCgpa() > o2.getCgpa()) {
                return -1;
            } else {
                if (o1.getName().compareTo(o2.getName()) < 0) {
                    return -1;
                } else if (o1.getName().compareTo(o2.getName()) > 0) {
                    return 1;
                } else {
                    if (o1.getId() < o2.getId()) {
                        return -1;
                    } else if (o1.getId() > o2.getId()) {
                        return +1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    });

    public List<Student> getStudents(List<String> events) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).contains("ENTER")) {
                String s1 = events.get(i).substring(6);
                String[] words = s1.split("\\s");
                Student stu = new Student(Integer.parseInt(words[2]), words[0], Double.parseDouble(words[1]));
                queue.offer(stu);
            } else {
                queue.poll();
            }
        }
        while (!queue.isEmpty()) {
            students.add(queue.poll());
        }

        return students;
    }

}


class Student {

    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

}
