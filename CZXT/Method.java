package Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Method {
	/**
	 * ����
	 */
	public static ArrayList<Lists> input(){
		ArrayList<Lists> list = new ArrayList();
		Scanner scanner = new Scanner(System.in);
		System.out.println("���������������");
		int a = scanner.nextInt();
		scanner.nextLine();
		System.out.println("���������,���ս������ƣ���ʼʱ�䣬Ҫ��ʱ�䣬���ȼ����룺");
		for (int i = 0; i < a; i++) {
			String str = scanner.nextLine();
			String arr[] = str.split("\\s+");
			Lists ls= new Lists();
			ls.name = arr[0];
			ls.createTime = Integer.parseInt(arr[1]);
			ls.requireTime = Integer.parseInt(arr[2]);
			ls.priority = Integer.parseInt(arr[3]);
			list.add(ls);
		}
		return list;
	}
	
	
	
	 /**
     * �ҵ���һ��ִ�еĽ���--�����ȷ���
     */
    public int findFirst(ArrayList<Lists> pro,int nowTime){
    	ArrayList<Lists> proList =new ArrayList<Lists>(pro);
    	ArrayList<Lists> list = conformTime(pro, nowTime);
    	Lists little = list.get(0);
    	for (int i = 0; i < pro.size(); i++) {
			if (list.get(i).createTime < little.createTime) {
				little = list.get(i);
			}
		}
    	return compare(proList, little);
    }
    
    /**
     * �ҵ���һ��ִ�еĽ���--�̽�������
     */
    public int findSPF(ArrayList<Lists> pro,int nowTime){
    	ArrayList<Lists> proList =new ArrayList<Lists>(pro);
    	ArrayList<Lists> list = conformTime(pro, nowTime);
    	Lists little = list.get(0);
    	for (int i = 0; i < pro.size(); i++) {
			if (list.get(i).requireTime < little.requireTime) {
				little = list.get(i);
			}
		}
    	return compare(proList, little);
    }
    
    /**
     * �ҵ���һ��ִ�еĽ���--����Ӧ�����ȷ�
     */
    public int findResponse(ArrayList<Lists> pro,int nowTime){
    	ArrayList<Lists> proList =new ArrayList<Lists>(pro);
    	ArrayList<Lists> list = conformTime(pro, nowTime);
    	int little = 0;
    	double rank[] = new double[list.size()];
    	for (int i = 0; i < list.size(); i++) {
			rank[i] = 
					(nowTime-1-list.get(i).createTime+list.get(i).requireTime)
					/
					list.get(i).requireTime;
		}
    	for (int i = 0; i < rank.length; i++) {
			if (rank[i] > rank[little]) {
				little = i;
			}
		}
    	return compare(proList, list.get(little));
    }
    
    /**
     * �ҵ���һ��ִ�еĽ���--������ռʽ���ȼ���
     */
    public int findPriority(ArrayList<Lists> pro,int nowTime){
    	ArrayList<Lists> proList =new ArrayList<Lists>(pro);
    	ArrayList<Lists> list = conformTime(pro, nowTime);
    	Lists little = list.get(0);
    	for (int i = 0; i < pro.size(); i++) {
			if (list.get(i).priority < little.priority) {
				little = list.get(i);
			}
		}
    	return compare(proList, little);
    }
    
    /**
     * �ҵ���һ��ִ�еĽ���--ʱ��Ƭ��ת
     */
    public int findRR(ArrayList<Lists> pro,int nowTime){
    	ArrayList<Lists> proList =new ArrayList<Lists>(pro);
    	ArrayList<Lists> list = conformTime(pro, nowTime);
    	for (int i = 0; i < pro.size(); i++) {
			if (list.get(i).priority == 0) {
				return compare(proList, proList.get(i));
			}
		}
    	return 0;
    }
    
    /**
     * �ж�ʱ��Ƭ�Ƿ���ת��һ��
     */
    public boolean check(ArrayList<Lists> list){
    	for (int i = 0; i < list.size(); i++) {
			if (list.get(i).priority == 0) {
				return false;
			}
		}
    	return true;
    }
    
    /**
     * ����ʱ��Ƭ��ʱ��
     */
    public Time saveTimeForRR(ArrayList<Lists> list,String name,int overTime) {
    	int point = 0;
		for (int i = 0; i < list.size(); i++) {
			if (name == list.get(i).name) {
				point = i;
				break;
			}
		}
		System.out.println(list.get(point).requireTime);
		Time time = new Time(
		list.get(point).name,
		overTime-list.get(point).createTime,
		(double)((overTime-list.get(point).createTime-1)*100/list.get(point).requireTime)/100);
		return time;
	}
    
    /**
     * �ҵ�����ʱ�������
     */
    public ArrayList<Lists> conformTime(ArrayList<Lists> pro,int nowTime){
    	int size = pro.size();
    	for (int i = 0; i < size; i++) {
    		for (int j = 0; j < pro.size(); j++) {
				if(pro.get(j).createTime > nowTime){
					pro.remove(j);
				}
			}
		}
    	return pro; 
    }
    
    /**
     * �ҵ������pro�е��±�
     */
    public int compare(ArrayList<Lists> pro,Lists little){
    	for (int i = 0; i < pro.size(); i++) {
			if (little.name == pro.get(i).name){
				return i;
			}
		}
    	return 0;
    }
    
    /**
     * �۲�List�����ڲ���
     */
    public void showList(ArrayList<Lists> list){
    	for (int i = 0; i < list.size(); i++) {
			list.get(i).show();
		}
    	System.out.println();
    }
    
    /**
     * ���Ҫִ�н��ǵ�����
     */
    public void printName(int key){
    	switch (key) {
		case 0:
			System.out.println("�����ȷ���:");
			break;
		case 1:
			System.out.println("�̽�������:");
			break;
		case 2:
			System.out.println("������ռʽ���ȼ���:");
			break;
		case 3:
			System.out.println("����Ӧ�����ȷ�:");
			break;
		}
    }
    
    /**
     * ��ӡ��תʱ��ʹ�Ȩ��תʱ��
     */
    public void printTurnTime(ArrayList<Time> list){
    	double count1 = 0.0;
    	double count2 = 0.0;
    	for (int i = 0; i < list.size(); i++) {
    		count1 += list.get(i).turnTime;
    		count2 += list.get(i).carryTime;
		}
    	Time time = new Time("ƽ��",count1/list.size(),count2/list.size());
    	list.add(time);
    	System.out.print("��������      :");
    	for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).name+"  ,");
		}
    	System.out.println();
    	System.out.print("��תʱ��      :");
    	for (int i = 0; i < list.size(); i++) {
    		System.out.print(list.get(i).turnTime+" ,");
		}
    	System.out.println();
    	System.out.print("��Ȩ��תʱ��:");
    	for (int i = 0; i < list.size(); i++) {
    		System.out.print(list.get(i).carryTime+" ,");
		}
    	System.out.println();
    	System.out.println();
    }
    
    
    /**
     * ��������Ҫ��ʱ��
     */
    public int totalTime(ArrayList<Lists> list) {
		int totalTime = 0;
    	for (int i = 0; i < list.size(); i++) {
			totalTime += list.get(i).requireTime;
		}
    	return totalTime;
	}
    
}





















































