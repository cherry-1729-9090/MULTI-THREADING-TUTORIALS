package CourseNotifier;

public class CourseNotifier {
    public static void main(String[] args) throws InterruptedException {
        final Course course = new Course("Java Programming");

        // create 3 threads 2 for students waiting for the notification and one for the
        // instructor who is writing the course

        new Thread(() -> {
            synchronized (course) {

                System.out.println(Thread.currentThread().getName() + " is waiting the course : " + course.getTitle());

                try {
                    course.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " has been notified for course : "
                        + course.getTitle() + "that is completed : " + course.isCompleted());
            }


        }, "StudentA").start();

        new Thread(() -> {

            synchronized (course) {

                System.out.println(Thread.currentThread().getName() + " is waiting the course : " + course.getTitle());

                try {
                    course.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " has been notified for course : "
                        + course.getTitle() + "that is completed : " + course.isCompleted());
            }


        }, "StudentB").start();

        new Thread(() -> {

            synchronized (course) {

                System.out.println(Thread.currentThread().getName() + " is starting the new course : " + course.getTitle());

                try {
                    Thread.sleep(5000);
                    course.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }, "Rahul").start();

    }
}

class Course {
    private String title;
    private boolean completed;

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
