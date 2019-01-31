public class djb2 implements StringHasher {
	public int hash(String s) {
		int hash = 5381;

		for (int i = 0; i < s.length(); i++) {
			hash = ((hash << 5) + hash) + s.charAt(i);
		}
		return hash;
	}
}
