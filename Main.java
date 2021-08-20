package card;
import java.util.*;
class User
{
	int id;
	String user_name;
	User()
	{
		id=0;
		user_name="";
	}
	public void add_user()
	{
		System.out.println("Enter usr id");
		Scanner u=new Scanner(System.in);
		id=u.nextInt();
		u.nextLine();
		System.out.println("Enter user name");
		user_name=u.nextLine();
	}
}

class card
{
	Scanner c=new Scanner(System.in);
	long number;
	int dd,mm,yyyy;
	card()
	{
		number=0;
		dd=0;
		mm=0;
		yyyy=0;
	}
	public void add_card(long number,int dd,int mm,int yyyy)
	{
		this.number=number;
		this.dd=dd;
		this.mm=mm;
		this.yyyy=yyyy;
	}
	public boolean card_validation(long number,int dd,int mm,int yyyy)
	{
		int sum1=0;
		String x=String.valueOf(number);
		for(int j=x.length()-2;j>=0;j-=2)
		{
			if(x.charAt(j)<5)
			{
				sum1+=Integer.parseInt(x.charAt(j)+"")*2;
			}
			else
			{
				int xyz=0;
				xyz=Integer.parseInt(x.charAt(j)+"")*2;
				xyz=xyz/10+xyz%10;
				sum1+=xyz;
			}
		}
		int sum2=0;
		for(int j=x.length()-1;j>=0;j-=2)
		{
			sum2+=Integer.parseInt(x.charAt(j)+"");
		}
		int total=sum1+sum2;
		if(total%10==0 && x.length()>=13 && x.length()<=16)
		{
			if(mm==2 && yyyy%4==0 && dd<=29)
				return true;
			else if(mm==2 && yyyy%4!=0 && dd<=28)
				return true;
			else if(dd<=31 && (mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12) && yyyy>=2021 && yyyy<9999)
				return true;
			else if(dd<=30 && (mm==4 || mm==6 || mm==9 || mm==11) && yyyy>=2021 && yyyy<9999)
				return true;
			else
				return false;
		}
		else
			return false;
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner p=new Scanner(System.in);
		HashMap<User,card[]> Card= new HashMap<User,card[]>();
		int cont;
		do
		{
			int y=0;
			System.out.println("Enter the operation you want to perform");
			System.out.println("1.Add Card Details");
			System.out.println("2.Delete Card Details");
			System.out.println("3.Modify Card Details");
			y=p.nextInt();
			switch(y)
			{
			case 1:
				User use=new User();
				use.add_user();
				System.out.println("Enter the number of cards");
				int n=p.nextInt();
				card[] ca=new card[n];
				for(int i=0;i<n;i++)
				{
					ca[i]=new card();
					System.out.println("Enter credit card number");
					long numb=p.nextLong();
					System.out.println("Enter date of expiry");
					int d=p.nextInt();
					int m=p.nextInt();
					int yy=p.nextInt();
					if(!ca[i].card_validation(numb, d, m, yy))
					{
						System.out.println("Invalid Card");
						i--;
						n--;
					}
					else
					{
						ca[i].add_card(numb, d, m, yy);
						System.out.println("Card Added Successfully");
					}
				}
				Card.put(use, ca);
				break;
			case 2:
				int no=0;
				System.out.println("Enter usr id");
				int id=p.nextInt();
				p.nextLine();
				System.out.println("Enter user name");
				String user_name=p.nextLine();
				for(User i:Card.keySet())
				{
					if(id==i.id)
					{
				
						int z=Card.get(i).length;
						card[] tmp=new card[z];
						for(int a=0;a<z;a++)
							tmp[a]=new card();
						tmp=Card.get(i);
						System.out.println("Which card you want to delete");
						no=p.nextInt();
						card[] xyz=new card[tmp.length-1];
						for(int b=0,k=0;b<tmp.length;b++)
						{
							if(b==no)
								continue;
							xyz[k++]=tmp[b];
						}
						Card.put(i, xyz);
						System.out.println("Card Deleted Successfully");
					}
					else
						System.out.println("User Not Found");
				}
				break;
			case 3:
				System.out.println("Enter usr id");
				int id1=p.nextInt();
				p.nextLine();
				System.out.println("Enter user name");
				String username=p.nextLine();
				for(User i:Card.keySet())
				{
					if(id1==i.id)
					{
						card x=new card();
						System.out.println("Enter credit card number");
						long num=p.nextLong();
						System.out.println("Enter date of expiry");
						int day=p.nextInt();
						int month=p.nextInt();
						int year=p.nextInt();
						int l=Card.get(i).length;
						card[] t=new card[l];
						for(int a=0;a<l;a++)
						{
							t[a]=new card();
						}
						t=Card.get(i);
						if(x.card_validation(num,day,month,year))
						{
							x.add_card(num,day,month,year);
							System.out.println("Enter the card you want to edit");
							int nu=p.nextInt();
							for(int a=0;a<1;a++)
							{
								if(a==nu)
									t[a]=x;
							}
							Card.put(i, t);
							System.out.println("Card Modified Successfully");
							
						}
						else
							System.out.println("Invalid Card");
					}
					else
						System.out.println("Card Not Found");
				}
				break;
			default:
					System.out.println("Invalid Key");
					break;
			}
		System.out.println("Do you want to continue?");
		cont=p.nextInt();
		}
		while(cont==1);
		for(User i:Card.keySet())
		{
			System.out.println("ID:"+i.id);
			System.out.println("Name:"+i.user_name);
			card[] tempo=new card[5];
			for(int f=0;f<5;f++)
				tempo[f]=new card();
			tempo=Card.get(i);
			for(int j=0;j<tempo.length;j++)
			{
				if(tempo[j].number!=0 || tempo[j].dd!=0 || tempo[j].mm!=0 || tempo[j].yyyy!=0)
				{
					System.out.println("Card Number:"+tempo[j].number);
					System.out.println("Date of expiry:"+tempo[j].dd+"/"+tempo[j].mm+"/"+tempo[j].yyyy);
				}
			}
		}

	}

}
