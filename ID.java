package card;
import java.util.*;

class card_
{
	Scanner c=new Scanner(System.in);
	long number;
	int dd,mm,yyyy;
	card_()
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

public class ID {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int id;
		Scanner p=new Scanner(System.in);
		HashMap<Integer,card_[]> Card= new HashMap<Integer,card_[]>();
		int cont;
		do
		{
			int y=0;
			System.out.println("Enter the operation you want to perform");
			System.out.println("1.Add card Details");
			System.out.println("2.Delete card Details");
			System.out.println("3.Modify card Details");
			y=p.nextInt();
			switch(y)
			{
			case 1:
				System.out.println("Enter User id");
				id=p.nextInt();
				System.out.println("Enter the number of cards");
				int n=p.nextInt();
				card_[] ca=new card_[n];
				for(int i=0;i<n;i++)
				{
					ca[i]=new card_();
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
				Card.put(id, ca);
				break;
			case 2:
				int no=0;
				System.out.println("Enter usr id");
				id=p.nextInt();
				int z=Card.get(id).length;
				card_[] tmp=new card_[z];
				for(int a=0;a<z;a++)
					tmp[a]=new card_();
				tmp=Card.get(id);
				System.out.println("Which card you want to delete");
				no=p.nextInt();
				card_[] xyz=new card_[tmp.length-1];
				for(int b=0,k=0;b<tmp.length;b++)
				{
					if(b==no)
						continue;
					xyz[k++]=tmp[b];
				}
				Card.put(id, xyz);
				System.out.println("Card Deleted Successfully");
				break;
			case 3:
				System.out.println("Enter usr id");
				id=p.nextInt();
				card_ x=new card_();
				System.out.println("Enter credit card number");
				long num=p.nextLong();
				System.out.println("Enter date of expiry");
				int day=p.nextInt();
				int month=p.nextInt();
				int year=p.nextInt();
				int l=Card.get(id).length;
				card_[] t=new card_[l];
				for(int a=0;a<l;a++)
				{
					t[a]=new card_();
				}
				t=Card.get(id);
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
					Card.put(id, t);
					System.out.println("Card Modified Successfully");
							
				}
					else
					System.out.println("Invalid card");
					
	
				break;
			default:
				System.out.println("Invalid Key");
				break;
			}
		System.out.println("Do you want to continue?");
		cont=p.nextInt();
		}
		while(cont==1);
		for(int i:Card.keySet())
		{
			System.out.println("ID:"+i);
			card_[] tempo=new card_[5];
			for(int f=0;f<5;f++)
				tempo[f]=new card_();
			tempo=Card.get(i);
			for(int j=0;j<tempo.length;j++)
			{
				if(tempo[j].number!=0 || tempo[j].dd!=0 || tempo[j].mm!=0 || tempo[j].yyyy!=0)
				{
					System.out.println("card_ Number:"+tempo[j].number);
					System.out.println("Date of expiry:"+tempo[j].dd+"/"+tempo[j].mm+"/"+tempo[j].yyyy);
				}
			}
		}

	}
}




