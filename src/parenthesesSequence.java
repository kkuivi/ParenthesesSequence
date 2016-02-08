/*This program takes the number of pairs of parentheses and prints all the syntactically correct sequences that can be formed 
 * given this input.*/
public class parenthesesSequence {
	public static void main(String[] args){
		int pairs = 3;
		printParenSequence(pairs);
	}
	/*This method prints all the syntactically correct sequences of a given pair of parentheses.*/
	static void printParenSequence(int paren_pair){
		StringBuilder paren_sequence = new StringBuilder(); //stores a sequence of parentheses
		int num_open_placed = 0; //stores number of open parentheses - '('  - placed in a sequence
		int num_closed_placed = 0; //stores number of close parentheses - ')' - placed in a sequence
		char curr_char = '('; //stores current character added to the sequence of parentheses
		int MAX_PARENTHESES = paren_pair; //stores the maximum number of open or closed parentheses that can be placed in a sequence 
		if(paren_pair < 0)
			System.out.println("Invalid input: Pair of sequences cannot be less than 0");
		else if(paren_pair == 0)
			System.out.println("Empty sequence");
		else{
			paren_sequence.append('('); //add '(' to sequence
			printParenSequences(num_open_placed + 1, num_closed_placed, paren_pair, MAX_PARENTHESES,
					curr_char, paren_sequence);
		}
	}
	
	/*This method creates and prints all the permutations of syntactically correct sequences of parentheses, based on
	 * the pair of parentheses remaining.*/
	private static void printParenSequences(int num_open_placed, int num_closed_placed, int pairs_left,
			int MAX_PARENTHESES, char curr_char, StringBuilder paren_sequence){
		if(pairs_left == 0) //if all parentheses have been used in a sequence
			System.out.println(paren_sequence.toString());
		else{
			if(curr_char == '('){ //if '(' was the last character added to the sequence
				if(num_open_placed < MAX_PARENTHESES){ //if there are more '(' that can be added to the sequence
					StringBuilder new_sequence = new StringBuilder(paren_sequence); //copy the current sequence so that changes made will only affect the copied sequence
					new_sequence.append('('); //add '(' to the copied sequence
					printParenSequences(num_open_placed + 1, num_closed_placed, pairs_left,
							MAX_PARENTHESES, curr_char, new_sequence);
				}
				//for every '(' added to a sequence a ')' can come immediately after it
				StringBuilder new_sequence = new StringBuilder(paren_sequence); //copy current sequence so that changes made to it will only affect copied sequence
				new_sequence.append(')'); //add ')' to the copied sequence
				curr_char = ')'; //indicate that ')' was the last character added to the sequence
				printParenSequences(num_open_placed, num_closed_placed + 1, pairs_left - 1, 
						MAX_PARENTHESES, curr_char, new_sequence); //once a ')' has been added to the sequence then decrement pairs_left
			}
			else if(curr_char == ')'){ //if ')' was the last character added to the sequence
				if(num_open_placed > num_closed_placed){ //can only add a ')' to a sequence if there are more '(' than ')' in the sequence
					StringBuilder new_sequence = new StringBuilder(paren_sequence); //copy current sequence so that changes made to it will only affect the copied sequence
					new_sequence.append(')'); //add ')' to the copied sequence
					printParenSequences(num_open_placed, num_closed_placed + 1, pairs_left -1, 
							MAX_PARENTHESES, curr_char, new_sequence); //once a ')' has been added to the sequence then decrement pairs_left
				}
				if(num_open_placed < MAX_PARENTHESES){ //can only add '(' after a ')' if there are more unused '(' that can be added to the sequence
					StringBuilder new_sequence = new StringBuilder(paren_sequence);//copy current sequence so that changes made to it will only affect the copied sequence 
					new_sequence.append('('); //add '(' to the current sequence
					curr_char = '('; //indicate that '(' was added to the sequence
					printParenSequences(num_open_placed + 1, num_closed_placed, pairs_left, 
							MAX_PARENTHESES, curr_char, new_sequence);
				}
			}
		}
	}

}
