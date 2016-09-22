package mycode;

public class Exchange{
	MobilePhoneSet data = new MobilePhoneSet(this);
	Exchange next = null;
	Exchange prev = null;
	Exchange parent = null;
	Exchange temp = null;
	ExchangeList children = new ExchangeList(this);
	int id;
	public Exchange(int number){
		id = number;
	}
	public Exchange parent(){
		return parent;
	}
	public int numChildren(){
		temp = children.first;
		int counter = 0;
		while(temp!=null){
			counter++;
			temp = temp.next;
		}
		return counter;
	}
	public Exchange child(int i)throws NullPointerException{
		temp = children.first;
		for(int k=0;k<i-1;k++){
			if(temp==null){
				throw new NullPointerException();
			}
			temp = temp.next;
		}
		return temp;
	}
	public Boolean isRoot(){
		if(parent==null){
			return true;
		}
		else return false;
	}
	public void update(){
		temp = children.first;
		while(temp!=null){
			//incomplete
		}
	}
	public RoutingMapTree subtree(int i){
		RoutingMapTree r = new RoutingMapTree();
		r.root = this.child(i);
		return r;
	}
	public MobilePhoneSet residentSet(){
		temp = children.first;
		while(temp!=null){
			//incomplete
		}
		return data;
	}
}

class ExchangeList{
	Exchange parent = null;
	Exchange first = null;
	Exchange last = null;
	public ExchangeList(Exchange a){
		parent = a;
	}
	public void add(Exchange a){
		a.parent = parent;
		if(first==null){
			first = a;
			last = a;
		}
		else if(first==last){
			first.next = a;
			a.prev = first;
			last = a;
		}
		else{
			last.next = a;
			a.prev = last;
			last = a;	
		}
	}
	public Exchange findparent(){
		return parent;
	}
}
