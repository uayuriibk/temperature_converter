<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONVERTER</title>   
        <script src="http://code.jquery.com/jquery-latest.js">   
        </script>
        <script>
            $(document).ready(function() {                        
                $('#submit').click(function(event) {  
                    var numberValue=$('#number').val();
                    var resultScale=$('#scale').val();
 
                 $.post('ActionServlet',{number:numberValue, scale:resultScale},function(data) { 
                        $("#resulttext").html("<p>Result: "+data.result+"</p>");
                    }, "json");
                });                   
            });
        </script>         
</head>

<body>
  <form id="form1">
    <h1>Converter</h1>
    Enter value for converting:
    <input type="text" id="number"/><br>
    Select the resulting scale for converting:
    <select name="scale" size="1" id=scale>
      <option selected="selected" value="celsius">Celsius</option>
      <option value="fahrenheit">Fahrenheit</option>
    </select>
    <input type="button" id="submit" value="Convert !"/><br/>
    <div id="resulttext"></div>
  </form> 
</body>

</html>