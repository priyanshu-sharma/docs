## Hadoop Assignment

1.	Mention Hadoop distribution? Difference between CDH and CDP

```
Hadoop is a framework that allows for distributed storage and processing of large data sets across clusters of computers. It has become a core component in many big data environments. It works on Map Reduce paradigm and highly efficient for aggregation queries on large dataset.

Two major distributions are Cloudera's CDH and CDP.

1. Cloudera Distribution including Apache Hadoop (CDH)
2. Cloudera Data Platform (CDP)

CDH

1. It is a distribution of Hadoop provided by Cloudera primarily for enterprise.
2. Adds other components like Apache Spark, Apache Hive, HBase, Impala, and others, pre-integrated and tested for enterprise use.
3. It also has support, management tools (Cloudera Manager) but doesn't include security features.
4. On premises deployments.
5. Focuses on traditional Hadoop components with a strong emphasis on batch processing and data warehousing.
6. Provides security features, but they are less integrated compared to CDP.
7. Comparatively get less updates as compare to CDP.
8. Traditional and rigid systems.

CDP

1. It is combination of CDH + Hortonworks Data Platform (HDP).
2. It offers enhanced data management capabilities, security, and governance across hybrid and multi-cloud environments.
3. Supports hybrid and multi-cloud environments, as well as on-premises deployments.
Components and Features:
4. Includes newer services and features for data engineering, data warehousing, machine learning, and real-time data streaming. It also integrates tools from Hortonworks like Apache NiFi and Apache Knox.
5. Offers enhanced security, governance, and data management through Cloudera's Shared Data Experience (SDX), which provides unified metadata, security, and governance across environments.
6. Actively developed with regular updates, new features, and ongoing support from Cloudera.
7. More modern and flexible.
```

2.	Explain Hadoop Architecture

```
It is designed to handle large amounts of data by distributing storage and processing across a cluster of machines. This distributed system provides high availability, fault tolerance, and scalability, making it a popular choice for big data processing. 

Key components of Hadoop architecture:

1. Hadoop Distributed File System (HDFS)
2. YARN (Yet Another Resource Negotiator)
3. MapReduce

1. Hadoop Distributed File System (HDFS)

HDFS is the storage layer of Hadoop. It allows you to store large files across multiple machines in a cluster.

1. Blocks: HDFS splits large files into smaller fixed-size blocks (default size is 128 MB). Each block is stored across different nodes in the cluster, ensuring fault tolerance.

2. Replication: Each block is replicated across multiple nodes (default replication factor is 3). This ensures data redundancy, making the system fault-tolerant.

3. NameNode: This is the master node that manages the metadata and directory structure of HDFS. It keeps track of where the data blocks are stored.

4. DataNodes: These are the worker nodes where the actual data blocks are stored. They are responsible for serving read and write requests from the clients and performing block creation, deletion, and replication upon instruction from the NameNode.

2. YARN (Yet Another Resource Negotiator)

YARN is the resource management layer of Hadoop. It schedules and manages resources for various applications running in the cluster.

1. ResourceManager: The master node of YARN, it allocates resources across all applications in the system. It keeps track of the resources available on each node.

2. NodeManager: Runs on each node in the cluster and is responsible for managing the resources available on that node. It reports the status and resource usage back to the ResourceManager.

3. ApplicationMaster: Each application running in Hadoop has its own ApplicationMaster, which coordinates the execution of the application. It negotiates resources from the ResourceManager and works with the NodeManager(s) to execute and monitor the tasks.

3. MapReduce

MapReduce is a programming model and processing layer of Hadoop. It processes large data sets in parallel across a Hadoop cluster.

1. Map Phase: The input data is divided into smaller chunks, and each chunk is processed by a Map function, which generates intermediate key-value pairs.

2. Shuffle and Sort Phase: The output from the Map function is shuffled (to group data with the same key together) and then sorted.

3. Reduce Phase: The sorted key-value pairs are then processed by the Reduce function, which aggregates the results to produce the final output.

4. Additional Components

Hadoop Common: These are the common utilities and libraries that support the other Hadoop modules. It provides necessary Java files and scripts to run Hadoop.

Hadoop Ecosystem: Besides the core components, Hadoop has an extensive ecosystem that includes tools for data ingestion, data storage, data analysis, and data management. Some of the key ecosystem components include:

1. Apache Hive: A data warehousing tool that allows SQL-like querying.
2. Apache Pig: A high-level scripting language for data analysis.
3. Apache HBase: A distributed NoSQL database that runs on top of HDFS.
4. Apache Spark: A fast data processing engine that works well with Hadoop.
5. Apache Flume and Sqoop: Tools for data ingestion into HDFS.
6. Hadoop Cluster Architecture

A Hadoop cluster is a collection of nodes where the Hadoop components are deployed.

1. Master Nodes: Typically host the NameNode and ResourceManager. These nodes manage the cluster and orchestrate the data storage and resource allocation.

2. Worker Nodes: Host DataNodes and NodeManagers. These nodes are responsible for storing the data and executing the processing tasks.

Fault Tolerance and High Availability

1. Fault Tolerance: Hadoop's architecture is inherently fault-tolerant. If a DataNode fails, the system can still access the data from other replicas. If a task fails, YARN can reschedule it on another node.

2. High Availability: In more advanced Hadoop setups, NameNode High Availability can be configured with an active and a standby NameNode, ensuring the cluster remains operational if the primary NameNode fails.
```

