public class PowerRanking {
  
  public static void main(String Args[]) {
    String[] InitTeam = {"Olivia", "Larissa", "Patty", "Wavis", "Hayden", "TK",
                         "Peter", "Adam", "Anna T", "Tye Tye", "Emma", "Wacker",
                         "Zach", "Kate H", "Eddie", "Hailey", "Ben GD", "Austin",
                         "Kate Britt", "Reed", "Ben W", "Jake", "Ella", "Noah",
                         "Elisabeth J", "Anna G", "Jono", "JB", "Kate T", "Steve",
                         "Christian", "Sammy B", "Lucas", "Erin", "Jack B", "Pat S",
                         "Jack Hilt"};
    String[] CurrRankings = {};
    CurrRankings = RandomList(InitTeam);
    for(int i = 0; i < CurrRankings.length; i++)
      System.out.print(CurrRankings[i] + ", ");
    CurrRankings = RandomSwap10Per(CurrRankings);
  }
  
  public static String[] RandomList(String[] InitTeam) {
    String[] ReturnList = new String[InitTeam.length];
    for(int i = 0; i < InitTeam.length; i++){
      int testIndex = 0;
      while(true) {
        testIndex = (int)(Math.random() * (InitTeam.length));
        if(InitTeam[testIndex] != "") 
          break;
      }
      ReturnList[i] = InitTeam[testIndex];
      InitTeam[testIndex] = "";
    }
    return ReturnList;
  }
  
  public static String[] RandomSwap10Per(String[] CurrRankings) {
    String[] NewRankings = new String[CurrRankings.length];
    int NumSwaps = (int)((double)CurrRankings.length * .1);
    for(int i = 0; i < NumSwaps; i++) {
      int testIndex1 = (int)(Math.random() * (CurrRankings.length));
      int testIndex2 = 0;
      if((testIndex1 + (CurrRankings.length/4)) > (CurrRankings.length - 1))
        testIndex2 = testIndex1 - CurrRankings.length/4;
      else
        testIndex2 = testIndex1 + CurrRankings.length/4;
      NewRankings[]
    }
    return null;
  }
 
  
}
