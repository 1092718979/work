package Demo;

public class Lists {
	//进程名称
	public String name;
	//进程开始时间
	public int createTime;
	//进程要求时间
	public int requireTime;
	//优先级
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
