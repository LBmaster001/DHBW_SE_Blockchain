package blockchain;

import java.math.BigInteger;
import java.util.Arrays;

public class HashAlgorithm {
	
	// Primes from https://bigprimes.org/
	public final BigInteger PRIME = new BigInteger("8409601453766596243"); // Near to the biggest number, to generate very large numbers
	public final BigInteger MOD = new BigInteger("9213877341630809363"); // Biggest 64 Bit Number = +-9223372036854775808.
	// This will be the maximum amount to hashes possible
	public final int BYTESIZE = 8; // 64 Bit
	
	/**
	 * This method takes a byte array as an input and performs a hash calculation on it.
	 * @param input the byte array which will be hashes. null will return null as the hash. an empty array will still give a valid output
	 * @return the hash of the given input which will always be the length of HashAlgorithm.BYTESIZE
	 */
	public byte[] dohash(byte[] input) {
		if (input == null)
			return null;
		// Hash to start with. Will be the output, if input is length == 0
		BigInteger hash = new BigInteger("2320528790493460279");
		// Magic in here
		for (byte b : input) {
			BigInteger x = new BigInteger("" + b + hash);
			BigInteger y = x.modPow(PRIME, MOD);
			hash = hash.add(y);
		}
		// Do one last Mod operation
		hash = hash.mod(MOD);
		byte[] hashBytes = hash.toByteArray();
		// Just for Safety. Number of bytes should always be equal to the BYTESIZE !
		hashBytes = Arrays.copyOfRange(hashBytes, 0, BYTESIZE);
		// Return the Calculated Bytes Array
		return hashBytes;
	}
	
	/**
	 * This method first converts the input String to a byte array using s.getBytes(). Then the hash will be calculated and returned in hex format
	 * @param input Input that should be hashed
	 * @return result of the hash algorithm on hex format
	 */
	public String dohash(String input) {
		// Get Bytes out of the String
		byte[] sBytes = input.getBytes();
		// Calculate Hash
		byte[] hash = dohash(sBytes);
		// Convert bytes to a readable hex format and return it
		return bytesToHex(hash);
	}

	// Copied from https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}
}
