# Design Pattern
1. Singelton<br/>
Only single application run, multiple running will make read same data to casue error.
2. Composite<br/>
Every item classes is inherit from one parent class. Like essential, luxury and misc inherit from the Item class.
# Class Diagram
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/class.png)<br/>
# Instruction
1. Clone the repo
2. Open IntelliJ and from top menu click “File” -> “Open”.
3. Open the “individual-project-lingxianghu” in the popup window to load the project into IntelliJ
4. Put all test files, db files and order files under the root directory. 
5. Go to top right corner of IntelliJ and click the Run Main button to run the billing.java.
6. Follow the instructions in the console, check the console result.
7. Read "Dataset - Sheet1.csv" by click [Enter], or input the whole file name in the console, make sure the file is located the root directory.
8. After press [Enter] or input another file name, load the “config.csv” file by click [Enter]. It set the different categories' cap. (In the default, [Essential:5, Luxury:3, Misc:6]).
9. Then the next step is read the credit card file.click [Enter] or input file name to read file data.
10. The last step is get the order date. Same as the previous steps, click [Enter] or input file name.
11. After click [Enter], the application will to run and then exit.<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/enter.png)<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/enter2.png)<br/>
12. If no error detected in the input, the output file “Output.csv” will be created and show in the root directory, the file contain the total amount paid.<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/order1.png)<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/output.png)<br/>
13. If a new credit card is founded, it will update the file “Cards - Sheet1.csv”.<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/newcard.png)<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/update.png)<br/>
24. If some errors occur, the error file “Errors.txt” will provide the error item which exceeds the quantity.<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/errororder.png)<br/>
![image](https://github.com/gopinathsjsu/individual-project-lingxianghu/blob/main/pic/error.png)
