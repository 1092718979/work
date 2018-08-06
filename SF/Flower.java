package haha;

import java.math.BigInteger;

public class Flower {
	static BigInteger[] num = new BigInteger[10];
	
	public static void main(String[] args) {
		fuzhi(num);
		int cishu[] = {0,0,0,0,0,0,0,0,0,0};	
		meiju(cishu,0,21);
	}
	public static void fuzhi(BigInteger[] num){		
		for(int i = 0;i<10;i++)
		{
			num[i] = new BigInteger(""+i);
			num[i] = num[i].pow(21);
		}
	}
	public static void meiju(int cishu[],int index,int n){
		if (index == 9) {
			cishu[9] = n;
			jisuan(cishu, num);
		}else{
			for (int i = 0; i <= n; i++) {
				cishu[index] = i;
				meiju(cishu, index+1, n-i);
			}
		}
	}	
	public static void jisuan(int cishu[],BigInteger num[]){
		BigInteger zong = new BigInteger("0");
		for (int i = 0; i < cishu.length; i++) {
			zong = zong.add(num[i].multiply(new BigInteger(""+cishu[i])));
		}
		String str = zong.toString();
		if (str.length() != 21) {
			return ;
		}else{
			char ch[] = str.toCharArray();
			int a[] = {0,0,0,0,0,0,0,0,0,0};
			for (int i = 0; i < ch.length; i++) {
				switch (ch[i]) {
				case '0': a[0]++;break;
				case '1': a[1]++;break;
				case '2': a[2]++;break;
				case '3': a[3]++;break;
				case '4': a[4]++;break;
				case '5': a[5]++;break;
				case '6': a[6]++;break;
				case '7': a[7]++;break;
				case '8': a[8]++;break;
				case '9': a[9]++;break;
				}
			}
			for (int i = 0; i < a.length; i++) {
				if (cishu[i] != a[i]) {
					return ;
				}
			}
			System.out.println(str);
		}
		
	}




}
