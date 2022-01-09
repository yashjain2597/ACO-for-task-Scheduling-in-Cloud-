package resource_Scheduling_Using_ACO;

import java.util.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import java.awt.event.*;  




public class MyAllocationTest {
	private static List<Cloudlet> cloudletList;
	private static int cloudletNum;
	static JFrame frame;
	static JTextField textField;
	private static List<Vm> vmList= new ArrayList<Vm>();
	private static int vmNum=5;
	static String st;
	static double value1=0;
	
	MyAllocationTest(){
		//ImageIcon icon = new ImageIcon(getClass().getResource("aco1.png"));
	    //JLabel commentlabel = new JLabel(icon);
	    //commentlabel.setBounds(520,25,250,200);
	    //frame.add(commentlabel);
		
	}
	
	
	public static void main(String args[]){
		
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		new MyAllocationTest(); 
		
		final JLabel lblTaskSchedulingIn = new JLabel("Task Scheduling In Cloud Environment");
		lblTaskSchedulingIn.setBounds(20, 0, 550, 80);
		lblTaskSchedulingIn.setFont(new Font("FreeSerif", Font.BOLD, 25));
		frame.getContentPane().add(lblTaskSchedulingIn);    
	    frame.setBackground(Color.GRAY);
	    frame.setTitle("Ant Colony Optimization");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyAllocationTest window = new MyAllocationTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Enter the Number of Cloudlets");
		lblNewLabel.setBounds(32, 72, 236, 20);
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(341, 72, 114, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		              
		    
		
		
		JButton btnNewButton = new JButton("Start Simulation");
	    btnNewButton.setBounds(341, 116, 157, 25);
	    frame.getContentPane().add(btnNewButton);
	    
	    frame.setSize(800,600);    
	    frame.setVisible(true);
	    
	    
	    
		
	    
		
		Log.printLine("Starting ACO Simulaion...");
		int num_user=16;
		Calendar calendar=Calendar.getInstance();
		boolean trace_flag=false;
		System.out.println("Enter the number of Cloudlets to be allocated- ");  
		
		
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(32, 190, 236, 32);
		lblNewLabel1.setFont(new Font("FreeSerif", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lbltime = new JLabel();
		lbltime.setBounds(32, 430, 630, 32);
		lbltime.setFont(new Font("FreeSerif", Font.BOLD, 16));
		frame.getContentPane().add(lbltime);
		
		btnNewButton.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            //tf.setText("Welcome to Javatpoint.");  
	    		st= textField.getText();
	    		
	    		
	    		cloudletNum=Integer.parseInt(st);
	    		CloudSim.init(num_user, calendar, trace_flag);
	    		
	    		@SuppressWarnings("unused")
	    		Datacenter datacenter0=createDatacenter("Datacenter_0");
	    		
	    		LinkACO broker=createBroker();
	    		int brokerId=broker.getId();
	    		
	    		long size=10000;
	    		int ram=512;
	    		long bw=1000;
	    		int pesNumber=1;
	    		String vmm="xen";
	    		

	    		Vm vm1 = new Vm(0, brokerId, 5000, pesNumber, ram, bw, size,
	    				vmm, new CloudletSchedulerSpaceShared());
	    		Vm vm2 = new Vm(1, brokerId, 2500, pesNumber, ram, bw, size,
	    				vmm,new CloudletSchedulerTimeShared());
	    		Vm vm3 = new Vm(2, brokerId, 2500, pesNumber, ram, bw, size,
	    				vmm,new CloudletSchedulerTimeShared());
	    		Vm vm4 = new Vm(3, brokerId, 1500, pesNumber, ram, bw, size,
	    				vmm, new CloudletSchedulerSpaceShared());
	    		Vm vm5 = new Vm(4, brokerId, 1000, pesNumber, ram, bw, size,
	    				vmm, new CloudletSchedulerSpaceShared());

	    					vmList.add(vm1);
	    					vmList.add(vm2);
	    					vmList.add(vm3);
	    					vmList.add(vm4);
	    					vmList.add(vm5);
	    					
	    		broker.submitVmList(vmList);
	    		
	    		int id=0;
	    		long[] lengths=new long[cloudletNum];
	    		long fileSize=1000;
	    		long outputSize=1000;
	    		UtilizationModel model=new UtilizationModelFull();
	    		Random rand = new Random();
	    		for(int i=0;i<cloudletNum;i++)
	    		{
	    		long leftLimit = 11000;
	    	    long rightLimit = 12000;
	    	    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	    	    lengths[i]=generatedLong;
	    		}
	    		cloudletList=new ArrayList<Cloudlet>();
	    		for (int i = 0; i < cloudletNum; i++) {
	    			Cloudlet cloudlet=new Cloudlet(id, lengths[i], pesNumber, fileSize, outputSize, model, model, model);
	    			cloudlet.setUserId(brokerId);
	    			cloudletList.add(cloudlet);
	    			id++;
	    		}
	    		broker.submitCloudletList(cloudletList);
	    		broker.bind(5,50);//bind
	    		
	    
	    		CloudSim.startSimulation();
	    		
	    		List<Cloudlet> newList=broker.getCloudletReceivedList();
	    		CloudSim.stopSimulation();
	    		
	    		
	    		
	    		
	    		
	    		Log.printLine("ACO simulation is finished!");
	    		
	    		lblNewLabel1.setText("ACO Simulation finished!");
	    		
	    		String data[][]=printCloudletList(newList);
	    		
	    		String column[]={"Cloudlet ID" ,  "Datacenter ID" ,  "VM ID", "Time", "Start Time", "Finish Time", "Status"};
	    		JTable jt=new JTable(data,column);    
			    frame.getContentPane().add(jt);
			    //jt.setBounds(32,185,540,192);
			    JScrollPane scrollPane = new JScrollPane(jt);
			    scrollPane.setBounds(32, 230, 700, 180);
			    frame.getContentPane().add(scrollPane);
			    
			    lbltime.setText("This (ACO) schedule plan takes "+ value1/10 +" ms  to finish execution.");
	    	        }  
	    	    });
		
		//Log.printLine("ACO simulation is finished!");*/
	}
	 
	
	public static Datacenter createDatacenter(String name){
		List<Host> hostList=new ArrayList<Host>();
		List<Pe> peList = new ArrayList<Pe>();
		
		
		
		
		int mips=5000;
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); 
		