3.	Configuration files used during hadoop installation

```
During the installation and configuration of Hadoop, several important configuration files are used to set up and manage the Hadoop cluster. These files define various settings such as paths, replication factors, memory limits, and other operational parameters for Hadoop’s components. Below are the key configuration files used during Hadoop installation:

1. core-site.xml

1. Location: $HADOOP_HOME/etc/hadoop/core-site.xml
2. Purpose: Configures core Hadoop settings, including the default filesystem (HDFS) and other important parameters.

Key Properties:
fs.defaultFS: Defines the default filesystem URI. For HDFS, it usually looks like hdfs://namenode_hostname:port.
hadoop.tmp.dir: Specifies the directory for storing temporary data.

2. hdfs-site.xml

1. Location: $HADOOP_HOME/etc/hadoop/hdfs-site.xml
2. Purpose: Configures settings specific to HDFS, including replication factor, Namenode, and Datanode configurations.

Key Properties:
dfs.replication: Defines the default replication factor for HDFS blocks (typically set to 3).
dfs.namenode.name.dir: Specifies the directory where the NameNode metadata is stored.
dfs.datanode.data.dir: Specifies the directories where DataNode stores its data blocks.

3. yarn-site.xml

1. Location: $HADOOP_HOME/etc/hadoop/yarn-site.xml
2. Purpose: Configures YARN (Yet Another Resource Negotiator), which is responsible for managing resources and job scheduling.

Key Properties:
yarn.resourcemanager.hostname: Defines the hostname of the ResourceManager.
yarn.nodemanager.aux-services: Defines auxiliary services like mapreduce_shuffle required for MapReduce jobs.
yarn.nodemanager.resource.memory-mb: Specifies the maximum amount of memory that can be allocated to containers on each NodeManager.

4. mapred-site.xml

1. Location: $HADOOP_HOME/etc/hadoop/mapred-site.xml
2. Purpose: Configures settings for MapReduce jobs.

Key Properties:
mapreduce.framework.name: Defines the framework used to execute MapReduce jobs (usually set to yarn).
mapreduce.job.tracker: Specifies the JobTracker node (for older versions of Hadoop). In newer versions, MapReduce jobs are managed by YARN.

5. hadoop-env.sh

1. Location: $HADOOP_HOME/etc/hadoop/hadoop-env.sh
2. Purpose: Configures environment variables used by Hadoop, such as Java Home, memory settings for daemons, and more.

Key Variables:
JAVA_HOME: Specifies the path to the Java installation.
HADOOP_HEAPSIZE: Defines the maximum heap size for Hadoop daemons like NameNode, DataNode, and ResourceManager.

6. slaves (or workers)

1. Location: $HADOOP_HOME/etc/hadoop/slaves (or workers in newer versions)
2. Purpose: Lists the hostnames or IP addresses of all the worker nodes in the cluster.
Usage: During startup, the master node (NameNode or ResourceManager) uses this file to start daemons on all listed worker nodes.

7. masters

1. Location: $HADOOP_HOME/etc/hadoop/masters
2. Purpose: Lists the hostnames or IP addresses of secondary master nodes such as the SecondaryNameNode.
Usage: In some Hadoop versions, this file is used to specify where to run the SecondaryNameNode.

8. capacity-scheduler.xml (optional)

1. Location: $HADOOP_HOME/etc/hadoop/capacity-scheduler.xml
2. Purpose: Configures the Capacity Scheduler, which is used to allocate cluster resources among multiple users or groups.

Key Properties:
yarn.scheduler.capacity.root.queues: Defines the queues in the cluster.
yarn.scheduler.capacity.<queue-name>.capacity: Specifies the capacity allocated to each queue.

9. fair-scheduler.xml (optional)

1. Location: $HADOOP_HOME/etc/hadoop/fair-scheduler.xml
2. Purpose: Configures the Fair Scheduler, which is another resource scheduler option for YARN, allowing resources to be shared fairly among all jobs.

Key Properties:
<queue name="default">: Defines queues and their weights in the fair scheduling system.
```

