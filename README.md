# App_Delivery_Kafka_


  <h1>RequestHttp App_Delivery_Kafka Documentation</h1>
  ![Kafka_App drawio](https://github.com/Renanmartim/App_Delivery_Kafka_/assets/117313515/47a5828e-ecdc-45bd-89ff-f93f7610ff91)
  <h2>Actors</h2>
    <ul>
        <li><strong>RequestHttp</strong>: This actor is responsible for handling HTTP requests and communicating with the <strong>MongoDb</strong> actor to perform database operations.</li>
        <li><strong>MongoDb</strong>: This actor is responsible for communicating with a MongoDB database and performing database operations.</li>
    </ul>
    <h2>Topics</h2>
    <ul>
        <li><strong>clientTopic</strong>: This topic is used for communication between the <strong>RequestHttp</strong> actor and the client.</li>
        <li><strong>kitchen_delivery</strong>: This topic is used for communication between the <strong>Client</strong> and the <strong>Kitchen</strong> actor.</li>
        <li><strong>delivery_topic</strong>: This topic is used for communication between the <strong>Kitchen</strong> actor and the <strong>Client</strong> actor.</li>
    </ul>
    <h2>Classes</h2>
    <ul>
        <li><strong>RequestHttp</strong>: This class implements the <strong>RequestHttp</strong> actor. It is responsible for handling HTTP requests and communicating with the <strong>MongoDb</strong> actor to perform database operations.</li>
        <li><strong>MongoDb</strong>: This class implements the <strong>MongoDb</strong> actor. It is responsible for communicating with a MongoDB database and performing database operations.</li>
    </ul>
    <h2>Usage</h2>
    <p>To use this project, you will need to start the <strong>RequestHttp</strong> actor and the <strong>MongoDb</strong> actor. You can then send HTTP requests to the <strong>RequestHttp</strong> actor to perform database operations.</p>
    <p>For example, you can send a POST request to the <code>/v1</code> endpoint to create a new kitchen delivery. The request should include a JSON payload with the following format:</p>
    <pre><code>{
      "id": "65b5688efde0743b4e21",
      "name": "Hamburger",
       "description": "Hamburger no picles"
    }
    </code></pre>
    <p>The <strong>RequestHttp</strong> actor will then communicate with the <strong>MongoDb</strong> actor to create a new kitchen delivery document in the database.</p>
    <h2>Dependencies</h2>
    <ul>
        <li><strong>akka-actor</strong>: A library for building concurrent and distributed systems using the Actor Model.</li>
        <li><strong>mongodb-driver-sync</strong>: A library for communicating with a MongoDB database.</li>
    </ul>
    <h2>Building and Running</h2>
    <p>To build and run this project, you will need to have the following tools installed:</p>
    <ul>
        <li>JDK 8 or later</li>
        <li>Apache Maven 3.6.1 or later</li>
        <li>Kafka Server</li>
    </ul>
    <p>In this project there is already a file to upload the Kafka server:</p>
    <pre><code>docker-compose up --build</code></pre>
    <p>To build the project, run the following command:</p>
    <pre><code>mvn clean package</code></pre>
    <p>This will create a JAR file in the <code>target</code> directory.</p>
    <p>To run the project, use the following command:</p>
    <pre><code>java -jar target/request-http-1.0.0.jar</code></pre>
    <p>This will start the <strong>RequestHttp</strong> actor and the <strong>MongoDb</strong> actor. You can then send</p>
