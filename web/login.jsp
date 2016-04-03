<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="login.css"> 
    <title>LabFinder</title>
</head>
<body>
<center>
<h1> Find a labpartner!</h1>
<h3>
<div id="wrapper">
<div id="textpart">
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vehicula tellus eget 
      magna dignissim a egestas odio luctus. Mauris interdum lorem sed augue venenatis 
      nec consequat magna tempor. Nullam blandit libero libero, eget posuere dolor. 		Vestibulum       Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vehicula tellus eget magna dignissim a egestas odio luctus. Mauris interdum lorem sed augue venenatis nec consequat magna tempor. Nullam blandit libero libero, eget posuere dolor. Vestibulum     
</div>

      <!--<div class="spacer"><img src="spacer.gif" width="510" height="180"></img></div>
      <div class="spacer"><img src="spacer.gif" width="760" height="200"></img></div>

      <p>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vehicula tellus eget 
      magna dignissim a egestas odio luctus. Mauris interdum lorem sed augue venenatis 
      nec consequat magna tempor. Nullam blandit libero libero, eget posuere dolor. 		Vestibulum 
      </p>-->

<div id="loginpart"> 
	Log in using your Facebook acount <br>
    <br>
    <tag:notlogged>
    <a href="login">
    <img src="http://i.imgur.com/9mIArvu.png" id="facebookButton"> 
    </a>
    </tag:notlogged>
    <tag:logged>
    You are already logged in!
    </tag:logged>

</h3>
</div>
</div>
</body>
</html>
