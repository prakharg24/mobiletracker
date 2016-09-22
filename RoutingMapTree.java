package mycode;

import java.util.Scanner;

public class RoutingMapTree{
	Exchange root;
	Exchange temp;
	public RoutingMapTree(){
		Exchange a = new Exchange(0);
		root = a;
	}
	public Exchange findExchange(int id){
		Exchange ans = null;
		temp = root;
		if(temp.id==id){
			ans = temp;
		}
		else if(temp.numChildren()!=0){
			for(int i=0;i<temp.numChildren();i++){
				if(temp.subtree(i+1).findExchange(id)!=null){
					ans = temp.subtree(i+1).findExchange(id);
				}
			}
		}
		return ans;
	}
	public Exchange findPhone(MobilePhone a){
		Exchange ans = null;
		if(root.data.IsMember(a)){
			temp = root;
			for(int i=0;i<temp.numChildren();i++){
				if(temp.child(i+1).data.IsMember(a)){
					ans = temp.child(i+1);
					if(temp.subtree(i+1).findPhone(a)!=null){
						ans = temp.subtree(i+1).findPhone(a);
					}
				}
			}
		}
		return ans;
	}
	public boolean containsNode(Exchange a){
		int flag = 0;
		temp = root;
		if(a==root){
			flag = 1;
		}
		else{
			for(int i=0;i<temp.numChildren();i++){
				if(temp.subtree(i+1).containsNode(a)){
					flag = 1;
					break;
				}
			}
		}
		if(flag==1){
			return true;
		}
		else return false;
	}
	public void switchOn(MobilePhone a, Exchange b){
		a.switchOn();
		temp = b;
		while(temp.parent!=null){
			temp.data.Insert(a);
			temp = temp.parent;
		}
		temp.data.Insert(a);
	}
	public void switchOff(MobilePhone a)throws NullPointerException{
		temp = this.findPhone(a);
		if(temp==null){
			throw new NullPointerException();
		}
		listelem l = temp.data.localset.first;
		listelem i = temp.data.idset.first;
		MobilePhone m = null;
		while(l!=null){
			if((int)i.data==(int)a.id){
				m = (MobilePhone)l.data;
			}
			l = l.next;
			i = i.next;
		}
		m.switchOff();
		while(temp.parent!=null){
			temp.data.Delete(a);
			temp = temp.parent;
		}
		temp.data.Delete(a);
	}
	public Exchange lowestRouter(Exchange a, Exchange b){
		temp = null;
		int flag = 0;
		if(a.id==b.id){
			temp = a;
		}
		else{
			Exchange tempa = a;
			Exchange tempb = b;
			while(tempa!=null){
				while(tempb!=null){
					if(tempa.id==tempb.id){
						temp = tempa;
						flag=1;
						break;
					}
					tempb = tempb.parent;
				}
				if(flag==0){
					tempb = b;
					tempa = tempa.parent;	
				}
				else{break;}
			}
		}
		return temp;
	}
	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		Exchange moba = this.findPhone(a);
		Exchange mobb = this.findPhone(b);
		Exchange node = this.lowestRouter(moba, mobb);
		ExchangeList ans = new ExchangeList(null);
		ExchangeList ans1 = new ExchangeList(null);
		while(moba.id!=node.id){
			Exchange c = new Exchange(moba.id);
			ans.add(c);
			moba = moba.parent;
		}
		while(mobb.id!=node.id){
			Exchange c = new Exchange(mobb.id);
			ans1.add(c);
			mobb = mobb.parent;
		}
		Exchange c = new Exchange(moba.id);
		ans.add(c);
		while(ans1.last!=null){
			ans.add(ans1.last);
			ans1.last = ans1.last.next;
		}
		return ans;
	}
	public void movePhone(MobilePhone a, Exchange b){
		this.switchOff(a);
		this.switchOn(a, b);
	}
	public void performAction(String actionMessage) {
		System.out.print(actionMessage + ": ");
		Scanner in = new Scanner(actionMessage);
		String s;
		try{
			s = in.next();
		}catch(Exception e){
			System.out.println("No Query entered");
			return;
		}
		try{
		if(s.equals("addExchange")){
			int a = in.nextInt();
			int b = in.nextInt();
			try{
				temp = this.findExchange(a);
				Exchange input = new Exchange(b);
				temp.children.add(input);
			}catch (NullPointerException e){
				System.out.print("Error - The Exchange " + a + " does not exist");
			}
			System.out.println();
		}
		else if(s.equals("switchOnMobile")){
			int a = in.nextInt();
			int b = in.nextInt();
			try{
				temp = this.findExchange(b);
				MobilePhone input = new MobilePhone(a);
				if(temp.data.IsMember(input)){
				
				}
				else{
					this.switchOn(input, temp);
				}
			}catch (NullPointerException e){
				System.out.print("Error - The Exchange " + b + " does not exist");
			}
			System.out.println();
		}
		else if(s.equals("switchOffMobile")){
			int a = in.nextInt();
			MobilePhone input = new MobilePhone(a);
			try{
				this.switchOff(input);
			}catch(NullPointerException e){
				System.out.print("Error - The MobilePhone " + a + " doest not exist");
			}
			System.out.println();
		}
		else if(s.equals("queryNthChild")){
			int a = in.nextInt();
			int b = in.nextInt();
			temp = this.findExchange(a);
			if(temp==null){
				System.out.println("Error - The Exchange " + a + " does not exist");
				return;
			}
			try{
				System.out.println(temp.child(b+1).id);
			}catch(NullPointerException e){
				System.out.println("Error - The Exchange " + a + " have less than " + b + " child");
			}
		}
		else if(s.equals("queryMobilePhoneSet")){
			int a = in.nextInt();
			try{
				temp = this.findExchange(a);
				listelem t;
				t = temp.data.idset.first;
				while(t!=null){
					System.out.print(t.data + " ");
					t = t.next;
				}
				System.out.println();
			}catch(NullPointerException e){
				System.out.println("Error - The Exchange " + a + " does not exist");
			}
		}
		else if(s.equals("findPhone")){
			int a = in.nextInt();
			MobilePhone m = new MobilePhone(a);
			temp = this.findPhone(m);
			try{
				System.out.println(temp.id);
			}catch(NullPointerException e){
				System.out.println("Error - The Mobile Phone " + a + " does not exist");
			}
		}
		else if(s.equals("lowestRouter")){
			int a = in.nextInt();
			int b = in.nextInt();
			Exchange e1 = this.findExchange(a);
			Exchange e2 = this.findExchange(b);
			try{
				System.out.println(this.lowestRouter(e1, e2).id);
			}catch(NullPointerException e){
				System.out.println("Error - The Exchange " + a + " or " + b + " does not exist");
			}
		}
		else if(s.equals("findCallPath")){
			int a = in.nextInt();
			int b = in.nextInt();
			MobilePhone m1 = new MobilePhone(a);
			MobilePhone m2 = new MobilePhone(b);
			Exchange et = this.findPhone(m2);
			try{
				ExchangeList el = this.routeCall(m1, m2);
				System.out.print(el.first.id);
				el.first = el.first.next;
				while(el.first!=null){
					System.out.print(", " + el.first.id);
					el.first = el.first.next;
				}
				System.out.print(", " + et.id);
				System.out.println();
			}catch(NullPointerException e){
				System.out.println("Error - The Mobile Phone " + a + " or " + b + " does not exist");
			}
		}
		else if(s.equals("movePhone")){
			int a = in.nextInt();
			int b = in.nextInt();
			MobilePhone m1 = new MobilePhone(a);
			Exchange e1 = this.findExchange(b);
			try{
				this.movePhone(m1, e1);
				System.out.println();
			}catch(NullPointerException e){
				System.out.println("Error - The Mobile Phone " + a + " or Exchange " + b + " does not exist");
			}
		}
		else{
			System.out.println("Query not recognised");
		}
		}catch(Exception e){
			System.out.println("Query not in the desired format");
		}
	}
}
