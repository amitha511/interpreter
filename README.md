<h2 align="left">Text Based Calculator</h2>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#Example">Example</a></li>
        <li><a href="#Terms">Terms</a></li>
        <li><a href="#Tools">Tools</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The project is a lightweight interpreter that is able to execute commands. <br />
The input of the program is a text file , contains a series of text based commands. <br />
The program analyzes the text, executes the commands and finally prints the values of all the variables. </br> </br>


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
A command is the entire line <br />
The program supports assignment commands only. <br />
There are 2 types of assignment commands: <br />
- variable = expression <br />
- variable += expression <br />
 

## Tools

The tools that are involved in the project:

* [Docker](https://docs.docker.com/get-docker/)
* [Make](https://formulae.brew.sh/formula/make)
* [Maven](https://maven.apache.org/)
* [Java - Amazon corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

## Prerequisites

1. make sure that you have docker installed:
   ```sh
    docker version
   ```
2. make sure that you have make installed:
   ```sh
    make --version
   ```

## Executing the program

1. build docker image
   ```sh
    make build
   ```
2. execute the calculator passing an example input file as argument
   ```sh
    make run -s examples/input.txt
   ```