# Modern GUI Project

This project provides a modern GUI look for Java Swing applications. It includes components such as FelixButton and FelixFrame that can be used as alternatives to JButton and JFrame, respectively.

## Screenshots

### Modern GUI Application

![Modern GUI Application](/pics/modern_example_1.png)

Description: This screenshot shows a Java Swing application window using the modern GUI. The components have a sleek and contemporary design.

### Standard Java Swing Application
![Standard Java Swing Application](/pics/old_example.png)

Description: This screenshot shows a standard Java Swing application window without the modern GUI applied. The components have the default Swing look and feel.

### Another Example of the Modern GUI
![Another Example](/pics/modern_example_2.png)

Description: This screenshot showcases another example of the modern GUI applied to a different application.

## Installation

To use the modern GUI in your project, follow these steps:

1. Download the project as a ZIP file.
2. Extract the contents of the ZIP file to a desired location.
3. Add the extracted folder to your Java project.
4. Replace instances of JButton with FelixButton and JFrame with FelixFrame in your code.
5. Build and run your project.

Note: Make sure to include the required dependencies and import statements as specified in the project documentation.

## Usage

To utilize the modern GUI components, you can refer to the following code snippet:

```java
import com.example.gui.FelixButton;
import com.example.gui.FelixFrame;

public class MyApplication {

    public static void main(String[] args) {
        FelixFrame frame = new FelixFrame("My Application");
        FelixButton button = new FelixButton("Click Me");
        frame.add(button);
        // Add other components and logic here
        frame.pack();
        frame.setVisible(true);
    }
}
