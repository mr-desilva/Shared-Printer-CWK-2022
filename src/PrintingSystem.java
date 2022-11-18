public class PrintingSystem {
    public static void main(String[] args) {
        ServicePrinter printer = new LaserPrinter("canon printer", 10, 400, 350, 200);

        ThreadGroup studentsGroup = new ThreadGroup("Students");
        ThreadGroup technicalGroup = new ThreadGroup("Technician");

        Runnable studentRunnable1 = new Student("Adeesha", studentsGroup, printer);
        Runnable studentRunnable2 = new Student("Malith", studentsGroup, printer);
        Runnable studentRunnable3 = new Student("Sanjula", studentsGroup, printer);
        Runnable studentRunnable4 = new Student("Abhilash", studentsGroup, printer);

        Thread studentThread1 = new Thread(studentsGroup, studentRunnable1, "Adeesha");
        Thread studentThread2 = new Thread(studentsGroup, studentRunnable2, "Malith");
        Thread studentThread3 = new Thread(studentsGroup, studentRunnable3, "Sanjula");
        Thread studentThread4 = new Thread(studentsGroup, studentRunnable4, "Abhilash");

        Runnable paperTechnician =new PaperTechnician("PaperTech", technicalGroup, printer);
        Runnable tonerTechnician =new PaperTechnician("TornerTech", technicalGroup, printer);

        Thread paperTechThread = new Thread(technicalGroup, paperTechnician, "Paper Tech");
        Thread tonerTechThread = new Thread(technicalGroup, paperTechnician, "Paper Tech");

        studentThread1.start();
        studentThread2.start();
        studentThread3.start();
        studentThread4.start();

        paperTechThread.start();
        tonerTechThread.start();





    }
}
