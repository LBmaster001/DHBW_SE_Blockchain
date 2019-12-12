package blockchain;

public class Blockchain {
	
	public static void main(String[] args) {
		Block firstBlock = new Block("Hello World!", "0000000000000000");
		System.out.println(firstBlock);

		Block secondBlock = new Block("Hello World!", firstBlock.getHash());
		System.out.println(secondBlock);

		Block thirdBlock = new Block("Hello World!", secondBlock.getHash());
		System.out.println(thirdBlock);

		Block fourthBlock = new Block("Hello World!", thirdBlock.getHash());
		System.out.println(fourthBlock);

	}
}
