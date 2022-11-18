public class LaserPrinter implements ServicePrinter{
    private String name;
    private int id;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int documentPrinted;
    private boolean paperRefilled = false; // to keep track of call to refill paper() is sucesssful or not
    private boolean tornerRefilled = false; // to keep track of call to refill replaced() is sucessfull or not

    public boolean isTornerRefilled() {
        return tornerRefilled;
    }

    public boolean isPaperRefilled() {
        return paperRefilled;
    }

    public LaserPrinter(String name, int id, int currentPaperLevel, int currentTonerLevel, int documentPrinted) {
        super();
        this.name = name;
        this.id = id;
        this.currentPaperLevel = currentPaperLevel;
        this.currentTonerLevel = currentTonerLevel;
        this.documentPrinted = documentPrinted;
        this.paperRefilled = paperRefilled;
        this.tornerRefilled = tornerRefilled;
    }

    @Override
    public synchronized void printDocument(Document document) {
        while (document.getNumberOfPages() <= currentPaperLevel && document.getNumberOfPages() <= currentTonerLevel) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " Finished printing: 5 Documents, ");
        this.currentPaperLevel -= document.getNumberOfPages();
        this.currentTonerLevel -= document.getNumberOfPages();
        notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {
        //change this method
//        while (this.currentTonerLevel > (Minimum_Toner_Level -1)) {
//            try {
//                wait(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        this.currentTonerLevel = PagesPerTonerCartridge;
//        notifyAll();

        // option 2
        boolean tried =false;
        this.tornerRefilled = false;
        while (!(this.currentTonerLevel > (Minimum_Toner_Level - 1))) {
            if(tried) {
                break;
            }
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(this.currentTonerLevel < Minimum_Toner_Level) {
            this.currentTonerLevel = 500;
            this.tornerRefilled = true;
            notifyAll();
        }

    }

    @Override
    public void refillPaper() {
        // update this method
//        int count =0;
//        while (count < 2) {
//            if(this.currentPaperLevel <= (Full_Paper_Tray - 50)) {
//                this.currentPaperLevel +=50;
//                break;
//            } else {
//                if(count == 1){
//                    break;
//                }
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//            count++;
//        }
//
//        while (this.currentPaperLevel <= Full_Paper_Tray) {
//            this.currentPaperLevel += 50;
//        }
        boolean tried = false;
        this.paperRefilled = false;

        while (!(this.currentPaperLevel <= (Full_Paper_Tray - 50))) {
            if(tried == true) {
                break;
            }
        }
    }

    //[ PrinterID: lp-CG.24, Paper Level: 35, Toner Level: 310, Documents Printed:

    @Override
    public String toString() {
        return "[ PrinterID: " + name + "." + id +
                " , Paper Level: " + currentPaperLevel +
                ", Toner Level: " + currentTonerLevel +
                ", Documents Printed: " + documentPrinted;
    }

    // Getters and Setters

}
