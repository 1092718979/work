package Demo;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;

public class Process {
	Method method = new Method();
	ArrayList<Lists> list;
	int firstTime = 0;
	/**
	 * 构造函数
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
     * 先来先服务
     */
    public void FCFS(){
        base(0);
    }
    
    /**
     * 短进程优先
     */
    public void SPF(){
        base(1);
    }
    
    /**
     * 高响应比优先法
     */
    public void response(){
        base(3);
    }
    
    /**
     * 不可抢占式优先级法
     */
    public void priorityNotPossession(){
       base(2);
    }
    
    /**
     * 时间片轮转
     * 用优先级表示进程在时间片中的轮转
     *      0表示没有轮转，1表示已经轮转过。
     *      ArrayList更改指问题。
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
    		proString[i][4] = 0+"";//结束时间
		}
		//overTime 记录当前的时间	next 下一个要执行的进程号
		int overTime=0,i=1,next = 0;
		int totalTime = method.totalTime(process);
		System.out.println("时间片轮转:");
		System.out.print("当前轮转时序为:");
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
		//计算周转时间和带权周转时间
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
     * 可抢占式优先级法
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
    		proString[i][4] = 0+"";//结束时间
		}
		//overTime 记录当前的时间	next 下一个要执行的进程号
		int overTime=0,i=1,next = 0;
		int totalTime = method.totalTime(process);
		System.out.println("可抢占式优先级法:");
		System.out.print("当前轮转时序为:");
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
		//计算周转时间和带权周转时间
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
	 * 先来先服务，短进程优先，不可抢占式优先级法,高响应比优先法的基类
	 */
	public void base(int key){
		ArrayList<Lists> process = new ArrayList<Lists>(this.list);
		
		ArrayList<Time> saveTime = new ArrayList<Time>();
		//overTime 记录当前的时间	next 下一个要执行的进程
		int length = process.size();
		int overTime=this.firstTime,i=1+this.firstTime,next = 0;
		method.printName(key);
		for (int j = 0; j < length; j++) {
			switch (key) {
			case 0:
				next = method.findFirst(new ArrayList<Lists>(process),overTime);
				break;//先来先服务
			case 1:		
				next = method.findSPF(new ArrayList<Lists>(process),overTime);
				break;//短进程优先
			case 2:
				next = method.findPriority(new ArrayList<Lists>(process),overTime);
				break;//不可抢占式优先级法
			case 3:
				next = method.findResponse(new ArrayList<Lists>(process),overTime);
				break;//高响应比优先法
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
