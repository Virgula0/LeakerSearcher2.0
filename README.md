# LeakerSearcher2.0
Version 2.0 of the quick and simple algorithm to search in big files faster than other rivals (Grep-Egrep etc)

```
                    (                             (                                    
                    )\ )             )            )\ )                     )           
                   (()/(   (    ) ( /(   (  (    (()/(   (    ) (       ( /(   (  (    
                    /(_)) ))\( /( )\()) ))\ )(    /(_)) ))\( /( )(   (  )\()) ))\ )(   
                   (_))  /((_)(_)|(_)\ /((_|()\  (_))  /((_)(_)|()\  )\((_)\ /((_|()\  
                   | |  (_))((_)_| |(_|_))  ((_) / __|(_))((_)_ ((_)((_) |(_|_))  ((_) 
                   | |__/ -_) _` | / // -_)| '_| \__ \/ -_) _` | '_/ _|| ' \/ -_)| '_| 
                   |____\___\__,_|_\_\\___||_|   |___/\___\__,_|_| \__||_||_\___||_|   
 ```
## What's new in this version?
· The main important feature: It's available for all operating systems.

· All tasks are excecuted parallely in order to speed up the search.

· Search in more than 25 GB requires about 260 seconds while with a normal scan with grep about 660.

· Interactive Menu available by just lauching the program or excecute it via arg commands.

## For what os is developed?
It is developed for Unix Based systems so Linux and Mac and for Windows.

### Prerequisites
JRE at least 1.8 version (Java 8).

### Dependencies?
No dependecies

### How it works?
  ```
  ·Verify if your email is in a leak: https://haveibeenpwned.com 
  ·Now download your leak where is your target 
  ·You can use LeakerSearcher to search string in file/s leak/s faster then a manual string recovery. 
  ·Done! 
  ```
The algorithm of this software is very simple. It is coded to search strings in big files. It's very useful to search in leaks that are some Gbs or more or to search strings in other big files...
It allows you to choose the best way to search in file/s with an interactive menu or via commands.
 
![img](https://i.imgur.com/mRgf2P0.png)

### How many Extensions can I scan?
Whatever extension you want, only one constraint is present: characters of files should be at least ISO_8859_1 encoded.

    * Option 1 -> (1) Search in a single file. 

     Search for a string in a single file that is very big: 10 gb or more

    * Option 2 -> (2) Search in enumerated files.

    Search for a string in enumerated files from 1.ext to your own n.ext 

    * Option 3 -> (3) Search in all .ext files in a single path.

    Insert you path directory, next the program will scan all files in directory with extension specified by you.
    For example scan all .txt in C:\Users\Desktop\dir to search your own string

    * Option 4 -> (4) Search in all .ext files in a path and its subdirectories.

    Insert you path directory, next the program will scan all files in directory and subdirectory of your path with extension    specified by you.
    For example scan all .txt in C:\Users\Desktop\dir to search your own string
    In the directory there are
     
     --------dir
    |           \
     ------------subdirectory_1 -> search all txt
    |               \
     ----------------subdirectory_2 -> search all txt

 #### Warning!
 
 Close all other opened application while using this program.
 
 ### Installation
 ```
 git clone https://github.com/Virgula0/LeakerSearcher2.0
 cd LeakerSearcher2.0
 ```
 Linux: 
 ```
 sudo ./Linux_Debian_Installation.sh
 ```
 Mac OSx:
 ```
 ./OSX_Installer.sh
 ```
 Windows:
 Still not supported the installation but you can find the JAR file here and excecute it with:
 ```
 java -jar LeakerSearcher.jar
 ```
 #### Hey! JAR file is also executable as shown above also on OSx and Linux if you don't want to install it.
 
 ## Screen
 
 
 
 ## Versions (Current: 2.0#Stable)
 Added in this version :
 Version 2.0
 
 ## Video
 [![IMAGE ALT TEXT HERE](https://i.imgur.com/ZF6qEy1.jpg)](https://www.youtube.com/watch?v=OXG3YNKDXKE)
 Coming soon a new demonstration video.
 
 ## Author
 
 Coded by Virgula December 2017 updated in June 2019.
 https://github.com/Virgula0/LeakerSearcher2.0
