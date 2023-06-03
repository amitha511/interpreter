<h2 align="left">Text Based Calculator</h2>

## About The Project

The project is able to execute commands. <br />

The input of the program is a text file containing a series of commands. <br />

The program analyzes the commands and executes them.

Finally, it prints the values of all the variables. </br> </br>


### Example
Given a text file with the following content as input:  <br />
i = 0 <br />
j = ++i <br />
x = i++ + 5 <br />
y = 5 + 3 * 10 <br />
i += y <br />

Output: <br />
(i=37,j=1,x=6,y=35) <br /> 

### Terms

<u> Token: </u> <br />
A group of characters having collective meaning. <br />
A token can't contain a space character.<br />
Examples for tokens: "i" , "i++" , "5" , "+" , "var" <br />
<br /> <u> Expression: </u> <br />
Group of tokens that can be evaluated to a single integer value. <br />
For example: "5 + 3 * i + j"<br />
<br /> <u> Command: </u> <br />
A command is the entire line. <br />
The program supports assignment commands only. <br />
There are 2 types of assignment commands: <br />
- variable = expression <br />
- variable += expression <br />