package blockchain;

import java.security.MessageDigest;
import java.util.Random;

public class MainClass {

	public static void main(String[] args) {
		HashAlgorithm alg = new HashAlgorithm();
		String s1 = "Hello World.";
		String s2 = "HelloW orld.";
		String s3 = "Hello World/";
		String s4 = "Hello World!";
		String s5 = "";
		System.out.println("Hash of \"" + s1 + "\":  \t" + alg.dohash(s1));
		System.out.println("Hash of \"" + s2 + "\":  \t" + alg.dohash(s2));
		System.out.println("Hash of \"" + s3 + "\":  \t" + alg.dohash(s3));
		System.out.println("Hash of \"" + s4 + "\":  \t" + alg.dohash(s4));
		System.out.println("Hash of \"" + s5 + "\":  \t" + alg.dohash(s5));
		byte[] randomBytes = new byte[1024*1024];
		new Random().nextBytes(randomBytes);
		int time = benchmark(randomBytes);
		System.out.println("Took " + time + "ms to calculate 1MB of hash");;
		time = benchmarkSha256(randomBytes);
		System.out.println("Took " + time + "ms to calculate 1MB of hash");

	}

	public static int benchmark(byte[] bigarray) {
//		new Random().nextBytes(longarray);
		long start = System.currentTimeMillis();
		byte[] hash = new HashAlgorithm().dohash(bigarray);
		long time = System.currentTimeMillis() - start;
		String hashStr = HashAlgorithm.bytesToHex(hash);
		System.out.println("Hash of Bytes: " + hashStr);
		return (int) time;
	}

	public static int benchmarkSha256(byte[] bigarray) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			long start = System.currentTimeMillis();
			byte[] hash = sha256.digest(bigarray);
			long time = System.currentTimeMillis() - start;
			String hashStr = HashAlgorithm.bytesToHex(hash);
			System.out.println("Hash of Bytes: " + hashStr);
			return (int) time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

}
