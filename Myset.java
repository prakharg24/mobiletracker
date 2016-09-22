package mycode;

public class Myset {
	listelem first = null;
	listelem last = null;
	listelem temp = null;
	public boolean IsEmpty(){
		if(first==null){
			return true;
		}
		else return false;
	}
	public boolean IsMember(Object o){
		int flag = 0;
		if((first!=null)&&(first==last)){
			if(first.data==o){
				flag = 1;
			}
		}
		else if(first!=null){
			temp = first;
			while(temp!=null){
				if(temp.data==o){
					flag = 1;
				}
				temp = temp.next;
			}
		}
		if(flag==0){
			return false;
		}
		else{ return true;}
	}
	public void Insert(Object o){
		listelem abc = new listelem();
		abc.data = o;
		if(first==null){
			first = abc;
			last = abc;
		}
		else if(first==last){
			first.next = abc;
			abc.prev = first;
			last = abc;
		}
		else{
			last.next = abc;
			abc.prev = last;
			last = abc;	
		}
	}
	public void Delete(Object o){
		if(first==null){
			
		}
		else if(first==last){
			if(first.data==o){
				first=null;
				last=null;
			}
		}
		else{
			if(first.data==o){
				first = first.next;
				first.prev = null;
			}
			else{
			temp = first;
			while(temp!=null){
				if(temp.data==o){
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
				}
				temp = temp.next;
			}
			}
		}	
	}
	public Myset Union(Myset a){
		Myset ans = new Myset();
		temp=first;
		while(temp!=null){
			ans.Insert(temp.data);
			temp = temp.next;
		}
		temp=a.first;
		while(temp!=null){
			if(!(ans.IsMember(temp))){
				ans.Insert(temp.data);
			}
			temp = temp.next;
		}
		return ans;
	}
	public Myset Intersection(Myset a){
		Myset ans = new Myset();
		Myset inter = new Myset();
		temp=first;
		while(temp!=null){
			ans.Insert(temp.data);
			temp = temp.next;
		}
		temp=a.first;
		while(temp!=null){
			if(ans.IsMember(temp)){
				inter.Insert(temp.data);
			}
			temp = temp.next;
		}
		return inter;
	}
}

class listelem{
	Object data;
	listelem next = null;
	listelem prev = null;
}

class MobilePhone{
	int id;
	boolean status = false;
	Exchange base;
	public MobilePhone(int number){
		id = number;
	}
	public int number(){
		return id;
	}
	public boolean status(){
		return status;
	}
	public void switchOn(){
		status = true;
	}
	public void switchOff(){
		status = false;
	}
	public Exchange location(){
		if(status){
			return base;
		}
		else return null;//add exception
	}
	public boolean isequal(Object o){
		MobilePhone m = (MobilePhone)o;
		if(id==m.id){
			return true;
		}
		else return false;
	}
}

class MobilePhoneSet{
	Myset localset = new Myset();
	Myset idset = new Myset();
	Exchange parent;
	public MobilePhoneSet(Exchange p){
		parent = p;
	}
	public void Insert(MobilePhone m){
		localset.Insert(m);
		idset.Insert(m.id);
		m.base = parent;
	}
	public boolean IsEmpty(){
		return localset.IsEmpty();
	}
	public boolean IsMember(MobilePhone m){
		int flag = 0;
		if((idset.first!=null)&&(idset.first==idset.last)){
			if((int)idset.first.data==(int)m.id){
				flag = 1;
			}
		}
		else if(idset.first!=null){
			idset.temp = idset.first;
			while(idset.temp!=null){
				if((int)idset.temp.data==(int)m.id){
					flag = 1;
				}
				idset.temp = idset.temp.next;
			}
		}
		if(flag==0){
			return false;
		}
		else{ return true;}
	}
	public void Delete(MobilePhone m){
		localset.Delete(m);
		idset.Delete(m.id);
		if(localset.first==null){
			
		}
		else if(localset.first==localset.last){
			if((int)idset.first.data==(int)m.id){
				idset.first=null;
				idset.last=null;
				localset.first=null;
				localset.last=null;
			}
		}
		else{
			if((int)idset.first.data==(int)m.id){
				localset.first = localset.first.next;
				localset.first.prev = null;
				idset.first = idset.first.next;
				idset.first.prev = null;
			}
			else if((int)idset.last.data==(int)m.id){
				localset.last = localset.last.prev;
				localset.last.next = null;
				idset.last = idset.last.prev;
				idset.last.next = null;
			}
			else{
			localset.temp = localset.first;
			idset.temp = idset.first;
			while(localset.temp!=null){
				if((int)idset.temp.data==(int)m.id){
					idset.temp.prev.next = idset.temp.next;
					idset.temp.next.prev = idset.temp.prev;
					localset.temp.prev.next = localset.temp.next;
					localset.temp.next.prev = localset.temp.prev;
				}
				idset.temp = idset.temp.next;
				localset.temp = localset.temp.next;
			}
			}
		}
	}
}