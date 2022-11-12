import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;


public class PatientFrame {
   private JFrame frame;
   private JLabel headerLabel;
   //private JLabel statusLabel;
   private JLabel headerLabel1;
   private JTextArea statusLabel;
   private JPanel controlPanel;
   public PatientFrame(){
      prepareGUI();
   }
   static ISortedList<Patient> patientSortedList;
   IPriorityQueue<Patient> patientPriorityQueue = new PriorityQueue<>();

   public static void main(String[] args) throws IOException{
	   
      PatientManagement patientManagement = new PatientManagement();
      patientSortedList = patientManagement.management();


      PatientFrame swingControlDemo = new PatientFrame();
      swingControlDemo.showButtonDemo(patientSortedList);

   }
   private void prepareGUI(){
      frame = new JFrame("Java Swing");
      frame.setSize(1200,800);
      frame.add(new JLabel(new ImageIcon("./img/amrita.png")));
      frame.setLayout(new GridLayout(6, 1));
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }
      });
      
      headerLabel = new JLabel("", JLabel.CENTER);
      headerLabel.setFont(new Font("Verdana", Font.BOLD, 28));
      headerLabel.setForeground(Color.BLUE);

      headerLabel1= new JLabel("", JLabel.CENTER);
      headerLabel1.setFont(new Font("Verdana", Font.PLAIN, 18));
      headerLabel1.setForeground(Color.BLUE);
      
      statusLabel = new JTextArea();
      JScrollPane scrollV = new JScrollPane (statusLabel);
      scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      //statusLabel.setSize(150,300);
      statusLabel.setBounds(20,75,350,400);  
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      frame.add(headerLabel);
      frame.add(controlPanel);
      frame.add(scrollV);
      frame.add(headerLabel1);
      frame.setVisible(true);
   }
   private void showButtonDemo(ISortedList<Patient> patientSortedList ){
      headerLabel.setText("Patient Report for Analytics");  
      headerLabel1.setText("By Geethika Naidu Pula");  
      JButton okButton1= new JButton("List Patients based on Treatment Time");
      okButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	priorityOrder(patientSortedList);
            //statusLabel.setText("Ok Button is clicked here");
         }
      });
      controlPanel.add(okButton1);
      frame.setVisible(true);
    
      // list of patients after 5 treatments
      JButton okButton2 = new JButton("List Patients after 5 Treatment");
      okButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //statusLabel.setText("Ok Button is clicked here");
        	 fiveTreatment(patientSortedList);
         }
      });
      controlPanel.add(okButton2);
      frame.setVisible(true);
    
      // list of patients after 10 treatments
      JButton okButton3 = new JButton("List Patients after 10 treatment");
      okButton3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 tenTreatment(patientSortedList);
            //statusLabel.setText("Ok Button is clicked here");
         }
      });
      controlPanel.add(okButton3);
      frame.setVisible(true);
    
      // last patient
      JButton okButton4 = new JButton("Last Patient in the Queue");
      okButton4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 finalPatient(patientSortedList);
           // statusLabel.setText("Ok Button is clicked here");
         }
      });
      controlPanel.add(okButton4);
      frame.setVisible(true);
   }




       public void management() throws IOException {

           FileIO.readFile(patientSortedList);
           allInformation();
       }
       
       
       public void allInformation() {
           System.out.println("The listed patients with all their information: \n");
           for (int index = 1; index <= patientSortedList.getLength(); index++) {
               System.out.println(patientSortedList.getEntry(index));
           }
           System.out.println();
           System.out.println();
           System.out.println();
       }
       
       public void priorityOrder(ISortedList<Patient> patientSortedList) {
           ISortedList<Patient> tempList = new SortedList<>();
           for (int index = 1; index <= patientSortedList.getLength(); index++) {
               tempList.add(patientSortedList.getEntry(index));
           }

           PatientQueue.waitingLine(tempList,patientPriorityQueue);
          // statusLabel.add("The line of patients according to their order of receiving treatment time with all their" +
            //       " information: \n");
           patientPriorityQueue.displayItems(statusLabel);
          System.out.println("1");
           //System.out.println();
           //System.out.println();
       }
       
       public void finalPatient(ISortedList<Patient> patientSortedList) {
    	   ISortedList<Patient> tempList = new SortedList<>();
           for (int index = 1; index <= patientSortedList.getLength(); index++) {
               tempList.add(patientSortedList.getEntry(index));
           }

           PatientQueue.waitingLine(tempList,patientPriorityQueue);


    	   int size = patientPriorityQueue.getSize();
           for (int index = 0; index < size; index++) {
               if (index == size - 1){
                   System.out.println("The final patient: \n");
                   patientPriorityQueue.displayItems(statusLabel);
                   }
               patientPriorityQueue.remove();
               
              }

           }

           public void fiveTreatment(ISortedList<Patient> patientSortedList) {
        	   ISortedList<Patient> tempList = new SortedList<>();
               for (int index = 1; index <= patientSortedList.getLength(); index++) {
                   tempList.add(patientSortedList.getEntry(index));
               }

               PatientQueue.waitingLine(tempList,patientPriorityQueue);

               int size = patientPriorityQueue.getSize();
               System.out.println(size);
               
               for (int index = 5; index < size; index++) {
                       patientPriorityQueue.remove();
                       if (index % 5 == 4) {
                           System.out.println("The line of patients after " + (index + 1) + " treatments: \n");
                           patientPriorityQueue.displayItems(statusLabel);
                           System.out.println();
                           System.out.println();
                       }
                   }
               }
       
           public void tenTreatment(ISortedList<Patient> patientSortedList) {
        	   ISortedList<Patient> tempList = new SortedList<>();
               for (int index = 1; index <= patientSortedList.getLength(); index++) {
                   tempList.add(patientSortedList.getEntry(index));
               }

               PatientQueue.waitingLine(tempList,patientPriorityQueue);

        	   int size = patientPriorityQueue.getSize();
               for (int index = 10; index < size; index++) {
                       patientPriorityQueue.remove();
                       if (index % 5 == 4) {
                           System.out.println("The line of patients after " + (index + 1) + " treatments: \n");
                           patientPriorityQueue.displayItems(statusLabel);
                           System.out.println();
                           System.out.println();
                       }
                   }
               }
       



}

