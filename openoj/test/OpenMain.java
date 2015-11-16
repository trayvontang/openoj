package practice.test;

import java.util.Scanner;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class OpenMain
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();
		System.out.println(a+b);
		input.close();
	}
}
