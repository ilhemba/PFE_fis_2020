<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
	
	<link rel="stylesheet" href="test.css">
-->
<html>
    <head>

	<#include "test.css">
        <title>Performance Report</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
	<img class="logo" src=${fislogo} >
	<h1 > SGW Performance</h1>
	<h2>${test_type1}</h2>
	<p align=center><img class="chart" src=${image1} ></p>
	
	<p>${interpretation1}</p>
	<p style="page-break-after: always;">&nbsp;</p> 
	<p style="page-break-after: always;">&nbsp;</p> 
			<table style="width:100%" border="1">
				<caption class="caption1">StreamGateway Performance Statics</caption>

<tr>
    <th >MAX</th>
    <th>${max1}</th> 
  </tr>
  
  <tr>
    <th >MIN</th>
    <th>${min1}</th>
  </tr>
  <tr>
    <th class="td1">AVERAGE</th>
    <th>${avr1}</th>
  </tr>
  
</table>




<h2>${test_type2}</h2>
	<p align=center><img class="chart" src=${image2} ></p>
	
	<p>${interpretation2}</p>
	
	
			<table style="width:100%" border="1">
				<caption class="caption1">StreamGateway Performance Statics</caption>

<tr>
    <th >MAX</th>
    <th>${max2}</th> 
  </tr>
  
  <tr>
    <th >MIN</th>
    <th>${min2}</th>
  </tr>
  <tr>
    <th class="td1">AVERAGE</th>
    <th>${avr2}</th>
  </tr>
  
</table>


<h2>${test_type3}</h2>
	<p align=center><img class="chart" src=${image3} ></p>
	
	<p>${interpretation3}</p>
	
	
			<table style="width:100%" border="1">
				<caption class="caption1">StreamGateway Performance Statics</caption>

<tr>
    <th >MAX</th>
    <th>${max3}</th> 
  </tr>
  
  <tr>
    <th >MIN</th>
    <th>${min3}</th>
  </tr>
  <tr>
    <th class="td1">AVERAGE</th>
    <th>${avr3}</th>
  </tr>
  
</table>
    </body>
</html>


