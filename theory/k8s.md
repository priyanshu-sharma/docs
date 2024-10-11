Deploying a **Spark Hello World** application on **Kubernetes** involves several steps, including setting up a Kubernetes cluster, configuring the Spark environment, packaging your Spark application, and deploying it on Kubernetes. Here's a step-by-step guide to help you deploy a Spark Hello World application on Kubernetes.

### Prerequisites
- **Kubernetes cluster** (Can be local via `minikube` or `kind`, or a cloud-based cluster like GKE, EKS, or AKS).
- **kubectl** CLI tool installed and configured to interact with your Kubernetes cluster.
- **Spark binary distribution** installed.
- **Docker** installed (if you are building your own Docker images).
- **Helm** installed (optional but useful for managing Kubernetes applications).

### Step 1: Set up Kubernetes Cluster
If you don’t have a Kubernetes cluster, you can set up one locally using Minikube:

```bash
# Start Minikube
minikube start --cpus 4 --memory 8192 --disk-size 20g

# Verify the cluster is running
kubectl get nodes
```

You can also use managed Kubernetes services like Google Kubernetes Engine (GKE), Amazon EKS, or Azure AKS.

### Step 2: Build Docker Image for Spark Application
If you don’t have a Spark Docker image, you can either pull one from the official Spark repository or build your own with the application included.

1. Create a basic **Hello World** Spark application in Scala or Python:
   **HelloWorld.scala**:
   ```scala
   import org.apache.spark.sql.SparkSession

   object HelloWorld {
     def main(args: Array[String]): Unit = {
       val spark = SparkSession.builder.appName("HelloWorld").getOrCreate()
       println("Hello, World from Spark!")
       spark.stop()
     }
   }
   ```

2. Package it using **sbt** or **Maven** (for Scala/Java).
   ```bash
   sbt package
   ```

3. Create a Dockerfile that packages Spark along with your application:

   **Dockerfile**:
   ```dockerfile
   FROM apache/spark:3.4.0
   COPY target/scala-2.12/hello-world_2.12-1.0.jar /opt/spark/jars/
   ENTRYPOINT [ "/opt/spark/bin/spark-submit", "--class", "HelloWorld", "--master", "k8s://https://kubernetes.default.svc", "--deploy-mode", "cluster", "--conf", "spark.executor.instances=2", "/opt/spark/jars/hello-world_2.12-1.0.jar" ]
   ```

4. Build the Docker image:
   ```bash
   docker build -t my-spark-hello-world .
   ```

5. Push it to a container registry (like DockerHub, GCR, or ECR):
   ```bash
   docker tag my-spark-hello-world:latest <your-dockerhub-username>/my-spark-hello-world:latest
   docker push <your-dockerhub-username>/my-spark-hello-world:latest
   ```

### Step 3: Create Kubernetes Namespace for Spark
```bash
kubectl create namespace spark
```

### Step 4: Deploy the Spark Application on Kubernetes
You can use the `spark-submit` command to deploy your application to Kubernetes. 

#### Option 1: Directly Using `spark-submit`
Make sure you have Spark installed locally and the `spark-submit` command available. Submit the job to Kubernetes by pointing to the K8s master URL:

```bash
/opt/spark/bin/spark-submit \
  --class HelloWorld \
  --master k8s://https://<KUBERNETES_CLUSTER_ENDPOINT> \
  --deploy-mode cluster \
  --name spark-hello-world \
  --conf spark.executor.instances=2 \
  --conf spark.kubernetes.namespace=spark \
  --conf spark.kubernetes.container.image=<your-dockerhub-username>/my-spark-hello-world:latest \
  local:///opt/spark/jars/hello-world_2.12-1.0.jar
```

#### Option 2: Using Helm (Optional)
Alternatively, you can use **Helm** to simplify the deployment of Spark on Kubernetes.

1. Add the Spark Helm chart repository:
   ```bash
   helm repo add bitnami https://charts.bitnami.com/bitnami
   helm repo update
   ```

2. Install the Spark chart:
   ```bash
   helm install spark-hello-world bitnami/spark \
     --namespace spark \
     --set image.repository=<your-dockerhub-username>/my-spark-hello-world \
     --set image.tag=latest \
     --set sparkJob.name=spark-hello-world
   ```

### Step 5: Monitor the Spark Job
To check the status of the Spark job, you can monitor the pods in the `spark` namespace:

```bash
kubectl get pods --namespace spark
```

You can also view the logs of the driver pod to verify that the application is running properly:

```bash
kubectl logs <driver-pod-name> --namespace spark
```

### Step 6: Cleanup
Once you are done, you can clean up the resources by deleting the Helm release or job:

```bash
helm uninstall spark-hello-world --namespace spark
```

Or, if you deployed directly with `spark-submit`, you can delete the resources manually:

```bash
kubectl delete pod <pod-name> --namespace spark
```

### Conclusion
By following these steps, you can successfully deploy a **Spark Hello World** application on Kubernetes. You can either use `spark-submit` to directly submit jobs or use Helm for simplified deployment management.