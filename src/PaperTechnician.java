public class PaperTechnician implements Runnable{

    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public PaperTechnician(String name, ThreadGroup group, ServicePrinter printer) {
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }



    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            printer.refillPaper();
            try {
                int num = ((int)Math.random() * 100);
                Thread.sleep(num);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
