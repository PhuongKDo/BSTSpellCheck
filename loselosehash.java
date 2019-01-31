public class loselosehash implements StringHasher {
	public int hash(String s) {
        int hash = 0;
        for (char c : s.toCharArray()) {
            hash += c;
        }
        return hash;
	}
}