4.	Difference between Hadoop fs and hdfs dfs

```
The commands hadoop fs and hdfs dfs are both used to interact with the Hadoop Distributed File System. 

1. Hadoop fs

1. Can interact with multiple file systems (HDFS, local file system, S3, etc.).
2. Is a more general command that wraps around the file system abstraction in Hadoop, which is why it works across different file systems.

2. HDFS dfs

1. Exclusively interacts with HDFS.
2. Is specialized for HDFS, making it more straightforward for HDFS-specific operations.
```

5.	Difference between Hadoop 2 and Hadoop 3

```
Hadoop 2 

1. Relies on data replication for fault tolerance, typically with a replication factor of 3 on 3 different Datanodes.
2. Significant storage overhead (200% overhead).
3. Introduces YARN, which separates resource management and job scheduling from MapReduce.
4. Lacks application level metrics.
5. Allows multiple namespaces and NameNodes, but it primarily operates with one active NameNode and one standby NameNode for high availability.
6. Primarily focused on CPU-based resource management.
7. Specific default ports that might conflict with other applications.
8. Does not have an intra-DataNode balancing mechanism, leading to uneven data distribution within individual DataNodes.
9. Requires Java 7.
10. MapReduce Application Master has limitations in handling large numbers of small files and dealing with resource contention.
11. Offers basic support for managing heterogeneous storage types.

Hadoop 3

1. Introduces Erasure Coding as an alternative to replication.
2. Much lower storage overhead (as low as 50%). 
3. Improves upon the YARN Timeline Service with version 2, offering better scalability, reliability, and usability. 
4. Robust and efficient way to collect and query application-level metrics.
5. Allows multiple active NameNodes, which can improve scalability by distributing metadata management across several NameNodes.
6. Introduces support for scheduling and managing resources like GPUs and FPGAs in YARN. 
7. FLexibility of port selection for ResourceManager from 8088 to 8090.
8. Introduces the Intra-DataNode Balancer, which balances data within a DataNode across multiple disks, improving storage efficiency and performance.
9. Requires Java 8 or later.
10. Enabling better handling of small files, more efficient resource allocation, and enhanced task management.
11. Enhances this support, allowing administrators to better manage and optimize the use of different storage media (e.g., SSDs, HDDs) within the cluster.
```

6.	What is replication factor ? why its important

```
RF in Hadoop is a critical parameter that defines the number of copies (or replicas) of each data block stored across different nodes in the Hadoop Distributed File System (HDFS). It ensures data availability, fault tolerance, and reliability in the Hadoop cluster.

The default replication factor is typically set to 3, meaning each data block will have three copies stored on three different DataNodes.

Importance - 
1. Fault Tolerance
2. Data Redundancy
3. Recovery from Failures
4. High Availability
5. Continuous Data Availability
6. Data Reliability
7. Protection Against Data Corruption
8. Load Balancing
9. Distributed Data Access
10. Scalability
11. Handling Large-Scale Failures
```

7.	What if Datanode fails?

```
When a DataNode in a Hadoop cluster fails, Hadoop's architecture and built-in mechanisms ensure that the system remains operational and that data is not lost. Here's what happens when a DataNode fails and how Hadoop handles it:

1. Detection of DataNode Failure

Heartbeat Mechanism
Block Reports

2. Handling of Block Replication

Under-Replicated Blocks
Re-Replication Process

3. Client Impact
Read Requests
Write Requests

4. Rebalancing the Cluster
Data Rebalancing

5. Administrative Actions
Monitoring
Manual Intervention

6. Recovery of the DataNode
Restarting the DataNode
Reintegrating into the Cluster
```

8.	What if Namenode fails?

