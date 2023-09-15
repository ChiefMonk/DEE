<img src="images\uct_logo.png" width="100px" style="text-align:center;float:center;" />

# The <strong>KLMDEETOOL</strong>
## The KLM Defeasible Entailment and Explanations Tool

### Table of Contents
1. [About the Tool](#about) 
2. [Requirements](#req)
3. [Compilation](#compile)
4. [Usage or Execution](#usage)
5. [Contributors](#cont)
6. [References](#refs)

<a name="about"></a>
## 1. About the Tool
The <strong>KLMDEETOOL</strong> is developed in Java, an object-oriented programming language, and is structured around a well-defined architecture that adheres to the Multi-tier Architecture pattern of software engineering. It consists of several interconnected components that work together to facilitate the processing and analysis of defeasible reasoning scenarios. The user interface tier or component offers both a command line interface (CLI) and a graphical user interface (GUI) for user interaction.

To aid in processing and solving complex logical expressions and queries efficiently, the KLMDEETool incorporates and relies on two
external libraries or packages: the <strong>TweetyProject</strong> and the <strong> SAT4J SAT Solver</strong>. The Desktop and Console Applications output of the <strong>KLMDEETOOL</strong> are shown below:

<img src="images\main_screen.png" width="500px" style="display: inline-block; margin: 0 auto; max-width: 300px" />
<p><sup></sup><em>Figure 1: The <strong>KLMDEETOOL</strong> GUI with an active example output</em><sub></sub></p>

<img src="images\main_screen_cli.png" width="500px" style="display: inline-block; margin: 0 auto; max-width: 300px" />
<p><sup></sup><em>Figure 2: The <strong>KLMDEETOOL</strong> CLI with an active example output</em><sub></sub></p>

<img src="images\explain_01.png" width="500px" style="display: inline-block; margin: 0 auto; max-width: 300px" />
<p><sup></sup><em>Figure 3: The <strong>KLMDEETOOL</strong> GUI explanation output for K |≈ 𝑠 |∼ 𝑤?</em><sub></sub></p>

<img src="images\explain_011.png" width="500px" style="display: inline-block; margin: 0 auto; max-width: 300px" />
<p><sup></sup><em>Figure 3: The <strong>KLMDEETOOL</strong> CLI explanation output for K |≈ 𝑠 |∼ 𝑤?</em><sub></sub></p>

<a name="req"></a>
## 2. Requirements
- Maven 4.0+
- Java 20+

<a name="compile"></a>
## 3. Compilation
The <strong>target</strong> folder already has the binary '<strong>KLMDEETool-1.0.1-jar-with-dependencies.jar</strong>' which can be run directly without any need for compilation. The default application is the Desktop Application (GUI).

To build th <strong>KLMDEETOOL</strong>, run the following against the home folder:
```mvn package```

<a name="usage"></a>
## 4. Usage or Execution

### The Desktop App (GUI)
```java -cp target/KLMDEETool-1.0.1-jar-with-dependencies.jar uct.cs.dee.tool.ui.ToolGUI```

### The Console App (CLI)
```java -cp target/KLMDEETool-1.0.1-jar-with-dependencies.jar uct.cs.dee.tool.ui.ToolCLI kb.txt "s~>w"```


<a name="cont"></a>
## 6. Contributors
* [Chipo Hamayobe (chipo@cs.uct.ac.za)](https://github.com/ChiefMonk) - Project Lead

<a name="refs"></a>
## 7. References
### The TweetyProject - [http://tweetyproject.org](http://tweetyproject.org)
The <strong>TweetyProject</strong> consists of diverse Java libraries that embody strategies for knowledge representation formalisms, encompassing classical logics, conditional logics, and probabilistic logics.

### The SAT4J SAT Solver - [http://www.sat4j.org](http://www.sat4j.org)
The <strong>SAT4J SAT Solver</strong> is a Java library designed to solve problems related to boolean satisfaction and optimisation.

### JUnit - [https://junit.org](https://junit.org)
The <strong>JUnit</strong> is a widely adopted open-source framework that provides a structured and
organised approach to writing and running unit tests in Java applications..



