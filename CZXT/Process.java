package Demo;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;

public class Process {
	Method method = new Method();
	ArrayList<Lists> list;
	int firstTime = 0;
	/**
	 * ���캯��
	 */
	public Process(ArrayList<Lists> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		int first = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(first).createTime > list.get(i).createTime){
				first = i;
			}
		}
		this.firstTime = list.get(first).createTime;
	}
	
    /**
     * �����ȷ���
     */
    public void FCFS(){
        base(0);
    }
    
    /**
     * �̽�������
     */
    public void SPF(){
        base(1);
    }
    
    /**
     * ����Ӧ�����ȷ�
     */
    public void response(){
        base(3);
    }
    
    /**
     * ������ռʽ���ȼ���
     */
    public void priorityNotPossession(){
       base(2);
    }
    
    /**
     * ʱ��Ƭ��ת
     * �����ȼ���ʾ������ʱ��Ƭ�е���ת
     *      0��ʾû����ת��1��ʾ�Ѿ���ת����
     *      ArrayList����ָ���⡣
     */
    public void RR(){
    	ArrayList<Lists> process = new ArrayList<Lists>(this.list);
    	ArrayList<Time> saveTime = new ArrayList<Time>();
    	String[][] proString = new String[process.size()][5];
    	for (int i = 0; i < proString.length; i++) {
    		Lists lists = process.get(i);
    		proString[i][0] = lists.name;
    		proString[i][1] = lists.createTime+"";
    		proString[i][2] = lists.requireTime+"";
    		proString[i][3] = lists.priority+"";
    		proString[i][4] = 0+"";//����ʱ��
		}
		//overTime ��¼��ǰ��ʱ��	next ��һ��Ҫִ�еĽ��̺�
		int overTime=0,i=1,next = 0;
		int totalTime = method.totalTime(process);
		System.out.println("ʱ��Ƭ��ת:");
		System.out.print("��ǰ��תʱ��Ϊ:");
		for (int j = 0; j < process.size(); j++) {
			process.get(j).priority = 0;
		}
		for (; i <= totalTime; i++) {
			next = method.findRR(new ArrayList<Lists>(process),i-1);
			process.get(next).priority=1;
			System.out.print(process.get(next).name+",");
			process.get(next).requireTime = process.get(next).requireTime - 1;
			if (process.get(next).requireTime == 0) {
				for (int j = 0; j < proString.length; j++) {
					if (proString[j][0] == process.get(next).name) {
						proString[j][4] = i+"";
						break;
					}
				}
				process.remove(next);
			}
			if (method.check(process)) {
				for (int j = 0; j < process.size(); j++) {
					process.get(j).priority = 0;
				}
			}
		}
		//������תʱ��ʹ�Ȩ��תʱ��
		for (int j = 0; j < proString.length; j++) {
			int createTime = Integer.parseInt(proString[j][1]);
			int requireTime = Integer.parseInt(proString[j][2]);
			int priority = Integer.parseInt(proString[j][3]);
			int t = Integer.parseInt(proString[j][4]);
			Time time = new Time(proString[j][0], 
					t-createTime,
					(double)((t-createTime)*100/requireTime)/100);
			saveTime.add(time);
		}
		System.out.println();
		method.printTurnTime(saveTime);
		for (int j = 0; j < proString.length; j++) {
			this.list.get(j).createTime = Integer.parseInt(proString[j][1]);
			this.list.get(j).requireTime = Integer.parseInt(proString[j][2]);
			this.list.get(j).priority = Integer.parseInt(proString[j][3]);
		}
    }
    
    /**
     * ����ռʽ���ȼ���
     */
    public void priorityPossession(){
    	ArrayList<Lists> process = new ArrayList<Lists>(this.list);
    	ArrayList<Time> saveTime = new ArrayList<Time>();
    	String[][] proString = new String[process.size()][5];
    	for (int i = 0; i < proString.length; i++) {
    		Lists lists = process.get(i);
    		proString[i][0] = lists.name;
    		proString[i][1] = lists.createTime+"";
    		proString[i][2] = lists.requireTime+"";
    		proString[i][3] = lists.priority+"";
    		proString[i][4] = 0+"";//����ʱ��
		}
		//overTime ��¼��ǰ��ʱ��	next ��һ��Ҫִ�еĽ��̺�
		int overTime=0,i=1,next = 0;
		int totalTime = method.totalTime(process);
		System.out.println("����ռʽ���ȼ���:");
		System.out.print("��ǰ��תʱ��Ϊ:");
		for (; i <= totalTime; i++) {
			next = method.findPriority(new ArrayList<Lists>(process),i-1);
			System.out.print(process.get(next).name+",");
			process.get(next).requireTime = process.get(next).requireTime - 1;
			if (process.get(next).requireTime == 0) {
				for (int j = 0; j < proString.length; j++) {
					if (proString[j][0] == process.get(next).name) {
						proString[j][4] = i+"";
						break;
					}
				}
				process.remove(next);
			}
		}
		//������תʱ��ʹ�Ȩ��תʱ��
		for (int j = 0; j < proString.length; j++) {
			int createTime = Integer.parseInt(proString[j][1]);
			int requireTime = Integer.parseInt(proString[j][2]);
			int priority = Integer.parseInt(proString[j][3]);
			int t = Integer.parseInt(proString[j][4]);
			Time time = new Time(proString[j][0], 
					t-createTime,
					(double)((t-createTime)*100/requireTime)/100);
			saveTime.add(time);
		}
		System.out.println();
		method.printTurnTime(saveTime);
		for (int j = 0; j < proString.length; j++) {
			this.list.get(j).createTime = Integer.parseInt(proString[j][1]);
			this.list.get(j).requireTime = Integer.parseInt(proString[j][2]);
			this.list.get(j).priority = Integer.parseInt(proString[j][3]);
		}
    }
    
	/**
	 * �����ȷ��񣬶̽������ȣ�������ռʽ���ȼ���,����Ӧ�����ȷ��Ļ���
	 */
	public void base(int key){
		ArrayList<Lists> process = new ArrayList<Lists>(this.list);
		
		ArrayList<Time> saveTime = new ArrayList<Time>();
		//overTime ��¼��ǰ��ʱ��	next ��һ��Ҫִ�еĽ���
		int length = process.size();
		int overTime=this.firstTime,i=1+this.firstTime,next = 0;
		method.printName(key);
		for (int j = 0; j < length; j++) {
			switch (key) {
			case 0:
				next = method.findFirst(new ArrayList<Lists>(process),overTime);
				break;//�����ȷ���
			case 1:		
				next = method.findSPF(new ArrayList<Lists>(process),overTime);
				break;//�̽�������
			case 2:
				next = method.findPriority(new ArrayList<Lists>(process),overTime);
				break;//������ռʽ���ȼ���
			case 3:
				next = method.findResponse(new ArrayList<Lists>(process),overTime);
				break;//����Ӧ�����ȷ�
			}
			System.out.print(process.get(next).name+": ");
			overTime = i + process.get(next).requireTime;
			for (; i < overTime; i++) {
				System.out.print(i+",");
			}
			System.out.println();
			Time time = new Time(
					process.get(next).name,
					overTime-process.get(next).createTime-1,
					(double)((overTime-process.get(next).createTime-1)*100/process.get(next).requireTime)/100);
			saveTime.add(time);
			process.remove(next);
		}	
		method.printTurnTime(saveTime);
	}
}
