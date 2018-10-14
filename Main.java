import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Handles input and output file 
 * accessing and creating
 * 
 * @author uscart
 *
 */
public class Main {
	/**
	 * Handles all input and output file
	 * manipulation
	 * @param args
	 */
	public static void main (String[] args) {
		File iFile;
		File oFile = null;
		Scanner fInput = null;
		StringBuilder text = new StringBuilder("");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		HashMap<String, Double> freqMap = new HashMap<String, Double>();
		int total = 0;
		
		try {
			System.out.println ("Input a valid input text file in the format yourfilename");
			Scanner input = new Scanner (System.in);
			String inputS = input.nextLine().trim() + ".txt";
			iFile = new File(inputS);
			if (inputS.equalsIgnoreCase("cancel")) {
				System.exit(0);
			}

			while (!iFile.exists()) {
				System.out.println ("Input a valid input text file in the format yourfilename");
				input = new Scanner (System.in);
				String inputS2 = input.nextLine().trim() + ".txt";
				if (inputS2.equalsIgnoreCase("cancel")) {
					System.exit(0);
				}
				iFile = new File(inputS2);
			}
			fInput = new Scanner(iFile);
			
			while (fInput.hasNextLine()) {
				String line = fInput.nextLine();
				text.append(line);
				
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					String s = "";
					s = s + c;
					total++;
					Integer val = map.get(s);
					if (val != null) {
						map.put(s, new Integer(val + 1));
					} else {
						map.put(s, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		System.out.println("Input a valid output text file in the format yourfilename");
		Scanner output = new Scanner(System.in);
		String sOut = output.nextLine().trim() + ".txt";
		oFile = new File(sOut);
		if (sOut.equalsIgnoreCase("cancel")) {
			System.exit(0);
		}
		output.close();
		
		for (int i = 0; i < map.size(); i++) {
			String c = (String) map.keySet().toArray()[i];
			freqMap.put(c, (double) map.get(c));
		}

		Huffman h = new Huffman(freqMap);
		h.createDict(oFile);
		h.compress(text.toString());
		
		fInput.close();
	}
}