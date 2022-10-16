package project01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class project01 {

	public static void main(String[] args)  {
		System.out.println("Welcome to the Movie Wall!");
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			File file = new File("tmdb_5000_credits.csv");
			Scanner myReader = new Scanner(file,"UTF-8");
			int count = 0;
			int index = 0;
			int commaCount = 0;
			myReader.nextLine();
			while(myReader.hasNextLine() && count != 100) {
				boolean comma = true;
				String data = myReader.nextLine();
				String[] splitnewData = data.split(",");
//hello
				
				for(int i = 0; i < splitnewData.length;i++) {
					if(splitnewData[i].contains("character") || splitnewData[i].contains("name")) {
					//	System.out.println(splitnewData[i]);
						String adjust = splitnewData[i].replace("\"\"","");
						//Skip first space
						adjust = adjust.substring(1,adjust.length());

						//Remove } in crew
						if(adjust.contains("}")) {
							adjust = adjust.substring(0,adjust.length() - 1);
						}
						//Remove }] in crew
						if(adjust.contains("}]")) {
							adjust = adjust.substring(0,adjust.length() - 2);
						}
						
						//If the character is the first string
						//ADDS CHARACTER 
						if (splitnewData[i].contains("character")) {
							String[] characters = adjust.split(": ");
							//System.out.println(splitnewData[i + 4]);
							//Name value
							String name = splitnewData[i + 4].replace("\"\"","");
							//Remove extra space in beginning
							name = name.substring(1,name.length());

							String[] names = name.split(": ");
							//If the index is not greater
							if(characters.length >= 2 && names.length >= 2) {
								Actor actor = new Actor(names[1].toUpperCase(),characters[1],splitnewData[1]);
							    //System.out.println(actor);
								//If the actors array contains an actor that 
								
								
								/*
								 * If actor is not found, add the actor
								 * else search for actor, add movie to actor								 * 
								 */
								
							//	boolean isAdded = false;
							//	for(int j = 0; i < actors.size();i++) {
							//		if(actors.get(i).getActor().equals(name)) {
							//			actors.get(i).getMovies().add(splitnewData[1]);
							//			isAdded = true;
							//		}
							//	}
								//If the actor is not added yet, add the actor 
							//	if(isAdded == false) {
									actors.add(actor);
							//	}
							}
						//If it is just a name, aka crew members
						} else {
							String[] characters = adjust.split(": ");
							Actor actor = new Actor(characters[1]);

						}
 					
						//System.out.println(adjust);

					}
				}
			
				//System.out.println(castData);
				//System.out.println(newData);
				//for(int i = 0; i < splitnewData.length; i++) {
				//	System.out.println(splitnewData[i]);
				//}
				
				//System.out.println(splitnewData);
				count++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
		insertionSort(actors);
		for(int i = 0; i < actors.size();i++) {
			System.out.println(actors.get(i));
		}

		//Scanning Function
		
		Scanner sc = new Scanner(System.in);
		boolean movieLoop = true;
		boolean continueLoop = true;
		boolean found = false;
		try {
			while (movieLoop) {
				continueLoop = true;
				found = false;
				System.out.println("Enter an Actor: ");
				try {
					String name = sc.nextLine().toUpperCase();
					if (name.equals(binarySearchInsert(actors,name))) {
						for(int i = 0; i < actors.size();i++) {
							if(actors.get(i).getActor().equals(name)) {
								found = true;
								//actors.get(i).printMovies();	
							}	
						}
					}
				
					System.out.println(binarySearchInsert(actors,name));
					if (found == false) {
						System.out.println("No actor found") ;
						System.out.println("Did you mean " + binarySearchInsert(actors,name));
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println();
				}
			
				System.out.println("Press Y to continue or N to stop");
				
				
				while(continueLoop) {
					String end = sc.nextLine().toUpperCase();
					if(end.equals("Y") || end.equals("YES")) {
						movieLoop = true;
						continueLoop = false;
					} else if(end.equals("N") || end.equals("NO")){
						movieLoop = false;
						continueLoop = false;
						System.out.println("Thank you for using the Movie Wall!");
					} else {
						System.out.println("Invalid arguments");
						continueLoop = true;
					}
				}
			}
		
		} catch (IllegalArgumentException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
		
	} //END OF MAIN
	
	public static void insertionSort(ArrayList<Actor> arr) {
		for (int i = 1; i < arr.size(); i++) {
			Actor temp = arr.get(i);
			int j = i - 1;
			while (j >= 0 && arr.get(j).getActor().compareTo(temp.getActor()) > 0) {
				arr.set(j+1,arr.get(j));
				j--;
			}
			arr.set(j+1, temp);
		}
	}
	public static String binarySearchInsert(ArrayList<Actor> arr, String target) {
	       	int min = 0;
	       	int max = arr.size() - 1;
	       	while (min <= max) {
	       		int mid = (min + max) /2;
	       		if(arr.get(mid).getActor().equals(target)) {
	       			return arr.get(mid).getActor();
	       		}
	       		if(arr.get(mid).getActor().compareTo(target) <= 0) {
	       			min = mid + 1;
	       		}
	       		else {
	       			max = mid - 1;
	       		}
	       	}
	         return arr.get(min).getActor();
	      }


}


