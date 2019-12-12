package blockchain;

public class Block {

	private String hash;
	private String previousHash;
	private String data;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.hash = this.calculateBlockHash();
	}

	private String calculateBlockHash() {
		String calculatedHash = new HashAlgorithm().dohash(previousHash + data);
		return calculatedHash;
	}

	public String getHash() {
		return this.hash;
	}

	public String getData() {
		return this.data;
	}

	@Override
	public String toString() {
		return "Previous Hash: " + previousHash + "\nData: " + data + "\nBlock Hash: " + hash;
	}
}
