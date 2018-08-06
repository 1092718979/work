package he;

import java.util.Scanner;

public class shanshu {

	public static void main(String[] args) {
		String n;
		int s = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("输入一个整数N：");
		n = scan.nextLine();
		System.out.println("输入要删除的个数S：");
		s = scan.nextInt();
		int[] a = new int[n.length()];
		a = shift(n);
		run(a, s, 0, a.length);
		show(a);
	}
	public static void run(int a[],int s,int index,int lth){//s为要删掉的数据，index为数组起点，lth为数组长度
		//找到前S个数最小的之后把前边的数删除点，假设删掉的为X个数
		//之后问题就变成，剩下的数组中删掉S-X个数，递归调用
		if (s == 0) {
			return ;
		}
		int count = 0;
		if (lth == s) {//当剩下要删点的数和数组剩余长度相等时，说明当前数组为一个递增数列，直接闪电数组最后的S位。
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
			if (count == 0) {//当本次递归删掉的数为0个时，说明当前s+1个数为递增数列，跳到下一位执行。
				run(a, s, index+1, lth-1);
			}else {
				run(a, s-count, index+count, lth-count);
			}
			
		}
	}
	public static void show(int a[]){//显示数组中剩下的数
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				System.out.print(a[i]+" ");
			}
		}
	}
	public static int[] shift(String n){//把字符串转成int数组
		int[] a = new int[n.length()];
		for(int i = 0; i < n.length(); i++){   
			a[i] = Integer.parseInt( String.valueOf(n.charAt(i)));
		}
		return a;
	}
}
