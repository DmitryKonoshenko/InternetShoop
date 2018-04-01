package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.OrderItem;
import com.divanxan.internetshop.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * This is service for sending mail report about order for customer
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Service("mailService")
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private String username;
    private String password;
    private Properties props;

    /**
     * This method need for sending message with order information on user email
     *
     * @param orderDetail - OrderDetail of customer
     */
    public void send(OrderDetail orderDetail) {

        props = new Properties();
        username = "divanxan@gmail.com";
        password = "sbn32meat";
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //mail of customer
//        String toEmail = orderDetail.getUser().getEmail();
        //because i don't know what mail can be exist
        String toEmail = "divanxan@gmail.com";

        String subject = "Квитанция о покупке в магазине Элекроник";
        StringBuilder text = new StringBuilder();

        text.append("Вы Совершили покупку в магазине Электроник:\n");
        text.append("Номер заказа: ").append(orderDetail.getId()).append("\n");
        text.append("Дата: ").append(orderDetail.getOrderDate()).append("\n\n");
        List<OrderItem> orderItemList = orderDetail.getOrderItems();

        for (OrderItem item : orderItemList) {
            Product product = item.getProduct();
            text.append("Название: ").append(product.getName())
                    .append(". Цена: ")
                    .append(item.getBuyingPrice())
                    .append("рублей. Количество: ")
                    .append(item.getProductCount())
                    .append(".\n").append("Общая цена: ")
                    .append(item.getTotal()).append(" рублей.\n\n");
        }
        if (orderDetail.getDiscount() != 0) text.append("Скидка: ").append(orderDetail.getDiscount()).append("%\n");
        text.append("Итого: ").append(orderDetail.getOrderTotal()).append(" рублей.\n\n");
        text.append("Метод оплаты: ");
        if (orderDetail.isPay()) text.append(" картой.\n");
        else text.append(" наличными.\n");
        text.append("Адрес доставки: ");
        if (orderDetail.isDelivery()) {
            text.append(orderDetail.getShipping().getAddressLineOne()).append("\n");
            text.append(orderDetail.getShipping().getAddressLineTwo()).append("\n");
            text.append(orderDetail.getShipping().getCity()).append(" - ")
                    .append(orderDetail.getShipping().getPostalCode()).append("\n");
            text.append(orderDetail.getShipping().getState()).append(" - ")
                    .append(orderDetail.getShipping().getCountry()).append("\n");
        } else text.append(" самовывоз.\n");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок письма
            message.setSubject(subject);
            //Содержимое
            message.setText(text.toString());
            logger.info("Email send");
            logger.info(text.toString());
            //Отправляем сообщение
            Transport.send(message);

        } catch (MessagingException e) {
            logger.error("Message not send");
            logger.error(text.toString());
            throw new RuntimeException(e);
        }
    }

}
