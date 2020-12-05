package soa;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsAmqManager implements MessageListener {
	private final String ACTIVEMQ_URI ="tcp://localhost:61616";
	private ActiveMQConnectionFactory cnFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageConsumer consumer;
	private Messaging receiver;
	
	private void connect(String queue) throws JMSException{
		if(cnFactory==null) {
			cnFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URI);
			connection = cnFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queue);
			consumer = session.createConsumer(destination);
		}
	}
	
	public void close() throws JMSException, Exception{
		if(session != null) session.close();
		if (connection != null) connection.close();
		if(consumer != null) consumer.close();
		cnFactory = null;
	}
	
	public void produce(String queue, String msg) throws JMSException, Exception{
		//skapa uppkoppling
		connect(queue);
		//Skapa producer-object för att skicka meddelande
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		try {
			//skapa meddelande
			TextMessage message = session.createTextMessage(msg);
			producer.send(message);
		}finally {
			producer.close();
			close();
		}
	}
	
	public String consume(String queue) throws JMSException, Exception{
		//koppla upp
		connect(queue);
		//hämta meddelande via MessageConsumer
		try {
			TextMessage message = (TextMessage) consumer.receive(1000);
			if(message != null)
				return message.getText();
		}finally {
			close();
		}
		return "Finns inga meddelanden i kön";
	}
	
	public void autoConsume(String queue) throws JMSException, Exception{
		//koppla upp
		connect(queue);
		//starta lyssnare på tråd med detta objekt
		consumer.setMessageListener(this);
	}
	
	@Override
	public void onMessage(Message msg) {
		try {
			//print för test
			System.out.println(((TextMessage)msg).getText());
			if(receiver!=null) receiver.setMessage((TextMessage)msg);
			//för att det inte ska gå för fort
			TimeUnit.MILLISECONDS.sleep(2000);
		}catch(InterruptedException | JMSException ex) {
			System.out.println("nått gick fel..." + ex.toString());
		}
	}
	//Ange objekt osm ska ta emot meddelande
	public void setMsgReceiver(Messaging msgReceiver) {
		receiver = msgReceiver;
	}
	
	

}