```
When the NameNode in a Hadoop cluster fails, it can be a critical issue because the NameNode is responsible for managing the metadata and namespace of the Hadoop Distributed File System (HDFS). 

Hadoop deals with a NameNode failure:

1. Role of the NameNode
The NameNode is the master server in an HDFS cluster that manages the filesystem namespace and regulates access to files by clients. It stores metadata information about the file system, including the directory structure, file-to-block mapping, and block locations on DataNodes. Unlike DataNodes, which store the actual data, the NameNode only stores the metadata. Therefore, it is a single point of failure in a Hadoop cluster.

2. Impact of a NameNode Failure
No Access to HDFS
Read and Write Operations Halt
Risk of Data Loss: 

3. Mechanisms to Handle NameNode Failure

A. NameNode High Availability (HA)

Dual NameNodes: Hadoop provides a High Availability (HA) feature that allows running two NameNodes in a cluster: an active NameNode and a standby NameNode.

Automatic Failover: If the active NameNode fails, the standby NameNode takes over automatically, ensuring that the HDFS remains available with minimal disruption. This failover process is managed by ZooKeeper, which monitors the health of the NameNodes and coordinates the failover.

Shared Storage: Both the active and standby NameNodes share the same storage (typically a set of JournalNodes) to keep their metadata synchronized. This ensures that when a failover occurs, the standby NameNode has up-to-date information.

B. Secondary NameNode (Not a Backup)

Checkpointing: The Secondary NameNode is often misunderstood as a backup for the NameNode, but its actual role is to perform periodic checkpoints of the NameNode's metadata. It merges the NameNode's edit log with the filesystem image (FsImage) to produce a new, compact FsImage, reducing the size of the edit log and preventing it from growing too large.

Manual Recovery: In case of a NameNode failure, the FsImage and edit logs maintained by the Secondary NameNode can be used to manually restore the NameNode, but this process is not automatic and involves downtime.

C. Backups of Metadata

Regular Backups: Administrators should regularly back up the NameNode metadata (FsImage and edit logs) to ensure they can restore the NameNode in case of failure.

Remote Backup: For additional safety, storing backups in a remote location can help recover from catastrophic failures.

4. Recovery from NameNode Failure

A. With High Availability (HA) Enabled
Automatic Failover

B. Without High Availability (HA)
Manual Recovery
Restoring the NameNode
Using Secondary NameNode’s Checkpoints
Restarting Services
```

9.	Why is block size 128 MB ? what if I increase or decrease the block size

```
The default block size in Hadoop is typically set to 128 MB (or sometimes 64 MB in older versions).

Importance

1. Efficiency in Data Storage and Management:
Large File Handling
Reduced Metadata Overhead

2. Optimized I/O Performance:
Sequential Data Processing
Disk Throughput

3. Reduced Network Traffic

Impact of increase in Block Size

Benefits

1. Reduced Metadata Load
2. Improved Sequential Read/Write Performance
3. Fewer Map Tasks

Drawbacks:

1. Wasted Space for Small Files
2. Less Parallelism


Impact of decrease in Block Size

Benefits

1. Better Handling of Small Files
2. Increased Parallelism

Drawbacks:

1. Increased Metadata Overhead
2. More Network Traffic
3. Higher Job Overhead
```

10.	Small file problem

```
The "small file problem" in Hadoop refers to the inefficiency and performance issues that arise when a large number of small files are stored in HDFS. This problem can significantly impact the performance and scalability of a Hadoop cluster. 

Issues

1. Metadata Overhead:
Increased Metadata Load
Increased NameNode Memory Usage

2. Performance Degradation:
Increased Metadata Operations
MapReduce Overhead

3. Inefficient Storage Utilization:
Block Wastage

Solution

1. Combine Small Files:
Using Hadoop’s Archive Tool
Custom Aggregation

2. Use of File Formats Optimized for Small Files:
SequenceFile
Parquet and ORC

3. Leverage HBase for Small Data Items
4. Larger Blocks
5. Optimize File Processing
```

11.	What is Rack awareness?

```
Rack Awareness in Hadoop is a feature designed to enhance the fault tolerance and data locality of the (HDFS). It involves organizing DataNodes into racks within a data center and configuring Hadoop to be aware of the physical or logical rack locations of these nodes. 
It is used to manage the placement of data and replicas across different racks in a data center. A "rack" refers to a physical or logical grouping of servers within a data center that share the same network switch.

Benefits

1. Improved Fault Tolerance:
Rack-Level Failure Protection
Network Partition Handling

2. Enhanced Data Locality:
Efficient Data Processing
Minimized Data Transfer

3. Optimized Resource Utilization:
Balanced Load Distribution
Efficient Replica Placement

Data Placement Strategy:

Replica Placement: When a new block is written to HDFS, the system places the first replica on the local DataNode where the write request was made. The second replica is placed on a different rack, and the third replica is placed on yet another rack. This strategy ensures that data is replicated across multiple racks for fault tolerance.

Rack Awareness in Scheduling: When a MapReduce job is run, the job scheduler uses rack awareness to place tasks on nodes that are physically close to the data, optimizing data locality and reducing data transfer times.

Handling Failures:

Rack Failure Detection
Data Recovery
```

