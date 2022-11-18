public class TonerTechnician implements Runnable {
    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public TonerTechnician(String name, ThreadGroup group, ServicePrinter printer) {
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }



    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            printer.replaceTonerCartridge();

            if(((LaserPrinter)printer).isTornerRefilled()){
                count ++;
            }
            try {
                int num = ((int)Math.random() * 100);
                Thread.sleep(num);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Toner Technician Finished cartridges replaced: " + count);
    }

}
