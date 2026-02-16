# Salary Incrementor
Date: 16/2/2026 

By: Sara Jalal 

[Email](mailto:saraalkhozaae@gmail.com) | [Linkedin](https://www.linkedin.com/in/sara-alkhozaae) | [GitHub](https://github.com/sarajalal2004)
***
### ***Description***
An API used to increment the salaries based on predefind basis. 
The API supports SCV file formats
The salary updated according to following rules
   * If the employee completed less than 60% of the projects they will NOT receive any increase in salary
   * On joined date, they'll receive 2% increase for each year worked only if they completed one year of work
   * Director receives 5% increase, Manager receives 2% increase. Employee receives 1% increase.
***
### ***Technologies Used***
* Intellij
    * Java
    * Springboot
* gitHub / gitBash
***
## API user guide
* You could use postman to call the following API
* ![POST](https://img.shields.io/badge/POST-e2daeb?style=flat-square)
`http://localhost:8080/api/salary/increment`

* Upload SCV file with the format
```
1,Alice,52000.0,2019-05-12,Employee,0.8
2,Bob,68000.0,2020-08-23,Manager,0.6
3,Charlie,75000.0,2021-03-19,Director,0.7
4,Diana,60000.0,2018-11-05,Manager,0.5
5,Eve,54000.0,2019-02-14,Employee,0.9
6,Frank,83000.0,2017-07-29,Director,0.4
7,Grace,71000.0,2021-10-10,Manager,0.6
8,Hank,49000.0,2019-06-30,Employee,0.7
9,Ivy,87000.0,2018-03-22,Director,0.8
10,Jack,67000.0,2020-09-15,Manager,0.5
```
* The program returns a text file included the result.
![result image in postman](https://i.imgur.com/wLF8doY.png)
***
### ***Future update***
- [ ] Add frontend for better user experiance
- [ ] Ability to customize the incrementing conditions