12.	What is SPOF ? how its resolved ?

```
Single Point of Failure (SPOF) refers to a component or system whose failure would result in the entire system or service becoming unavailable. In a distributed system like Hadoop, SPOFs can pose significant risks because their failure can lead to downtime or data loss.

Examples of SPOFs in Hadoop

1. NameNode: In traditional Hadoop setups without high availability (HA), the NameNode is a SPOF. If the NameNode fails, the HDFS becomes inaccessible because the NameNode holds all the metadata and namespace information.

2. JobTracker: JobTracker, which manages job scheduling and resource allocation, is a SPOF. If the JobTracker fails, job processing stops until the JobTracker is restored.

3. ResourceManager: The ResourceManager is responsible for resource management and job scheduling. A failure in the ResourceManager can halt job processing.

How SPOFs are Resolved

1. NameNode High Availability (HA):
Active/Standby NameNodes
ZooKeeper

2. JobTracker High Availability:
Backup JobTracker

3. ResourceManager High Availability:
Active/Standby ResourceManagers
ZooKeeper

4. DataNode Failures:
Replication
Automatic Re-replication

5. Other High Availability Configurations:
Service Redundancy
Regular Backups
```

13.	Explain zookeeper?

```
Apache ZooKeeper is a distributed coordination service designed to manage and coordinate distributed applications. It provides a reliable way to synchronize data and maintain configuration across a cluster of servers, helping to build highly available and fault-tolerant systems. ZooKeeper is widely used in distributed systems for tasks such as configuration management, leader election, distributed locking, and more.

Key Features of ZooKeeper

1. Distributed Coordination:
Centralized Service
Consistency

2. ZNodes:
Hierarchical Namespace
Data Storage

3. Leader Election:
Election Algorithms

4. Distributed Locking:
Synchronization

5. Configuration Management:
Dynamic Configuration

6. Watchers:
Event Notification

7. Fault Tolerance:
Ensemble
Quorum-Based Consensus

Working -

1. Ensemble Configuration:
Servers: ZooKeeper runs as a cluster of servers (the ensemble), each of which is a ZooKeeper server. The ensemble works together to provide coordination services and maintain a consistent state.
Leader Election: The ensemble elects one of the servers as the leader, which is responsible for handling write requests. The remaining servers act as followers, handling read requests and replicating changes from the leader.

2. ZNode Operations:
Create, Read, Update, Delete (CRUD): Clients can perform CRUD operations on ZNodes. Changes are replicated across the ensemble, and ZooKeeper ensures that all clients see a consistent view of the data.
Watches: Clients can set watches on ZNodes to be notified of changes, such as data modifications or node deletions.

3. Consensus Algorithm:
Zab Protocol: ZooKeeper uses the ZAB (ZooKeeper Atomic Broadcast) protocol for consensus. It ensures that all updates to the ZooKeeper state are broadcasted to the majority of servers in the ensemble, achieving consistency and reliability.

4. Client Interaction:
API: Clients interact with ZooKeeper through a client API, which provides methods for connecting to the ensemble, performing CRUD operations on ZNodes, setting watches, and handling notifications.
Common Use Cases for ZooKeeper

5. Distributed Configuration Management: Storing and distributing configuration data for distributed applications.
6. Leader Election: Electing a leader or coordinator in distributed systems to handle tasks that require a single point of control.
7. Distributed Locking: Implementing distributed locks to synchronize access to shared resources across a cluster.
8. Service Discovery: Maintaining a registry of available services and their locations, allowing clients to discover and connect to services dynamically.
9. Coordination and Synchronization: Managing distributed coordination tasks, such as managing state and ensuring consistency across distributed applications.
```

14.	Difference between -put and -CopyFromLocal?

