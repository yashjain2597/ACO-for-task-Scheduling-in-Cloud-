# Meta-Heuristic-Algorithms
# Implementation of Ant Colony Optimization meta-heuristic for task scheduling in cloud computing environment. 

Cloud Service providers are facing problem with optimized scheduling of tasks to the virtual machines in cloud computing environment. Scheduling of resources must be done in such a way that it reduces the total resource cost, reduces total time of execution, improves throughtput, reduces failure, balances server load and provides the highest possible QOS. The problem of task scheduling comes under the category of NP-hard problems. Meta-heuristics algorithms find the best or near-best solution in reasonable amount of time by making random choices to find the solution. 

Since ACO is more profound in solving scheduling problems it very efficiently solves this problem. 

For implementing this project I have used Cloudsim 4.0 which is an open source framework used to simulate the cloud computing infrastructure and services. It is entirely written in JAVA and it enables the modelling and simulation of core features of cloud like: task queues, event processing, cloud entity formation (data centers, data center broker etc.), communication between entities, borker policy implementation and so on. With this I have used Eclipse IDE and JAVA version 11.0.10.

This project contains 4 different code files where ACO.java and ANT.java contains the code for ANT colony optimization, LinkACO.java contains the functions for simulating the cloud environment like creation of VM, creation of data center, submitVMs etc. Myallocationtest is the file from where the execution starts is contains the main function as well as the code for GUI.


