package he;

public class juli {
	static int a[][] = {{1,1},{0,0},{4,5},{6,1},{7,1}};
	static double min = juli(0, 1);
	public static void main(String[] args) {
		int mid;
		paixu(a);							//��a�����������
		mid = getmid();						//�ֿ顣�õ��м�ĵ���±�
		kuai(0, mid);						//�ҵ���߿����С����
		kuai(mid, a.length-1);				//�ҵ��ұ߿����С����
		zhongkuai(min,mid);					//����Сֵ������м��
		System.out.println(min+" "+mid);
	}
	public static void zhongkuai(double min,int mid) {//�ҵ�����Ϊmin����ĵ���±�
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
	public static int getmid() {//�ҵ������м�ֵ����������±�
		//�ʺϷ�ɢ���ȵĵ��󣬲��ʺϲ����ܼ��ĵ���
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
	public static double kuai(int index,int end) {//�������±�indexΪ��㵽end��һ�����е���̾��롣
		//���
		double mmm = 0;
		for (int i = index; i <= end; i++) {
			for (int j = i+1; j <= end; j++) {
				if (juli(i, j) < min) {//����ÿ������С��������롣
					min = juli(i,j);
				}
			}
		}
		return min;
	}
	public static void paixu(int a[][]) {//����������
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
	public static double juli(int d1,int d2) {//����������֮��ľ��롣
		//���ù��ɶ�������ƽ���͵��ڵ�����ƽ����
		double c1 = 0,c2 = 0;							
		double xie = 0.0;
		c1 = Math.abs(a[d1][0] - a[d2][0]);
		c2 = Math.abs(a[d1][1] - a[d2][1]);
		xie = Math.sqrt(Math.pow(c1, 2)+Math.pow(c2, 2));
		return xie;
	}
}
