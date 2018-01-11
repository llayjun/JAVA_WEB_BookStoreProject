package com.llayjun.bookstore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 锟斤拷证锟斤拷锟斤拷锟缴筹拷锟斤拷
 * 
 */
public class CheckImgServlet extends HttpServlet {

	// 锟斤拷锟斤拷锟叫憋拷锟斤拷锟斤拷锟叫筹拷锟斤拷
	private List<String> words = new ArrayList<String>();

	@Override
	public void init() throws ServletException {
		// 锟斤拷始锟斤拷锟阶段ｏ拷锟斤拷取new_words.txt
		// web锟斤拷锟斤拷锟叫讹拷取 锟侥硷拷锟斤拷锟斤拷锟斤拷使锟矫撅拷锟皆达拷锟斤拷路锟斤拷
		String path = getServletContext().getRealPath("/WEB-INF/new_words.txt");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GBK"));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 锟斤拷止锟斤拷锟斤拷
		// response.setHeader("Cache-Control", "no-cache");
		// response.setHeader("Pragma", "no-cache");
		// response.setDateHeader("Expires", -1);

		int width = 120;
		int height = 30;

		// 锟斤拷锟斤拷一 锟斤拷锟斤拷一锟斤拷锟节达拷锟斤拷图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 锟斤拷锟斤拷锟� 图片锟斤拷锟狡憋拷锟斤拷锟斤拷色 ---通锟斤拷锟斤拷图锟斤拷锟斤拷
		Graphics graphics = bufferedImage.getGraphics();// 锟矫碉拷锟斤拷图锟斤拷锟斤拷 --- 锟斤拷锟斤拷
		// 锟斤拷锟斤拷锟轿猴拷图锟斤拷之前 锟斤拷锟斤拷锟斤拷指锟斤拷一锟斤拷锟斤拷色
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);

		// 锟斤拷锟斤拷锟斤拷 锟斤拷锟狡边匡拷
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 锟斤拷锟斤拷锟斤拷 锟侥革拷锟斤拷锟斤拷锟斤拷锟�
		Graphics2D graphics2d = (Graphics2D) graphics;
		// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
		graphics2d.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 18));

		Random random = new Random();// 锟斤拷锟斤拷锟斤拷锟斤拷锟�
		int index = random.nextInt(words.size());
		String word = words.get(index);// 锟斤拷贸锟斤拷锟�

		// 锟斤拷锟斤拷x锟斤拷锟斤拷
		int x = 10;
		for (int i = 0; i < word.length(); i++) {
			// 锟斤拷锟斤拷锟缴�
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 锟斤拷转 -30 --- 30锟斤拷
			int jiaodu = random.nextInt(60) - 30;
			// 锟斤拷锟姐弧锟斤拷
			double theta = jiaodu * Math.PI / 180;

			// 锟斤拷锟斤拷锟侥革拷锟斤拷锟�
			char c = word.charAt(i);

			// 锟斤拷c 锟斤拷锟斤拷锟酵计�
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}

		// 锟斤拷锟斤拷证锟斤拷锟斤拷锟捷憋拷锟斤拷session
		request.getSession().setAttribute("checkcode_session", word);

		// 锟斤拷锟斤拷锟斤拷 锟斤拷锟狡革拷锟斤拷锟斤拷
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}

		// 锟斤拷锟斤拷锟斤拷图片锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 ImageIO
		graphics.dispose();// 锟酵凤拷锟斤拷源
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 取锟斤拷某一锟斤拷围锟斤拷color
	 * 
	 * @param fc
	 *            int 锟斤拷围锟斤拷锟斤拷1
	 * @param bc
	 *            int 锟斤拷围锟斤拷锟斤拷2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// 取锟斤拷锟斤拷锟斤拷锟缴�
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
