import java.util.Scanner;

public class LinkedListShellSort {

	private Node first, last;
	static int count=0;
	static int exch=0;
	static int cmp=0;
	static int pass=0;

	private class Node{
		int item;
		Node next;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public void enqueue(int item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next=null;
		if (isEmpty()) 
			first = last;
		else 
			oldlast.next = last;
		count++;

	}

	static boolean isSorted(LinkedListShellSort a){
		for (Node i = a.first; i.next != null ; i=i.next)
			if(i.item>i.next.item)
				return false;
		return true;
	}

	static void sort(LinkedListShellSort a){
		for (Node i = a.first; i.next != null ; i=i.next)
			if(i.item>i.next.item){
				swap(a,i,i.next);
			}
		printLL(a);
		if(!isSorted(a))
			sort(a);	
	}

	static void hsort(LinkedListShellSort a){
		int N = count;
		int h = 1;
		Node[] array=new Node[count];
		int k=0;
		for (Node i = a.first; i != null; i=i.next){
			array[k]=i;
			k++;
		}
		while (h < N/3)
			h = 3*h + 1;
		while (h >= 1){
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && less(array[j], array[j-h]); j -= h, pass++){
					exch(array, j, j-h);
					exch++;
				}
			h = h/3;
			LinkedListShellSort b = new LinkedListShellSort();
			for(int g=0; g<array.length-1; g++)
				b.enqueue(array[g].item);
			a=b;
			b=null;
			printLL(a);
		}
	}

	private static boolean less(Node a, Node b){
		cmp++;
		return a.item<b.item;
	}

	private static void exch(Node[] arr, int i, int j){
		Node swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}

	public static void swap(LinkedListShellSort a,Node i, Node j){
		if(i==a.last){
			a.last=j;
		}else if(i==a.first){
			a.first=j;
		}

		for(Node k = a.first; k != null; k=k.next){
			if(k.next==i){	
				k.next=j;	//substitui o next do anterior para apontar para o node correto
			}				
		}	
		if(j.next!=null){
			Node aux = i;
			i.next=j.next;
			j.next=aux;
		}
	}

	static void printLL(LinkedListShellSort a){
		for(Node i = a.first; i.next != null ; i=i.next){
			System.out.print(i.item + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LinkedListShellSort llp = new LinkedListShellSort();

		String en= in.nextLine();
		String[] enq = en.split(" ");
		for(int i=0; i< enq.length; i++)
			llp.enqueue(Integer.parseInt(enq[i]));
		llp.enqueue(2147483647);//Dummy node
		//sort(llp);
		hsort(llp);
		
		System.out.print("Exch: ");
		System.out.println(exch);
		System.out.print("Cmp: ");
		System.out.println(cmp);
		System.out.print("Pass: ");
		System.out.println(pass);

		in.close();
	}
}
