package he;

import java.util.Scanner;

public class shanshu {

	public static void main(String[] args) {
		String n;
		int s = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("����һ������N��");
		n = scan.nextLine();
		System.out.println("����Ҫɾ���ĸ���S��");
		s = scan.nextInt();
		int[] a = new int[n.length()];
		a = shift(n);
		run(a, s, 0, a.length);
		show(a);
	}
	public static void run(int a[],int s,int index,int lth){//sΪҪɾ�������ݣ�indexΪ������㣬lthΪ���鳤��
		//�ҵ�ǰS������С��֮���ǰ�ߵ���ɾ���㣬����ɾ����ΪX����
		//֮������ͱ�ɣ�ʣ�µ�������ɾ��S-X�������ݹ����
		if (s == 0) {
			return ;
		}
		int count = 0;
		if (lth == s) {//��ʣ��Ҫɾ�����������ʣ�೤�����ʱ��˵����ǰ����Ϊһ���������У�ֱ��������������Sλ��
			for (int i = a.length-1,j = 0 ; j<s ;i--,j++) {
				a[i] = 0;
			}
		}else{
			int min = index;
			for (int i = index; i <= s ; i++) {
				if (a[i] < a[min]) {
					min = i;
				}
			}
			for (int i = 0; i < min-index; i++) {
				a[i] = 0;
				count++;
			}
			if (count == 0) {//�����εݹ�ɾ������Ϊ0��ʱ��˵����ǰs+1����Ϊ�������У�������һλִ�С�
				run(a, s, index+1, lth-1);
			}else {
				run(a, s-count, index+count, lth-count);
			}
			
		}
	}
	public static void show(int a[]){//��ʾ������ʣ�µ���
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				System.out.print(a[i]+" ");
			}
		}
	}
	public static int[] shift(String n){//���ַ���ת��int����
		int[] a = new int[n.length()];
		for(int i = 0; i < n.length(); i++){   
			a[i] = Integer.parseInt( String.valueOf(n.charAt(i)));
		}
		return a;
	}
}
