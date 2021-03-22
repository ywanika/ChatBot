/**
    * A program to carry on conversations with a human user.
    * This version:
    *   Uses advanced search for keywords 
    *   Will transform statements as well as react to keywords
    * @authors Laurie White, Alex Schwarz, Anika Sharma 
    * @version March 2021
    */
   public class Magpie4
   {
   
      /**
       * Get a default greeting  
       * @return a greeting
       */ 
      public String getGreeting()
      {
         return "Hello, let's talk.";
      }
 
      /**
       * Gives a response to a user statement
       * @param statementthe user statement
       * @return a response based on the rules given
       */
      public String getResponse(String statement)
      {
         String response = "";
         if (statement.length() == 0)
         {
            response = "Say something, please.";
         }
         // if the user types ___, respond with ___
         else if (findKeyword(statement, "mother") >= 0
                  || findKeyword(statement, "father") >= 0
                  || findKeyword(statement, "sister") >= 0
                  || findKeyword(statement, "brother") >= 0)
         {
            response = "Tell me more about your family.";
         }

         else if (findKeyword(statement, "what is your name") >= 0)
         {
            response = "I am Atlantis. What is your name?";
         }

         else if (findKeyword(statement, "how are you") >= 0)
         {
            response = "Good, Do you like the weather?";
         }
         
         else if (findKeyword(statement, "hi") >= 0
                  || findKeyword(statement, "hello") >= 0)
         {
            response = "How are you?";
         }

         else if (findKeyword(statement, "I want", 0) >= 0)
         {
            response = transformIWantToStatement(statement);
         }

         else if (findKeyword(statement, "I like", 0) >= 0)
         {
            response = transformILikeStatement(statement);
         }
        
        else if (statement.indexOf("?") >= 0)
         {
            response = getRandomQuestionResponse();
         }

         else
         {
            response = getRandomStatementResponse();
         }
         return response;
      }
 
      /**
       * Take a statement with "I want to <something>." and transform it into 
       * "What would it mean to <something>?"
       * @param statement the user statement, assumed to contain "I want to"
       * @return the transformed statement
       */
      private String transformIWantToStatement(String statement)
      {
         //  Remove the final period, if there is one
         statement = statement.trim();
         String lastChar = statement.substring(statement.length() - 1);
         if (lastChar.equals("."))
         {
            statement = statement.substring(0, statement.length() - 1);
         }
         int psn = findKeyword (statement, "I want", 0);
         String restOfStatement = statement.substring(psn + 6).trim();
         return "Why do you want " + restOfStatement + "?";
      }
 
      /**  
       * Take a statement with "I like <something>." and transform it into 
       * Why do you like <something>?
       * @param statement the user statement, assumed to contain "I like"
       * @return the transformed statement
       */
      private String transformILikeStatement(String statement)
      {
         statement = statement.trim();
         String lastChar = statement.substring(statement.length() - 1);
         if (lastChar.equals("."))
         {
            statement = statement.substring(0, statement.length() - 1);
         }
         int psn = findKeyword (statement, "I like", 0);
         String restOfStatement = statement.substring(psn + 6).trim();
         return "Why do you like " + restOfStatement + "?";
      }


      /**
       * Search for one word in phrase.  The search is not case sensitive.
       * This method will check that the given goal is not a substring of a longer string
       * (so, for example, "I know" does not contain "no").  
       * @param statement the string to search
       * @param goal the string to search for
       * @param startPos the character of the string to begin the search at
       * @return the index of the first occurrence of goal in statement or -1 if it's not found
       */
      private int findKeyword(String statement, String goal, int startPos)
      {
         String phrase = statement.trim();
         //  The only change to incorporate the startPos is in the line below
         int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
  
         //  Refinement--make sure the goal isn't part of a word 
         while (psn >= 0) 
         {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
               before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
               after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
   
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
            && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
               return psn;
            }
   
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
   
         }
  
         return -1;
      }
 
      /**
       * Search for one word in phrase.  The search is not case sensitive.
       * This method will check that the given goal is not a substring of a longer string
       * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
       * @param statement the string to search
       * @param goal the string to search for
       * @return the index of the first occurrence of goal in statement or -1 if it's not found
       */
      private int findKeyword(String statement, String goal)
      {
         return findKeyword (statement, goal, 0);
      }

      /**
       * Pick a default response to a question to use if nothing else fits.
       * @return a non-committal string
       */
      private String getRandomQuestionResponse()
      {
         final int NUMBER_OF_RESPONSES = 3;
         double r = Math.random();
         int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
         String response = "";
  
         if (whichResponse == 0)
         {
            response = "Not particularly, why do you ask?";
         }
         else if (whichResponse == 1)
         {
            response = "I don’t much care for that, why do you ask?";
         }
         else if (whichResponse == 2)
         {
            response = "Let’s get political.";
         }

         return response;
      }

    /**
       * Pick a default response to a statement to use if nothing else fits.
       * @return a non-committal string
       */
      private String getRandomStatementResponse()
      {
         final int NUMBER_OF_RESPONSES = 4;
         double r = Math.random();
         int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
         String response = "";
  
         if (whichResponse == 0)
         {
            response = "Interesting, tell me more.";
         }
         else if (whichResponse == 1)
         {
            response = "Hmmm.";
         }
         else if (whichResponse == 2)
         {
            response = "Do you really think so?";
         }
         else if (whichResponse == 3)
         {
            response = "You don't say.";
         }

         return response;
      }
   }