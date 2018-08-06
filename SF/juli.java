package he;

public class juli {
	static int a[][] = {{1,1},{0,0},{4,5},{6,1},{7,1}};
	static double min = juli(0, 1);
	public static void main(String[] args) {
		int mid;
		paixu(a);							//对a数组进行排序
		mid = getmid();						//分块。得到中间的点的下标
		kuai(0, mid);						//找到左边块的最小距离
		kuai(mid, a.length-1);				//找到右边块的最小距离
		zhongkuai(min,mid);					//以最小值画块的中间块
		System.out.println(min+" "+mid);
	}
	public static void zhongkuai(double min,int mid) {//找到距离为min最近的点的下标
		int zuo = 0,you = a.length-1;
		for (int i = mid; i > 0; i--) {
			if (mid-i > min) {
				zuo = i;
				break;
			}
		}
		for (int i = mid; i < a.length; i++) {
			if (i-mid > min){
				you = i;
				break;
			}
		}
		kuai(zuo, you);
		
	}
	public static int getmid() {//找到距离中间值最近的数组下标
		//适合分散均匀的点阵，不适合部分密集的点阵
		int midlenth = a[a.length-1][0]/2;
		int mid = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i][0] > midlenth) {
				mid = i;
				break;
			}
		}
		return mid;
	}
	public static double kuai(int index,int end) {//计算以下表index为起点到end的一个块中的最短距离。
		//穷举
		double mmm = 0;
		for (int i = index; i <= end; i++) {
			for (int j = i+1; j <= end; j++) {
				if (juli(i, j) < min) {//保留每距离最小两个点距离。
					min = juli(i,j);
				}
			}
		}
		return min;
	}
	public static void paixu(int a[][]) {//对数组排序。
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				if (a[i][0] > a[j][0]) {
					int i1 = a[i][0],j1 = a[i][1];
					a[i][0] = a[j][0];
					a[j][0] = i1;
					a[i][1] = a[j][1];
					a[j][1] = j1;
				}
			}
		}
	}
	public static double juli(int d1,int d2) {//计算两个点之间的距离。
		//利用勾股定理，两边平方和等于第三遍平方。
		double c1 = 0,c2 = 0;							
		double xie = 0.0;
		c1 = Math.abs(a[d1][0] - a[d2][0]);
		c2 = Math.abs(a[d1][1] - a[d2][1]);
		xie = Math.sqrt(Math.pow(c1, 2)+Math.pow(c2, 2));
		return xie;
	}
}
