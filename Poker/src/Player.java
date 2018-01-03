public class Player{


	Hand hand = new Hand();
	double balance;

	public Player(double balance){
		this.balance = balance;
	}

	public void deal(Card c){
		hand.addCard(c);
	}


	public Card[] discard(){
		System.out.println("This is your hand:");
		hand.printHand();
		System.out.println("How many cards would you like to discard?");
		int discardSize = 0;
		Card[] discards = new Card[discardSize];
		for(int i = 0; i<discardSize; i++) {
			int indexOfCard = IO.readInt();
			discards[i] = hand.getCard(indexOfCard);
			hand.removeCard(indexOfCard);
		}
		System.out.println("Balance: " + balance);
		return discards;
	
	}


	public double wager(double min) {
		System.out.println("How much would you like to wager? To fold enter -1");
		System.out.println("Minimum wager: $" + min);
		double wagerAmount = min;
		
		
		if(hand.hasFullHouse() || this.hand.hasFlush() || hand.hasStraight() || hand.hasFourOfAKind() || hand.hasTriplet() || hand.numPairs()==2) {
			if(min < balance/4.0) {
				wagerAmount = this.balance/4.0;
				this.balance = this.balance - wagerAmount;
				System.out.println("Wager: " + wagerAmount);
				return wagerAmount;
			} else {
				wagerAmount = min;
				this.balance = this.balance - wagerAmount;
				System.out.println("Wager: " + wagerAmount);
				return wagerAmount;
			}
		}
		
		if(hand.numPairs()==1) {
			wagerAmount = balance/7.0;
			System.out.println("Wager: " + wagerAmount);
			this.balance = this.balance - wagerAmount;
			return wagerAmount;
		}
		
		System.out.println("bad hand fold");
		return -1;
		
	}

	
	public Hand showHand(){
		return hand;
	}


	public double getBalance(){
		return balance;
	}


	public void winnings(double amount){
		this.balance+=amount;
		this.hand = new Hand();
	}

}