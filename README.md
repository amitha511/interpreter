<div id="top"></div>
<br />
<div align="left">
  <a>
    <img src="https://apps.apple.com/il/app/the-calculator/id398129933" alt="Calculator" width="80" height="80">
  </a>

<h2 align="left">Text Based Calculator</h2>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
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

The project is a lightweight interpreter executing assignment commands by order. <br />
The input of the program is a text file , contains a series of text based assignment commands. <br />
The program analyzes the text, executes the commands and finally prints the values of all the variables. </br> </br>
For example: <br />
Given a text file with this content as input:  <br />
i = 0 <br />
j = ++i <br />
x = i++ + 5 <br />
y = 5 + 3 * 10 <br />
i += y <br />

Output: <br />
(i=37,j=1,x=6,y=35) <br /> <br /> 
Let's learn some terms first <br />
<u> Token: </u> <br />
A group of characters having collective meaning. <br />
A token can't contain a space character in it.<br />
For example: "i" , "i++" , "5" , "+" , "var" <br />
<u> Expression: </u> <br />
Group of tokens that can be evaluated to a single integer value. <br />
For example: "5 + 3 * i + j"<br />
<u> Command: </u> <br />
The entire line of code <br />
The program supports assignment commands only. <br />
There are 2 types of assignment commands:
- variable = expression
- variable += expression
 

### Tools

The tools that are involved in the project:

* [Docker](https://docs.docker.com/get-docker/)
* [Make](https://formulae.brew.sh/formula/make)
* [Maven](https://maven.apache.org/)
* [Java - Amazon corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

## Prerequisites validations

Make sure that you have docker installed:
* 
  ```sh
  docker version
  ```
Make sure that you have make installed:
*
    ```sh
    make --version
    ```

### Executing the program

Text about running the application on docker

1. Clone the repo
   ```sh
   git clone git@github.com:ofirmatuti/text-based-calculator.git
   ```
2. Enter to the directory
   ```sh
   cd text-based-calculator
   ```
3. create a text file with the commands you want to execute:
   ```sh
    printf "i = 0 \nj = 1" > file.txt
   ```
4. print the file content
   ```sh
    cat file.txt
   ```

5. learn how to use make:
   ```sh
    make help
   ```
   
<!-- MARKDOWN LINKS & IMAGES -->
[product-screenshot]: https://apps.apple.com/il/app/the-calculator/id398129933