		mips = 2500;
		peList.add(new Pe(1, new PeProvisionerSimple(mips))); 
		
		mips = 2500;
		peList.add(new Pe(2, new PeProvisionerSimple(mips))); 
		
		mips = 1500;
		peList.add(new Pe(3, new PeProvisionerSimple(mips)));
			
		mips = 1000;
		peList.add(new Pe(4, new PeProvisionerSimple(mips))); 
		
		
		
		
		
		
		int hostId=0;
		int ram=4096;
		long storage=10000000;
		int bw=10000;

		
		hostList.add(new Host(hostId, new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw), storage, peList,
				new VmSchedulerTimeShared(peList)));
		

		
		
		String arch="x86";
		String os="Linux";
		String vmm="Xen";
		double time_zone=5.30;
		double cost=3.0;
		double costPerMcm=0.05;
		double costPerStorage=0.001;
		double costPerBw=0.001;
		LinkedList<Storage> storageList=new LinkedList<Storage>();
		
		DatacenterCharacteristics characteristics=new DatacenterCharacteristics(arch, os, vmm, hostList, time_zone, cost, costPerMcm, costPerStorage, costPerBw);
		Datacenter datacenter=null;
		try {
			datacenter=new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return datacenter;
	}
	
	private static LinkACO createBroker(){
		LinkACO broker=null;
		try {
			broker=new LinkACO("Broker");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return broker;
	}
	

	
	
	
	protected static String[][] printCloudletList(List<Cloudlet> list){
		int size=list.size();
		Cloudlet cloudlet;
		 //double value1=0;
		String indent="    ";
		Log.printLine();
		Log.printLine("========Execution Result ========");
		Log.printLine("Cloudlet ID"+indent+"STATUS"+indent+"Datacenter ID"+indent+"VM ID"+indent+"Time"+indent+"Start Time"+indent+"Finish Time");
		DecimalFormat dft=new DecimalFormat("###.##");
		String arr[][]=new String[size][7];
		for(int i=0;i<size;i++){
			cloudlet=list.get(i);
			Log.print(indent+cloudlet.getCloudletId()+indent+indent);
			if (cloudlet.getStatus()==Cloudlet.SUCCESS) {
				Log.print("SUCESSS");
				arr[i][0]=String.valueOf(cloudlet.getCloudletId());
				arr[i][1]=String.valueOf(cloudlet.getResourceId());
				arr[i][2]=String.valueOf(cloudlet.getVmId());
				arr[i][3]=String.valueOf(cloudlet.getActualCPUTime());
				arr[i][4]=String.valueOf(cloudlet.getExecStartTime());
				arr[i][5]=String.valueOf(cloudlet.getFinishTime());
				arr[i][6]="Success";
				Log.printLine(indent+indent+cloudlet.getResourceId()+indent+indent+indent
						+cloudlet.getVmId()+indent+indent+dft.format(cloudlet.getActualCPUTime())+ 
						indent+indent+dft.format(cloudlet.getExecStartTime())+
						indent+indent+dft.format(cloudlet.getFinishTime()));
		
		
			}
		 value1= value1+Double.parseDouble(dft.format(cloudlet.getActualCPUTime()));
					
	
	
			
		}
		
		Log.printLine("================ Execution Result Ends here ==================");
		System.out.println("This (ACO) schedule plan takes "+value1/10+" ms  to finish execution.");
		
		return arr;
	}
}
