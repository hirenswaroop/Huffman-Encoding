import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;

/**
 * Huffman encoding algorithm and 
 * huffman tree creation
 * 
 * @author uscart
 *
 */
public class Huffman {
	Heap<HuffmanNode> heap = new Heap<HuffmanNode>();
	HashMap<String, String> codes = new HashMap<String, String>();
	HashMap<String, Double> freqMap;

	/**
	 * Constructor for Huffman that allocates
	 * data to the frequency hashmap
	 * 
	 * @param freqMap
	 */
	public Huffman(HashMap<String, Double> freqMap) {
		this.freqMap = freqMap;
	}

	private void buildTree() {
		HuffmanNode root = null;

		for (int i = freqMap.size() - 1; i >= 0; i--) {
			String c = (String) freqMap.keySet().toArray()[i];
			heap.add(new HuffmanNode(c, freqMap.get(c)));
		}

		while (heap.size() > 1) {
			HuffmanNode a = heap.remove();
			HuffmanNode b = heap.remove();
			HuffmanNode r = a.combine(b);

			r.setLeft(a);
			r.setRight(b);
			root = r;
			heap.add(r);
		}
	}

	private void preOrder(HuffmanNode root, String s) {
		if (root.getLeft() == null && root.getRight() == null) {
			codes.put(root.getLetter(), s);
			return;
		}
		preOrder(root.getLeft(), s + "0");
		preOrder(root.getRight(), s + "1");
	}

	/**
	 * Creates the huffman dictionary of binary numbers 
	 * using the huffman tree 
	 * 
	 * @param oFile
	 */
	public void createDict(File oFile) {
		buildTree();
		preOrder(heap.get(0), "");	
		try {
			BufferedWriter writer = new BufferedWriter (new FileWriter(oFile));
			for (int i = 0; i < codes.size(); i++) {
				String s = (String) codes.keySet().toArray()[i];
				if (s.equals(" ")) {
					writer.write("(space)" + ": " + codes.get(s) + "\n");
				} else if (s.equals("\t")) {
					writer.write("(tab)" + ": " + codes.get(s) + "\n");
				} else {
					writer.write(s + ": " + codes.get(s) + "\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String compress(String s) {
		String c = new String();
		String stmp = new String();
		BitSet bs = new BitSet();
		
		for (int i = 0; i < s.length(); i++) {
			System.out.println("CharAt: " + s.charAt(i));
			stmp = stmp + s.charAt(i);
			System.out.println("stmp: " + stmp);
			c = codes.get(stmp);
			for(int j = 0; j < c.length(); j++) {
				if (c.charAt(j) == '0') {
					bs.set(j, false);
				} else {
					bs.set(j, true);
				}
			}
			stmp = "";
		}
		return c;
	}
}