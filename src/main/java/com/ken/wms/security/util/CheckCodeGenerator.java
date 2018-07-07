package com.ken.wms.security.util;


import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
public class CheckCodeGenerator {

	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static int width = 80;// 鐢熸垚楠岃瘉鐮佺殑瀹藉害
	private static int height = 35;// 鐢熸垚楠岃瘉鐮佺殑楂樺害
	private static int codeCount = 4;// 楠岃瘉鐮佷釜鏁�
	private static int lineCounr = 20;
	private static Font font;// 楠岃瘉鐮佸瓧浣撴牱寮�

	static {
		font = new Font("妤蜂綋", Font.BOLD|Font.ITALIC, 25);
	}

	/**
	 * 鑾峰彇楠岃瘉鐮�
	 * @return
	 */
	public Map<String, Object> generlateCheckCode() {

		// 瀛樺偍楠岃瘉鐮�
		StringBuilder codeBuilder = new StringBuilder();

		Random random = new Random();

		// 鍒涘缓 BufferedImage 瀵硅薄
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// 鍒涘缓Grapchics2D瀵硅薄
		Graphics graphics = image.getGraphics();
		Graphics2D graphics2d = (Graphics2D) graphics;

		// 璁剧疆鍥剧墖
		graphics.setColor(Color.white);
		graphics.fillRect(1, 1, width - 2, height - 2);
		graphics.setFont(font);
		
		graphics.setColor(Color.gray);
		// 缁樺埗棰滆壊鍜屼綅缃叏閮ㄤ负闅忔満浜х敓鐨勭嚎鏉�,璇ョ嚎鏉′负2f
		for (int i = 1; i <= lineCounr; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(width - 1);
			int y1 = random.nextInt(height - 1);

			Line2D line2d = new Line2D.Double(x, y, x1, y1);

			graphics2d.draw(line2d);
		}

		// 璁剧疆楠岃瘉鐮佷腑鐨勫唴瀹�
		for (int i = 0; i < codeCount; i++) {
			graphics.setColor(getRandColor());
			char c = codeSequence[random.nextInt(codeSequence.length - 1)];
			codeBuilder.append(c);
			graphics2d.drawString(c + "", 10 + 15 * i, 25);
		}

		Map<String, Object> checkCode = new HashMap<String,Object>();
		checkCode.put("checkCodeString", codeBuilder.toString());
		checkCode.put("checkCodeImage", image);
		
		return checkCode;
	}

	/**
	 * 闅忔満鐢熸垚棰滆壊
	 * 
	 * @return
	 */
	private Color getRandColor() {
		Random random = new Random();

		int r, g, b;
		r = random.nextInt(255);
		g = random.nextInt(255);
		b = random.nextInt(255);

		return new Color(r, g, b);
	}
}