```
In Hadoop's HDFS (Hadoop Distributed File System), both -put and -copyFromLocal are commands used to upload files from the local filesystem to HDFS. 

-put Command
1. Can handle both files and directories.
2. Supports wildcards for batch processing (e.g., *.txt).
3. Handles multiple source files or directories in one command.
4. Offer more comprehensive options in different Hadoop versions or distributions.
5. more general-purpose command

-copyFromLocal Command
1. It is essentially a more specific variant of the -put command.
2. It can handle both files and directories.
3. Designed to be straightforward for copying files from a local filesystem to HDFS.
4. Specialized version not that comprehensive.
5. Specifically focused on copying files from a local filesystem to HDFS.
```

15.	What is erasure coding?

```
It is a data protection technique used to enhance the reliability and availability of data in distributed storage systems, such as Hadoop's HDFS (Hadoop Distributed File System). It is an advanced method for achieving fault tolerance by encoding data into multiple pieces, which are then distributed across different storage nodes. This technique is designed to handle data loss and corruption more efficiently than traditional replication methods.

Working

1. Data Encoding:
Data Splitting
Parity Chunks

2. Storage:
Distribution

3. Data Recovery:
Reconstruction

Erasure Coding vs. Replication

Replication:
Mechanism: Replication involves making multiple copies of the same data and storing them on different nodes. For example, in HDFS, each file is replicated three times by default.
Pros: Provides high fault tolerance and straightforward recovery.
Cons: Increases storage requirements significantly because multiple copies of the same data are stored.

Erasure Coding:
Mechanism: Erasure coding divides data into smaller chunks and adds parity chunks, which are distributed across nodes.
Pros: Reduces storage overhead compared to replication while still providing fault tolerance
Cons: Adds complexity to data encoding and reconstruction processes. The encoding and decoding operations can be computationally intensive.
It uses the Reed-Solomon algorithm or similar techniques to encode data. This approach reduces the amount of storage needed compared to replication while still providing a high level of fault tolerance.

Benefits of Erasure Coding

1. Storage Efficiency:
Reduced Overhead

2. Fault Tolerance:
Recovery

3. Cost Efficiency:
Lower Storage Costs
```

16.	What is speculative execution?

```
It is a performance optimization technique used in distributed computing frameworks to improve job completion times and resource utilization. The idea is to increase the likelihood of completing tasks quickly by running duplicate tasks in parallel.

Working
1. Task Duplication
2. Task Monitoring
3. Early Termination
4. Failure Handling

Benefits
1. Reduced Job Completion Time
2. Improved Fault Tolerance
3. Load Balancing

Drawbacks of Speculative Execution
1. Increased Resource Usage
2. Potential Overhead
3. Diminished Returns
4. MapReduce
```

17.	Explain Yarn Architecture

```
It is the resource management and job scheduling component of Hadoop and used to address scalability and resource management limitations of the original MapReduce framework. It allows Hadoop to support various types of distributed applications beyond MapReduce, including interactive and real-time processing.

Core Components

1. ResourceManager (RM):
It is the master daemon responsible for managing and allocating resources across the cluster. It handles the overall resource management and job scheduling.

Components:
1. Scheduler: Allocates resources to various applications based on their requirements and policies. It supports different scheduling policies like Capacity Scheduler, Fair Scheduler, etc.
2. ApplicationManager: Manages the lifecycle of applications, including accepting job submissions, negotiating resources, and monitoring application status.

2. NodeManager (NM):
It is the per-node daemon that monitors resource usage and manages containers on individual nodes. It reports resource availability and status to the ResourceManager.

Components:
1. Container Management: Manages the lifecycle of containers, which are isolated environments where application tasks are executed.
2. Resource Monitoring: Monitors resource usage (CPU, memory, etc.) on the node and reports back to the ResourceManager.

3. ApplicationMaster (AM):
Each application running on YARN has its own ApplicationMaster. The ApplicationMaster is responsible for negotiating resources from the ResourceManager, managing the execution of application tasks, and handling application-specific logic.

Components:
1. Resource Negotiation: Requests resources from the ResourceManager and launches tasks in containers.
2. Task Management: Monitors the status of tasks, handles failures, and manages the overall execution of the application.

4. Containers:
Containers are isolated environments where application tasks are executed. They are allocated by NodeManagers based on resource requests from the ApplicationMaster.

Components:
1. Resource Isolation: Ensures that tasks running in containers do not interfere with each other, providing CPU, memory, and I/O isolation.
2. Execution: Containers run the application code (e.g., MapReduce tasks, Spark jobs) and handle task execution and data processing.

Workflow

1. Application Submission:
Submit: An application (e.g., a MapReduce job, Spark job) is submitted to the ResourceManager.
ApplicationMaster Request: The ResourceManager allocates resources for the ApplicationMaster to manage the application.

2. Resource Allocation:
ApplicationMaster: The ApplicationMaster negotiates resources from the ResourceManager based on its requirements.
Container Launch: The ResourceManager allocates containers on NodeManagers to run application tasks.

3. Task Execution:
Container Execution: NodeManagers start containers based on requests from the ApplicationMaster. The containers run the application tasks and process data.
Monitoring: The ApplicationMaster monitors task progress, handles failures, and coordinates the execution of tasks across containers.

4. Application Completion:
Completion: Once the application tasks are completed, the ApplicationMaster notifies the ResourceManager and cleans up resources.
Resource Release: The ResourceManager updates its records and releases the containers back to the pool of available resources.

Key Features of YARN
Scalability
Multi-Tenancy
Resource Management
Flexibility
Fault Tolerance
```

