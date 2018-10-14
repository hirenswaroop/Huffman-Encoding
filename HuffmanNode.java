/**
 * Nodes used when creating the 
 * huffman encoding tree
 * 
 * @author uscart
 *
 */

public class HuffmanNode implements Comparable<HuffmanNode> {
	private String letter;
	private double freq;
	private HuffmanNode left;
	private HuffmanNode right;
	
	/**
	 * Constructor for HuffmanNode which 
	 * allocates data to letter and freq
	 * 
	 * @param letter
	 * @param freq
	 */
	public HuffmanNode(String letter, double freq) {
		this.letter = letter;
		this.freq = freq;
	}
	
	/**
	 * Gets the letter
	 * 
	 * @return letter
	 */
	public String getLetter() {
		return this.letter;
	}
	
	/**
	 * Gets the left node
	 * 
	 * @return left
	 */
	public HuffmanNode getLeft() {
		return left;
	}

	/**
	 * Sets the left node to a desired value
	 * 
	 * @param left
	 */
	public void setLeft(HuffmanNode left) {
		this.left = left;
	}

	/**
	 * Gets the right node
	 * 
	 * @return right
	 */
	public HuffmanNode getRight() {
		return right;
	}

	/**
	 * Sets the right node to a desired value
	 * 
	 * @param right
	 */
	public void setRight(HuffmanNode right) {
		this.right = right;
	}

	/**
	 * Combines the letters and frequencies
	 * of two HuffmanNodes
	 * 
	 * @param b
	 * @return HuffmanNode
	 */
	public HuffmanNode combine(HuffmanNode b) {
		return new HuffmanNode (letter + b.letter, freq + b.freq);
	}
	
	/**
	 * Returns the difference of the 
	 * first frequency with the second
	 */
	@Override
	public int compareTo(HuffmanNode o) {
		return (int) (this.freq - o.freq);
	}
}
