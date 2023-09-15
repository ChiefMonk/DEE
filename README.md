## KLMDEETOOL - The KLM Defeasible Entailment and Explanations Tool

### Requirements
- Maven 4.0+
- Java 20+

### Compilation
To build the project, run the following against the home folder:
```mvn package```

### Execution
#### The Desktop App (GUI)
```java -cp target/KLMDEETool-1.0.1-jar-with-dependencies.jar uct.cs.dee.tool.ui.ToolGUI```

#### The Console App (CLI)
```java -cp target/KLMDEETool-1.0.1-jar-with-dependencies.jar uct.cs.dee.tool.ui.ToolCLI kb.txt "s~>w"```