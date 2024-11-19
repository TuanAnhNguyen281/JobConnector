package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class cvTemplateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"Basic_cv.png\"");

        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String introduction = request.getParameter("introduction");
        String education = request.getParameter("education");
        String experience = request.getParameter("experience");
        String activities = request.getParameter("activities");
        String skills = request.getParameter("skills");

        // Kích thước hình ảnh điều chỉnh nhỏ hơn
        int width = 800;
        int height = 1130; // Khoảng A4

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Vẽ nền trắng
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Xử lý ảnh đại diện - giảm kích thước avatar
        try {
            Part photoPart = request.getPart("photo");
            if (photoPart != null && photoPart.getSize() > 0) {
                InputStream photoStream = photoPart.getInputStream();
                BufferedImage uploadedImage = ImageIO.read(photoStream);
                
                int avatarSize = 150; // Tăng kích thước avatar
                BufferedImage circularAvatar = createCircularAvatar(uploadedImage, avatarSize);
                g2d.drawImage(circularAvatar, 30, 30, null);
            } else {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillOval(30, 30, 150, 150);
            }
        } catch (Exception e) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(30, 30, 150, 150);
        }

        // Vẽ thông tin cá nhân - điều chỉnh vị trí
        drawPersonalInfo(g2d, name, position, dob, address, phone, email);

        // Vẽ các section với khoảng cách nhỏ hơn
        int yOffset = 250; 
        yOffset = drawSection(g2d, "Giới thiệu", introduction, 30, yOffset);
        yOffset = drawSection(g2d, "Học vấn", education, 30, yOffset + 30);
        yOffset = drawSection(g2d, "Kinh nghiệm làm việc", experience, 30, yOffset + 30);
        yOffset = drawSection(g2d, "Hoạt động", activities, 30, yOffset + 30);
        yOffset = drawSection(g2d, "Kỹ năng", skills, 30, yOffset + 30);

        g2d.dispose();
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }

    private void drawPersonalInfo(Graphics2D g2d, String name, String position, 
            String dob, String address, String phone, String email) {
        // Tên
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 28)); // Tăng kích thước font
        g2d.drawString(name, 200, 70);

        // Vị trí
        g2d.setFont(new Font("Arial", Font.BOLD, 20)); // Tăng kích thước font
        g2d.setColor(new Color(80, 80, 80));
        g2d.drawString(position, 200, 100);

        // Thông tin liên hệ
        g2d.setFont(new Font("Arial", Font.PLAIN, 14)); // Tăng kích thước font
        g2d.setColor(Color.BLACK);
        g2d.drawString("DOB: " + formatDate(dob), 200, 130);
        g2d.drawString(address, 200, 150);
        g2d.drawString(phone, 200, 170);
        g2d.drawString(email, 200, 190);
    }

    private int drawSection(Graphics2D g2d, String title, String content, int x, int yStart) {
        // Tiêu đề section
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20)); // Tăng kích thước font
        g2d.drawString(title, x, yStart);

        // Đường gạch dưới
        g2d.drawLine(x, yStart + 5, x + 740, yStart + 5);

        // Nội dung với font nhỏ hơn
        g2d.setFont(new Font("Arial", Font.PLAIN, 14)); // Tăng kích thước font
        return drawMultiLineText(g2d, content, x, yStart + 30, 740); // Tăng khoảng cách giữa tiêu đề và nội dung
    }

    private int drawMultiLineText(Graphics2D g2d, String text, int x, int y, int maxWidth) {
        if (text == null || text.trim().isEmpty()) {
            return y + 30; // Khoảng trống tối thiểu cho section trống
        }

        String[] paragraphs = text.split("\n");
        int currentY = y;
        int lineHeight = g2d.getFontMetrics().getHeight() + 8; // Tăng khoảng cách dòng

        for (String paragraph : paragraphs) {
            String[] words = paragraph.split("\\s+");
            StringBuilder currentLine = new StringBuilder();

            for (String word : words) {
                if (g2d.getFontMetrics().stringWidth(currentLine + " " + word) <= maxWidth) {
                    if (currentLine.length() > 0) {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                } else {
                    if (currentLine.length() > 0) {
                        g2d.drawString(currentLine.toString(), x, currentY);
                        currentY += lineHeight;
                        currentLine = new StringBuilder(word);
                    } else {
                        g2d.drawString(word, x, currentY);
                        currentY += lineHeight;
                    }
                }
            }
            if (currentLine.length() > 0) {
                g2d.drawString(currentLine.toString(), x, currentY);
                currentY += lineHeight;
            }
            currentY += 10; // Tăng khoảng trống giữa các đoạn
        }

        return currentY;
    }

    private String formatDate(String rawDate) {
        return rawDate;
    }

    private BufferedImage createCircularAvatar(BufferedImage source, int size) {
        BufferedImage circularImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circularImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
        
        double scaleFactor = Math.max((double)size/source.getWidth(), (double)size/source.getHeight());
        int scaleWidth = (int)(source.getWidth() * scaleFactor);
        int scaleHeight = (int)(source.getHeight() * scaleFactor);
        
        int x = (size - scaleWidth)/2;
        int y = (size - scaleHeight)/2;
        
        g2.drawImage(source, x, y, scaleWidth, scaleHeight, null);
        g2.dispose();
        
        return circularImage;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