18.	How does ApplicationManager and Application Master differ

```
ApplicationMaster (AM)
It is responsible for managing the lifecycle of a specific application. 
It is an application-specific entity that coordinates and manages the execution of tasks for the application it serves.

Responsibilities:

1. Resource Negotiation: Requests resources from the ResourceManager to launch containers for running application tasks.
2. Task Scheduling and Execution: Manages the execution of tasks within containers. It coordinates tasks, handles failures, and monitors task progress.
3. Application-Specific Logic: Handles application-specific logic, such as data processing, job logic, and custom task management.
4. Job Monitoring: Monitors the status of the application, manages retries or restarts of failed tasks, and ensures the application progresses as expected.

Lifecycle:
Each application has its own ApplicationMaster instance. The ApplicationMaster starts when an application is submitted and runs until the application completes or fails.

ApplicationManager
It is a component of the ResourceManager that is responsible for managing the lifecycle of applications at a high level. It handles the submission and tracking of applications but does not manage the execution of application tasks.

Responsibilities:

1. Application Submission: Receives and processes new application submissions from clients, including setting up the initial state for the ApplicationMaster.
2. Application Registration: Registers and tracks the status of ApplicationMasters and their associated applications.
3. Resource Allocation: Coordinates with the ResourceManager’s Scheduler to allocate resources to ApplicationMasters for running their tasks.

Lifecycle:
The ApplicationManager is a persistent component of the ResourceManager and is responsible for managing multiple applications simultaneously. It does not terminate with the completion of an application but continues to handle new submissions and application management tasks.

Difference 

ApplicationMaster

1. Manages a single application, including resource requests, task execution, and application-specific logic.
2. Focuses on running the application’s tasks, managing the application’s execution, and ensuring that the application completes successfully.
3. Runs in a container on a node within the cluster. It is an application-specific process.
4. Created and managed on a per-application basis. It is terminated once the application is complete or fails.

ApplicationManager
1. Manages the overall lifecycle of applications at the ResourceManager level, including handling application submissions and coordinating resource allocation.
2. Deals with the administrative aspects of application management, such as receiving application submissions and tracking ApplicationMaster instances.
3. Runs as part of the ResourceManager’s daemon process and is not specific to any single application.
4. Continues to run as part of the ResourceManager’s lifecycle and manages multiple applications over time.
```

19.	Explain Mapreduce working?

```
Ot is a programming model and processing framework used for processing large datasets in a distributed manner across a cluster of computers. The MapReduce model breaks down a large data processing task into smaller, manageable chunks that can be processed in parallel, and then combines the results to produce the final output.

MapReduce Workflow

The MapReduce framework consists of two main phases: the Map phase and the Reduce phase. Here’s a step-by-step explanation of how it works:

1. Map Phase
Input Data: The input data is divided into chunks called splits or blocks. Each split is processed by a separate map task. Typically, these splits correspond to blocks of files stored in HDFS (Hadoop Distributed File System).

Map Function: Each map task applies a user-defined map function to the input data. The map function processes each record and outputs a set of intermediate key-value pairs.

The intermediate key-value pairs generated by the map function are written to local disks on the mapper nodes and then sorted and grouped by key.

2. Shuffle and Sort Phase
Shuffling: The framework redistributes the intermediate key-value pairs from the map phase to the reducers. This process is known as shuffling. The data is transferred across the network from mapper nodes to reducer nodes.

Grouping: The framework groups the intermediate key-value pairs by key, so that all values for a given key are sent to the same reducer.

Sorting: The intermediate key-value pairs are sorted by key. This ensures that the reducer receives the key-value pairs in sorted order, making it easier to process them.

3. Reduce Phase
Reduce Function: Each reduce task applies a user-defined reduce function to the sorted key-value pairs. The reduce function aggregates or processes the values for each key and produces the final output.
The final output from the reduce phase is written to HDFS. This output can be used for further processing or analysis.

Combiner: An optional optimization that performs a partial reduction on the map output before shuffling. It reduces the volume of data sent over the network and can improve performance.

Partitioner: Determines how the intermediate key-value pairs are distributed to the reducers. The default partitioner is based on hash values of the keys, but custom partitioners can be implemented for specific requirements.
```

