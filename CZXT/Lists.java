package Demo;

public class Lists {
	//��������
	public String name;
	//���̿�ʼʱ��
	public int createTime;
	//����Ҫ��ʱ��
	public int requireTime;
	//���ȼ�
	public int priority;
	
	public Lists() {
		// TODO Auto-generated constructor stub
	}
	
	public Lists(String name,int createTime,int requireTime,int priority) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.createTime = createTime;
		this.requireTime = requireTime;
		this.priority = priority;
	}
	
	public void  show() {
		System.out.print(name+" "+createTime+" "+requireTime+ " " +priority+" ");
	}
}
