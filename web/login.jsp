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

<div id="wrapper">
<div id="textpart">
    <h2>WELCOME!!!</h2>
    <h3>You look like you are in the need of a labpartner, am I right?
    Then you have come to the right place. Finally there is an easy way to find a labpartner that matches your needs.
        We can help you find that one in a million labpartner that you can spend all those early morning coffies and deadline panicattacks with.

    The right labpartner for you is out there somewhere let us find him/her for you!
    LET THE MATCHING BEGIN!!</h3>
</div>

      <!--<div class="spacer"><img src="spacer.gif" width="510" height="180"></img></div>
      <div class="spacer"><img src="spacer.gif" width="760" height="200"></img></div>

      <p>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vehicula tellus eget 
      magna dignissim a egestas odio luctus. Mauris interdum lorem sed augue venenatis 
      nec consequat magna tempor. Nullam blandit libero libero, eget posuere dolor. 		Vestibulum 
      </p>-->

<div id="loginpart"> 
	<h3>Log in using your Facebook acount <br>
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
    </center>
</body>
</html>
