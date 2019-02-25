import java.util.ArrayList;

public class PriorityQueue{
	public static void main(String[] args){

		IO inputOutput = new IO();

		PriorityHeap pq = new PriorityHeap(inputOutput.getKeys());
		ArrayList<String> commands = inputOutput.getCommands();

		for(String command:commands){
			if(command.contains("insert")){
				int number;
				String[] temp = command.split(" ");
				temp = IO.trimArrayContents(temp);
				try{
					number = Integer.parseInt(temp[1]);
					pq.insert(number);
					pq.printHeap();
				}catch(NumberFormatException e){
					System.out.println(temp[1] + " is not a number!");
				}
			}
			else if(command.contains("decreaseKey")){
				int index;
				int decreaseBy;
				String[] temp = command.split("[,()\\s]");
				temp = IO.trimArrayContents(temp);
				try{
					index = Integer.parseInt(temp[1]);
					decreaseBy = Integer.parseInt(temp[3]);
					pq.decreaseKey(index-1,decreaseBy);
					pq.printHeap();
				}catch(NumberFormatException e){
					System.out.println(temp[1] + " or " + temp[3] + " is not a number");
				}

			}
			else if(command.contains("deleteMin")){
				pq.deleteMin();
				pq.printHeap();
			}

			else if(command.contains("increaseKey")){
				int index;
				int increaseBy;
				String[] temp = command.split("[,()\\s]");
				temp = IO.trimArrayContents(temp);
				try{
					index = Integer.parseInt(temp[1]);
					increaseBy = Integer.parseInt(temp[3]);
					pq.increaseKey(index-1, increaseBy);
					pq.printHeap();
				}catch (NumberFormatException e){
					System.out.println(temp[1] + " or " + temp[3] + " is not a number");
				}

			}
			else if(command.contains("delete")){
				int nodeNr;
				String[] temp = command.split("[,()\\s]");
				temp = IO.trimArrayContents(temp);
				try{
					nodeNr = Integer.parseInt(temp[1]);
					pq.delete(nodeNr-1);
					pq.printHeap();
				}catch (NumberFormatException e){
					System.out.println(temp[1] + " is not a number");
				}
			}
		}

	}


}