20.	How many mappers are created for 1 GB file?

```
The number of mappers created for processing a 1 GB file in Hadoop MapReduce is determined by several factors, primarily the input split size.

Input Split Size - 128 MB
The number of mappers created is based on the size of these splits. For example, if the default split size is 128 MB, a 1 GB file would be divided into approximately 8 splits (1 GB / 128 MB per split).

For a 1 GB file with the default split size of 128 MB, Hadoop MapReduce would create approximately 8 mappers. The exact number of mappers depends on the split size configuration, so it can vary if the split size is adjusted.
```

21.	How many reducers are created for 1 GB file?

```
The number of reducers in a Hadoop MapReduce job is generally not directly tied to the size of the input file, such as a 1 GB file. Instead, it is determined by the job configuration and is typically specified by the user.

The number of reducers in a MapReduce job is specified by the job configuration and is not directly dependent on the size of the input file. For a 1 GB file, you can set the number of reducers based on your job’s requirements and cluster resources. The default is often 1, but configuring more reducers can help in optimizing job performance by balancing the processing load across multiple reducers.
```

22.	What is combiner? How does it work and provide performance gain? Where did you use it 

```
Combiner is an optimization technique in Hadoop MapReduce used to reduce the amount of data that needs to be shuffled and sorted between the map and reduce phases. It works as a local aggregation step that helps improve the efficiency of data processing by minimizing the volume of intermediate data sent over the network.

Working

1. Map Phase Output: During the Map phase, the map function generates intermediate key-value pairs for each record processed.

2. Combiner Execution: The Combiner is an optional function that runs after the map tasks and before the data is sent to the reducers. It performs a local aggregation or partial reduction of the intermediate key-value pairs generated by the mappers.

3. Local Aggregation: The Combiner function processes the intermediate data on the mapper nodes, reducing the volume of data that needs to be transferred over the network. It combines values for each key locally before the data is shuffled to the reducers.

4. Shuffle and Sort Phase: After the Combiner has processed the intermediate data, the reduced output is sent to the reducers. This reduces the total amount of data that needs to be shuffled and sorted, which can lead to performance improvements.

5. Reduce Phase: The Reduce phase receives the partially aggregated data from the Combiner, further processes it, and produces the final output.

Performance Gains
1. Reduced Network I/O:
2. Decreased Disk I/O:
3. Improved Reduce Performance:

Limitations:

1. The Combiner is not suitable for all types of aggregations. 
2. It is designed for associative and commutative operations where partial aggregation is valid and useful. 
3. It may not be appropriate for operations where combining partial results could lead to incorrect results.
```

23.	What is partitioner? How does it work and provide performance gain? Where did you use it

```
Partitioner is a key component in the Hadoop MapReduce framework that determines how the intermediate key-value pairs produced by the Map phase are distributed to the Reducers in the Reduce phase. It plays a crucial role in ensuring that data is evenly and efficiently distributed across reducers.

How the Partitioner Works

Key-Value Pairs: After the Map phase, each map task generates intermediate key-value pairs. The next step is to assign these pairs to reducers for processing.

Partitioning: The Partitioner decides which reducer will process each key-value pair based on the key. This involves mapping keys to specific reducers using a partitioning function.

By default, Hadoop uses a hash-based partitioner which hashes the key and uses the hash value to determine the reducer.

Custom Partitioner: You can implement a custom Partitioner if the default partitioning behavior does not meet the requirements of your application. This allows you to define your own logic for distributing keys among reducers.

Reducer Assignment: Based on the partitioning logic, intermediate data is shuffled and sorted by key, and then assigned to the appropriate reducers.

Benefits
Performance Gains
Load Balancing
Improved Performance
Minimized Data Skew
```
