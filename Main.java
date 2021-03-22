import java.util.Scanner;
  /**
   * A simple class to run the Magpie class.
   * @authors Laurie White, Alex Schwarz, Anika Sharma 
   * @version March 2021
   */
class Main {
  public static void main(String[] args)
	  {
        //create magpie object to run methods
		Magpie4 maggie = new Magpie4();
		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();

        //until person says "Bye", respond and give them prompt
		while (!statement.equals("Bye"))
		{
			System.out.println(":"+maggie.getResponse(statement));
			statement = in.nextLine();
		}
			
	  }
}