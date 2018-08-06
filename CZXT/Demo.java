package Demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
	public static void main(String[] args) {
		ArrayList<Lists> list = new ArrayList();
		//输入进程
//		Method method = new Method();
//		list = method.input();
		//测试用
		list.add(new Lists("p1",0,2,2));
		list.add(new Lists("p2",1,6,1));
		list.add(new Lists("p3",2,1,4));
		list.add(new Lists("p4",3,5,3));
		Process process = new Process(list);
		process.FCFS();
		process.SPF();
		process.response();
		process.priorityNotPossession();
		process.RR();
		process.priorityPossession();
	}
}	
