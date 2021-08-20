package com.example.carapp.mqtt;

import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt_Client {
    String subTopic = "liao/topic";
    String pubTopic = "liao/topic";
    String content;
    int qos = 1;
    String broker = "tcp://broker.emqx.io:1883";
    String clientId = "emqx_test";
    private String link;
    String mContext;
    private MqttClient client;
    MemoryPersistence persistence = new MemoryPersistence();
    public Mqtt_Client(){
        Connect();
    }
    public void setClient(MqttClient client) {
        this.client = client;
    }
    public void sendMsg(String string) throws MqttException {
        // 消息发布所需参数
        this.content = string;
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        this.client.publish(pubTopic, message);
        System.out.println("Message published");
    }
    public void setLink(String Link){
        this.link = Link;
        System.out.println(this.link);
    }
    public String getLink(){
        return this.link;
    }
    public void Connect(){
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            setClient(client);
            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_test");
            connOpts.setPassword("emqx_test_password".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String tmp;
                    tmp = new String(message.getPayload());
                    System.out.println("123");
                    System.out.println("接收訊息主題 : " + topic);
                    System.out.println("接收訊息Qos : " + message.getQos());
                    System.out.println("接收訊息內容 : " + tmp);
                    setLink(tmp);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");
            System.out.println("Publishing message: " + content);

            // 订阅
            client.subscribe(subTopic);


            //client.disconnect();
            System.out.println("Disconnected");
            // client.close();
            //System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
